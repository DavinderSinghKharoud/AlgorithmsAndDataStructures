/*
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
 */

import java.util.*;

public class RemoveInvalidParentheses {

    //O( 2 is to power n ) time complexity and O(N) space complexity
    public static List<String> removeInvalidParentheses(String s) {
        if (s == null || s.length() == 0) {
            List<String> lst = new ArrayList<>();
            lst.add("");
            return lst;
        }

        Set<String> set = new HashSet<>();

        int left = 0;
        int right = 0;

        //get the number of open and closing unbalanced parentheses
        for (char c : s.toCharArray()) {

            if (c == '(') {
                left++;
            } else if (c == ')') {

                if (left == 0) {
                    //closing brace need to be removed
                    right++;
                } else {
                    left--;
                }
            }
        }

        helper(s, 0, left, right, set, new StringBuilder(), 0, 0);

        return new ArrayList<>(set);
    }


    public static void helper(String s, int index, int left_count, int right_count, Set<String> res, StringBuilder builder, int open, int close) {

        if (left_count == 0 && right_count == 0 && index == s.length()) {
            res.add(builder.toString());
            return;
        }

        if (left_count < 0 || right_count < 0 || index >= s.length() || close > open) {
            return;
        }

        char c = s.charAt(index);
        if (c != ')' && c != '(') {

            //add the character
            builder.append(c);
            helper(s, index + 1, left_count, right_count, res, builder, open, close);
            builder.deleteCharAt(builder.length() - 1);
        } else if (c == ')') {
            //skip the character
            helper(s, index + 1, left_count, right_count - 1, res, builder, open, close);

            //add the character
            builder.append(c);
            helper(s, index + 1, left_count, right_count, res, builder, open, close + 1);
            builder.deleteCharAt(builder.length() - 1);
        } else {
            //skip the character
            helper(s, index + 1, left_count - 1, right_count, res, builder, open, close);

            //add the character
            builder.append(c);
            helper(s, index + 1, left_count, right_count, res, builder, open + 1, close);
            builder.deleteCharAt(builder.length() - 1);
        }

    }

    public static void main(String[] args) {

        System.out.println(removeInvalidParentheses(")("));
    }
}

