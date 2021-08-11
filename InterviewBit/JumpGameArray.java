package InterviewBit;

/**
 * Given an array of non-negative integers, A, you are initially positioned at the first index of the array.
 * <p>
 * Each element in the array represents your maximum jump length at that position.
 * <p>
 * Determine if you are able to reach the last index.
 * <p>
 * <p>
 * Input Format:
 * <p>
 * The first and the only argument of input will be an integer array A.
 * Output Format:
 * <p>
 * Return an integer, representing the answer as described in the problem statement.
 * => 0 : If you cannot reach the last index.
 * => 1 : If you can reach the last index.
 * Constraints:
 * 1 <= len(A) <= 1e6
 * 0 <= A[i] <= 30
 * <p>
 * Examples:
 * <p>
 * Input 1:
 * A = [2,3,1,1,4]
 * <p>
 * Output 1:
 * 1
 * <p>
 * Explanation 1:
 * Index 0 -> Index 2 -> Index 3 -> Index 4 -> Index 5
 * <p>
 * Input 2:
 * A = [3,2,1,0,4]
 * <p>
 * Output 2:
 * 0
 * <p>
 * Explanation 2:
 * There is no possible path to reach the last index.
 */
public class JumpGameArray {

    public static int canJump(int[] arr) {
        int len = arr.length;
        if( len == 0 ) return 0;
        int[] dp = new int[len];
        dp[len - 1] = 1;
        for (int index1 = len - 2; index1 >= 0; index1--) {

            int reach = Math.min(arr[index1] + index1, len - 1);

            for (int index2 = index1 + 1; index2 <= reach; index2++) {
                if (dp[index2] == 1) {
                    dp[index1] = 1;
                    break;
                }
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {

        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
    }
}
