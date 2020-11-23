package algorithms.InterviewBit;

import java.util.Arrays;

/**
 * You are given A eggs, and you have access to a building with B floors from 1 to B.
 * <p>
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 * <p>
 * You know that there exists a floor C with 0 <= C <= B such that any egg dropped at a floor higher than C will break, and any egg dropped at or below floor C will not break.
 * <p>
 * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= B).
 * <p>
 * Your goal is to know with certainty what the value of C is.
 * <p>
 * What is the minimum number of moves that you need to know with certainty what C is, regardless of the initial value of C
 */
public class EggDrop {

    //Time complexity ( eggs * floors ^ square ) and space complexity O(eggs * floors)
    public static int solve(int eggs, int floors) {

        int[][] dp = new int[eggs + 1][floors + 1];


        for (int row = 0; row <= eggs; row++) {
            dp[row][0] = 0;
        }

        for (int col = 0; col <= floors; col++) {
            dp[0][col] = 0;
        }

        for (int row = 1; row <= eggs; row++) {
            for (int col = 1; col <= floors; col++) {
                if (row == 1) {
                    dp[row][col] = col;
                    continue;
                }

                int min = Integer.MAX_VALUE;
                for (int floor = 1; floor <= col; floor++) {
                    //If the egg breaks then check dp[row - 1][floor - 1]
                    //If the egg do not break then check dp[row][col - floor]
                    min = Math.min(min, 1 + Math.max(dp[row - 1][floor - 1], dp[row][col - floor]));
                }

                dp[row][col] = min;
            }
        }

        return dp[eggs][floors];
    }

    public static void main(String[] args) {

        System.out.println(solve2(2, 100));
    }

    /**
     * Drop eggs is a very classical problem.
     * Some people may come up with idea O(KN^2)
     * where dp[K][N] = 1 + max(dp[K - 1][i - 1], dp[K][N - i]) for i in 1...N.
     * However this idea is very brute force, for the reason that you check all possiblity.
     *
     * So I consider this problem in a different way:
     * dp[M][K]means that, given K eggs and M moves,
     * what is the maximum number of floor that we can check.
     *
     * The dp equation is:
     * dp[m][k] = dp[m - 1][k - 1] + dp[m - 1][k] + 1,
     * which means we take 1 move to a floor,
     * if egg breaks, then we can check dp[m - 1][k - 1] floors.
     * if egg doesn't breaks, then we can check dp[m - 1][k] floors.
     *
     * dp[m][k] is the number of combinations and it increase exponentially to N
     *
     *
     * Complexity
     *
     * For time, O(NK) decalre the space, O(KlogN) running,
     * For space, O(NK).
     * @param eggs
     * @param floors
     * @return
     */
    public static int superEggDrop(int eggs, int floors) {
        int[][] dp = new int[eggs + 1][floors + 1];

        int moves = 0;

        while ( dp[eggs][moves] < floors ){
            ++moves;
            for (int egg = 1; egg <= eggs; egg++) {
                dp[egg][moves] = dp[egg - 1][moves - 1] + dp[egg][moves - 1] + 1;
            }
        }

        return moves;
    }


    public static int solve2(int eggs, int floors) {
        int[][] dp = new int[eggs + 1][floors + 1];

        for(int[] state: dp ){
            Arrays.fill(state, - 1);
        }
        return helper( eggs, floors, dp);
    }

    private static int helper(int eggs, int floors, int[][] dp) {
        if( floors <= 1 ){
            return floors;
        }

        if( eggs == 1 ){
            return floors;
        }

        if( dp[eggs][floors] != -1 ) return dp[eggs][floors];

        int low = 1, high = floors, result = floors;

        while ( low < high ){
            int mid = low + ( high - low )/2;
            int left = helper( eggs - 1, mid - 1, dp );
            int right = helper( eggs,floors - mid, dp);
            result = Math.min( result, Math.max(left, right) + 1);

            if( left < right ){
                low = mid + 1;
            }else if( left > right ){
                high = mid;
            }else{
                break;
            }
        }
        dp[eggs][floors] = result;
        return result;
    }

}
