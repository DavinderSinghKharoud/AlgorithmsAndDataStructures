package Preparation;

/**
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 *
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 *
 * int isMatch(const char *s, const char *p)
 * Some examples:
 *
 * isMatch("aa","a") → 0
 * isMatch("aa","aa") → 1
 * isMatch("aaa","aa") → 0
 * isMatch("aa", "a*") → 1
 * isMatch("aa", ".*") → 1
 * isMatch("ab", ".*") → 1
 * isMatch("aab", "c*a*b") → 1
 */
public class RegularExpressionMatchII {

    public int isMatch(final String s, final String pattern) {
        int len1 = s.length(), len2 = pattern.length();
        if(len1 == 0 || len2 == 0 || pattern.charAt(0) == '*' ) return 0;
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];
        dp[0][0] = true;

        for(int i = 1; i <= len2; i++){
            if(pattern.charAt(i - 1) == '*') dp[0][i] = dp[0][i - 2];
        }

        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                char cp = pattern.charAt(j - 1);
                char cs = s.charAt(i - 1);
                boolean curr = false;
                if(cp == '.' || cp == cs){
                    curr = dp[i - 1][j - 1];
                }else if(cp == '*'){
                    // System.out.println(i + " " + j);
                    curr = dp[i][j - 2];
                    char patternPrev = pattern.charAt(j - 2);
                    if(patternPrev == '.' || patternPrev == cs){
                        curr |= dp[i - 1][j];
                    }
                }
                dp[i][j] = curr;
            }
        }
        for(int i = 0; i < dp.length;i ++){
            for(int j = 0;j  < dp[0].length; j++){
                // System.out.print(dp[i][j]);
            }
            // System.out.println();
        }
        return (dp[len1][len2])?1: 0;
    }
}
