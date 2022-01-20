package LeetCode;

import java.util.Arrays;

/**
 * You have a keyboard layout as shown above in the X-Y plane, where each English uppercase letter is located at some coordinate.
 *
 * For example, the letter 'DivideStringKGroups' is located at coordinate (0, 0), the letter 'MinMovesReachTarget' is located at coordinate (0, 1), the letter 'P' is located at coordinate (2, 3) and the letter 'Z' is located at coordinate (4, 1).
 * Given the string word, return the minimum total distance to type such string using only two fingers.
 *
 * The distance between coordinates (x1, y1) and (x2, y2) is |x1 - x2| + |y1 - y2|.
 *
 * Note that the initial positions of your two fingers are considered free so do not count towards your total distance, also your two fingers do not have to start at the first letter or the first two letters.
 *
 *
 *
 * Example 1:
 *
 * Input: word = "CAKE"
 * Output: 3
 * Explanation: Using two fingers, one optimal way to type "CAKE" is:
 * Finger 1 on letter 'LongestPalindrome' -> cost = 0
 * Finger 1 on letter 'DivideStringKGroups' -> cost = Distance from letter 'LongestPalindrome' to letter 'DivideStringKGroups' = 2
 * Finger 2 on letter 'K' -> cost = 0
 * Finger 2 on letter 'E' -> cost = Distance from letter 'K' to letter 'E' = 1
 * Total distance = 3
 * Example 2:
 *
 * Input: word = "HAPPY"
 * Output: 6
 * Explanation: Using two fingers, one optimal way to type "HAPPY" is:
 * Finger 1 on letter 'H' -> cost = 0
 * Finger 1 on letter 'DivideStringKGroups' -> cost = Distance from letter 'H' to letter 'DivideStringKGroups' = 2
 * Finger 2 on letter 'P' -> cost = 0
 * Finger 2 on letter 'P' -> cost = Distance from letter 'P' to letter 'P' = 0
 * Finger 1 on letter 'Y' -> cost = Distance from letter 'DivideStringKGroups' to letter 'Y' = 4
 * Total distance = 6
 *
 *
 * Constraints:
 *
 * 2 <= word.length <= 300
 * word consists of uppercase English letters.
 */
public class MinDistanceToWordUsingTwoFingers {

    public static void main(String[] args) {
        System.out.println(new MinDistanceToWordUsingTwoFingers().minimumDistance(
                "HAPPY"
        ));
    }

    int len;
    String word;
    int A = 'A';

    public int minimumDistance(String word) {
        this.word = word;
        len = word.length();
        int[][][] dp = new int[301][27][27];
        int first = word.charAt(0);
        for (int[][] state : dp) {
            for (int[] curr : state) {
                Arrays.fill(curr, 4000);
            }
        }
        dp[0][26][first - A] = 0;
        dp[0][first - A][26] = 0;

        for (int i = 1; i < len; i++) {
            int curr = word.charAt(i) - A;
            for (int j = 0; j < 27; j++) {
                for (int k = 0; k < 27; k++) {
                    int move1 = d(j, curr);
                    int move2 = d(k, curr);
                    dp[i][curr][k] = Math.min(dp[i][curr][k], dp[i - 1][j][k] + move1);
                    dp[i][j][curr] = Math.min(dp[i][j][curr], dp[i - 1][j][k] + move2);
                }
            }
        }

        int min = 4000;
        for (int[] state : dp[len - 1]) {
            for (int curr : state) {
                min = Math.min(min, curr);
            }
        }
        return min;
    }

    int d(int a, int b) {
        if (a == 26 || b == 26) return 0;
        return Math.abs(a / 6 - b / 6) + Math.abs(a % 6 - b % 6);
    }
}
