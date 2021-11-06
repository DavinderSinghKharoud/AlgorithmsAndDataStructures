package Preparation;

/**
 * Implement wildcard pattern matching with support for ‘?’ and ‘*’ for strings ScoreSmallestIndexWithEqualValue and MinAndMaxNumberOfNodes.
 *
 * ’?’ : Matches any single character.
 * ‘*’ : Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Input Format:
 *
 * The first argument of input contains a string ScoreSmallestIndexWithEqualValue.
 * The second argument of input contains a string MinAndMaxNumberOfNodes.
 * Output Format:
 *
 * Return 0 or 1:
 *     => 0 : If the patterns do not match.
 *     => 1 : If the patterns match.
 * Constraints:
 *
 * 1 <= length(ScoreSmallestIndexWithEqualValue), length(MinAndMaxNumberOfNodes) <= 9e4
 * Examples :
 *
 * Input 1:
 *     ScoreSmallestIndexWithEqualValue = "aa"
 *     MinAndMaxNumberOfNodes = "a"
 *
 * Output 1:
 *     0
 *
 * Input 2:
 *     ScoreSmallestIndexWithEqualValue = "aa"
 *     MinAndMaxNumberOfNodes = "aa"
 *
 * Output 2:
 *     1
 *
 * Input 3:
 *     ScoreSmallestIndexWithEqualValue = "aaa"
 *     MinAndMaxNumberOfNodes = "aa"
 *
 * Output 3:
 *     0
 *
 * Input 4:
 *     ScoreSmallestIndexWithEqualValue = "aa"
 *     MinAndMaxNumberOfNodes = "*"
 *
 * Output 4:
 *     1
 */
public class RegularExpressionMatch {

    public int isMatch(String a, String b) {
        String temp = a;
        a = b;
        b = temp;

        int len1 = a.length(), len2 = b.length();
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;
        for (int j = 1; j <= len1; j++) {
            if (a.charAt(j - 1) == '*') {
                dp[j][0] = dp[j - 1][0];
            }
        }

        for (int row = 1; row <= len1; row++) {
            for (int col = 1; col <= len2; col++) {
                char aa = a.charAt(row - 1);
                if (aa == '*') {
                    dp[row][col] = (dp[row - 1][col] || dp[row - 1][col - 1] || dp[row][col - 1]);
                } else if (aa == '?' || aa == b.charAt(col - 1)) {
                    dp[row][col] = dp[row - 1][col - 1];
                } else {
                    dp[row][col] = false;
                }
            }
        }
        return (dp[len1][len2]) ? 1 : 0;
    }
}
