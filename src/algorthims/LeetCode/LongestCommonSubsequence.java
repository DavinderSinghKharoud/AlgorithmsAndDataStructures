package algorthims.LeetCode;

/**
 * Given two strings text1 and text2, return the length of their longest common subsequence.
 * <p>
 * A subsequence of a string is a new string generated from the original string with some characters(can be none) deleted without changing the relative order of the remaining characters. (eg, "ace" is a subsequence of "abcde" while "aec" is not). A common subsequence of two strings is a subsequence that is common to both strings.
 * <p>
 * <p>
 * <p>
 * If there is no common subsequence, return 0.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 */
public class LongestCommonSubsequence {

    //Time limit exceeded
    public static int longestCommonSubsequence(String text1, String text2) {

        return helper(text1, text2, 0, 0);
    }

    private static int helper(String text1, String text2, int i, int j) {

        if (i == text1.length() || j == text2.length()) {
            return 0;
        }

        if (text1.charAt(i) == text2.charAt(j)) {
            return 1 + helper(text1, text2, i + 1, j + 1);
        } else {
            return Math.max(helper(text1, text2, i + 1, j), helper(text1, text2, i, j + 1));
        }

    }

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence2("abcde", "adce"));
    }

    //Dynamic programming approach
    public static int longestCommonSubsequence2(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1][len2] ;

        int i = 0;
        int j = 0;


        while (i < len1) {
             j = 0;
            while (j < len2) {

                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i][j] = 1 + (( i - 1 < 0 || j - 1 < 0 ) ? 0:  dp[i - 1][j - 1] );
                } else {
                    int left = (i - 1 < 0 || j < 0) ? 0 : dp[i - 1][j];
                    int top = (j - 1 < 0 || i < 0) ? 0 : dp[i][j - 1];
                    dp[i][j] = Math.max(left, top);
                }

                j++;
            }

            i++;
        }

        return dp[len1 - 1][len2 - 1];
    }
}

