package Preparation;

/**
 * Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.
 * <p>
 * A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: str1 = "abac", str2 = "cab"
 * Output: "cabac"
 * Explanation:
 * str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
 * str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
 * The answer provided is the shortest such string that satisfies these properties.
 * Example 2:
 * <p>
 * Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
 * Output: "aaaaaaaa"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= str1.length, str2.length <= 1000
 * str1 and str2 consist of lowercase English letters.
 */

import java.util.*;

public class ShortestCommonSuperSequence {
    String[][] dp;

    public String shortestCommonSupersequence(String str1, String str2) {
        dp = new String[str1.length() + 1][str2.length() + 1];

        for (String[] state : dp) {
            Arrays.fill(state, "");
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + str1.charAt(i - 1);
                } else {
                    if (dp[i - 1][j].length() > dp[i][j - 1].length()) {
                        dp[i][j] = dp[i - 1][j];
                    } else dp[i][j] = dp[i][j - 1];
                }
            }
        }

        String lcs = dp[str1.length()][str2.length()];
        StringBuilder sbr = new StringBuilder();
        System.out.println(lcs);
        int start1 = 0, start2 = 0;
        for (int i = 0; i < lcs.length(); i++) {
            char c = lcs.charAt(i);
            while (start1 < str1.length() && str1.charAt(start1) != c) {
                sbr.append(str1.charAt(start1));
                start1++;
            }
            while (start2 < str2.length() && str2.charAt(start2) != c) {
                sbr.append(str2.charAt(start2));
                start2++;
            }
            sbr.append(c);
            start1++;
            start2++;
        }
        while (start1 < str1.length()) {
            sbr.append(str1.charAt(start1++));
        }
        while (start2 < str2.length()) {
            sbr.append(str2.charAt(start2++));
        }
        return sbr.toString();
    }
}
