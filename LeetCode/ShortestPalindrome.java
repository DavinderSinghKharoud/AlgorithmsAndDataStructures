package LeetCode;

import java.util.Arrays;

/**
 * You are given a string s. You can convert s to a palindrome by adding characters in front of it.
 * <p>
 * Return the shortest palindrome you can find by performing this transformation.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "aacecaaa"
 * Output: "aaacecaaa"
 * Example 2:
 * <p>
 * Input: s = "abcd"
 * Output: "dcbabcd"
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= s.length <= 5 * 104
 * s consists of lowercase English letters only.
 */
public class ShortestPalindrome {
    public static void main(String[] args) {
        System.out.println(new ShortestPalindrome().shortestPalindrome("ababbbabbaba"));
//        System.out.println(new ShortestPalindrome().shortestPalindrome("acab"));
    }

    public String shortestPalindrome(String s) {
        int len = s.length();
        String modified = s + "*" + (new StringBuilder(s).reverse().toString());
        int[] ps = new int[modified.length()];

        int i = 0, j = 1;
        while (j < ps.length) {
            char c1 = modified.charAt(i), c2 = modified.charAt(j);
            if (c1 == c2) {
                ps[j++] = i + 1;
                i++;
            } else {
                if (i == 0) j++;
                i = (i - 1 < 0) ? 0 : ps[i - 1];
            }
        }

        int maxEqual = len - ps[ps.length - 1];
        StringBuilder sbr = new StringBuilder();
        for (int count = 1; count <= maxEqual; count++) {
            sbr.append(s.charAt(len - count));
        }
        sbr.append(s);
        return sbr.toString();
    }
}
