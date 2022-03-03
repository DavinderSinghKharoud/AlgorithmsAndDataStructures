package Preparation;

/**
 * Additive number is a string whose digits can form additive sequence.
 *
 * FindingThreeDigitEvenNumbers valid additive sequence should contain at least three numbers. Except for the first two numbers, each subsequent number in the sequence must be the sum of the preceding two.
 *
 * Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.
 *
 * Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid.
 *
 *
 *
 * Example 1:
 *
 * Input: "112358"
 * Output: true
 * Explanation: The digits can form an additive sequence: 1, 1, 2, 3, 5, 8.
 *              1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
 * Example 2:
 *
 * Input: "199100199"
 * Output: true
 * Explanation: The additive sequence is: 1, 99, 100, 199.
 *              1 + 99 = 100, 99 + 100 = 199
 */
public class AdditiveNumber {
    public static void main(String[] args) {
        System.out.println(new AdditiveNumber().isAdditiveNumber("199001200"));
    }

    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) return false;
        //try every possiblity
        for (int i = 0; i < num.length(); i++) {
            for (int j = i + 1; j < num.length() - 1; j++) {
                if (helper(num.substring(j + 1), num.substring(0, i + 1), num.substring(i + 1, j + 1))) return true;
            }
        }
        return false;
    }

    boolean helper(String num, String first, String second) {
        if (first.length() > 1 && first.charAt(0) == '0'
                || (second.length() > 1 && second.charAt(0) == '0')) return false;
        if (num.isEmpty()) return true;
        String sum = add(first, second);
        if (num.startsWith(sum)) {
            return helper(num.substring(sum.length()), second, sum);
        }
        return false;
    }

    String add(String a, String b) {
        int len1 = a.length(), len2 = b.length();
        int carry = 0;
        StringBuilder sbr = new StringBuilder();
        int end1 = len1 - 1, end2 = len2 - 1;
        while (end1 >= 0 || end2 >= 0) {
            char ca = (end1 >= 0) ? a.charAt(end1--) : '0';
            char cb = (end2 >= 0) ? b.charAt(end2--) : '0';
            int curr = (ca - '0' + cb - '0' + carry);
            sbr.append(curr % 10);
            carry = curr / 10;
        }
        if (carry > 0) sbr.append(carry);
        return sbr.reverse().toString();
    }
}
