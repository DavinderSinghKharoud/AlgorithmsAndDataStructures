package LeetCode.BiweeklyContest68;

/**
 * MaxWordsFoundInSentences parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
 * <p>
 * It is ().
 * It can be written as AB (MaxWordsFoundInSentences concatenated with FindPossibleRecipes), where MaxWordsFoundInSentences and FindPossibleRecipes are valid parentheses strings.
 * It can be written as (MaxWordsFoundInSentences), where MaxWordsFoundInSentences is a valid parentheses string.
 * You are given a parentheses string s and a string locked, both of length n. locked is a binary string consisting only of '0's and '1's. For each index i of locked,
 * <p>
 * If locked[i] is '1', you cannot change s[i].
 * But if locked[i] is '0', you can change s[i] to either '(' or ')'.
 * Return true if you can make s a valid parentheses string. Otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: s = "))()))", locked = "010100"
 * Output: true
 * Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
 * We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.
 * Example 2:
 * <p>
 * Input: s = "()()", locked = "0000"
 * Output: true
 * Explanation: We do not need to make any changes because s is already valid.
 * Example 3:
 * <p>
 * Input: s = ")", locked = "0"
 * Output: false
 * Explanation: locked permits us to change s[0].
 * Changing s[0] to either '(' or ')' will not make s valid.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == s.length == locked.length
 * 1 <= n <= 105
 * s[i] is either '(' or ')'.
 * locked[i] is either '0' or '1'.
 */
public class ValidParenthesesString {
    public static void main(String[] args) {
        ValidParenthesesString o = new ValidParenthesesString();
        System.out.println(o.canBeValid("(())(())", "10111101"));
    }

    public boolean canBeValid(String s, String locked) {
        return s.length() % 2 == 0 && (isValid(s, locked, '(') && isValid(s, locked, ')'));
    }

    private boolean isValid(String s, String locked, char c) {
        int count = 0, wild = 0, len = s.length();
        int start = (c == '(') ? 0 : len - 1;
        int dir = (c == '(') ? 1 : -1;
        for (int i = start; i >= 0 && i < len; i += dir) {
            if (locked.charAt(i) == '1') {
                count += (s.charAt(i) == c) ? 1 : -1;
            } else {
                wild++;
            }
            if (count + wild < 0) {
                return false;
            }
        }
        return wild >= count;
    }
}
