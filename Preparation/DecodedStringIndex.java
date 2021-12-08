package Preparation;

/**
 * You are given an encoded string s. To decode the string to a tape, the encoded string is read one character at a time and the following steps are taken:
 * <p>
 * If the character read is a letter, that letter is written onto the tape.
 * If the character read is a digit d, the entire current tape is repeatedly written d - 1 more times in total.
 * Given an integer k, return the kth letter (1-indexed) in the decoded string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "leet2code3", k = 10
 * Output: "o"
 * Explanation: The decoded string is "leetleetcodeleetleetcodeleetleetcode".
 * The 10th letter in the string is "o".
 * Example 2:
 * <p>
 * Input: s = "ha22", k = 5
 * Output: "h"
 * Explanation: The decoded string is "hahahaha".
 * The 5th letter is "h".
 * Example 3:
 * <p>
 * Input: s = "a2345678999999999999999", k = 1
 * Output: "a"
 * Explanation: The decoded string is "a" repeated 8301530446056247680 times.
 * The 1st letter is "a".
 */
public class DecodedStringIndex {
    public String decodeAtIndex2(String s, int k) {
        long len = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                len *= (c - '0');
            } else len++;
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                len /= (c - '0');
                k %= len;
            } else {
                // System.out.println(k + " " + len);
                if (k % len == 0) return String.valueOf(c);
                len--;
            }
        }
        return null;
    }


    public String decodeAtIndex(String s, int k) {
        return helper(s, k);
    }

    String helper(String s, int k) {
        long len = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                if (++len == k) return String.valueOf(c);
            } else {
                if (len * (c - '0') >= k) {
                    if (k % len == 0) {
                        k = (int) len;
                    } else k = k % (int) len;
                    return helper(s, k);
                }
                len *= (c - '0');
            }
        }
        return null;
    }

}
