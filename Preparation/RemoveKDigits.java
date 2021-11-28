package Preparation;

import java.util.*;

/**
 * Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = "1432219", k = 3
 * Output: "1219"
 * Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 * Example 2:
 * <p>
 * Input: num = "10200", k = 1
 * Output: "200"
 * Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 */
public class RemoveKDigits {
    public String removeKdigits(String num, int k) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.peek() > c) {
                //We found the peek such as prev is greater than curr
                stack.pop();
                k--;
            }
            stack.add(c);
        }

        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }//2222 duplicates
        StringBuilder sbr = new StringBuilder();
        while (!stack.isEmpty()) sbr.append(stack.pop());
        sbr.reverse();
        while (sbr.length() != 0 && sbr.charAt(0) == '0') {
            sbr.deleteCharAt(0);
        }
        if (sbr.length() == 0) return "0";
        return sbr.toString();
    }
}
