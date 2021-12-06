package Preparation;

/**
 * You are given a string s that consists of only digits.
 * <p>
 * Check if we can split s into two or more non-empty substrings such that the numerical values of the substrings are in descending order and the difference between numerical values of every two adjacent substrings is equal to 1.
 * <p>
 * For example, the string s = "0090089" can be split into ["0090", "089"] with numerical values [90,89]. The values are in descending order and adjacent values differ by 1, so this way is valid.
 * Another example, the string s = "001" can be split into ["0", "01"], ["00", "1"], or ["0", "0", "1"]. However all the ways are invalid because they have numerical values [0,1], [0,1], and [0,0,1] respectively, all of which are not in descending order.
 * Return true if it is possible to split s​​​​​​ as described above, or false otherwise.
 * <p>
 * FindingThreeDigitEvenNumbers substring is a contiguous sequence of characters in a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "1234"
 * Output: false
 * Explanation: There is no valid way to split s.
 * Example 2:
 * <p>
 * Input: s = "050043"
 * Output: true
 * Explanation: s can be split into ["05", "004", "3"] with numerical values [5,4,3].
 * The values are in descending order with adjacent values differing by 1.
 * Example 3:
 * <p>
 * Input: s = "9080701"
 * Output: false
 * Explanation: There is no valid way to split s.
 * Example 4:
 * <p>
 * Input: s = "10009998"
 * Output: true
 * Explanation: s can be split into ["100", "099", "98"] with numerical values [100,99,98].
 * The values are in descending order with adjacent values differing by 1.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 20
 * s only consists of digits.
 */
public class SplittingString {
    public boolean splitString(String s) {
        long curr = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            curr = curr * 10 + (s.charAt(i) - '0');
            if (curr >= 10000000000L) break;
            if (helper(s.substring(i + 1), curr)) return true;
        }
        return false;
    }

    boolean helper(String s, long prev) {
        if (s.length() == 0) {
            return true;
        }
        //System.out.println(s + " " + prev);
        long curr = 0;
        for (int i = 0; i < s.length(); i++) {
            curr = curr * 10 + (s.charAt(i) - '0');
            if (curr >= 10000000000L) continue;
            if (curr > prev || (prev - curr != 1)) continue;
            if (helper(s.substring(i + 1), curr)) return true;
        }
        return false;
    }

}
