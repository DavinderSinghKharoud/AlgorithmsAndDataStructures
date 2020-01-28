package ProblemSolvingHackerRank;

import java.util.HashMap;
import java.util.Stack;

/**
 * A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].
 * <p>
 * Two brackets are considered to be a matched pair if the an opening bracket (i.e., (, [, or
 * {) occurs to the left of a closing bracket (i.e., ), ], or }) of the exact same type.
 * There are three types of matched pairs of brackets: [], {}, and ().
 * <p>
 * A matching pair of brackets is not balanced if the set of brackets it encloses are not matched.
 * For example, {[(])} is not balanced because the contents in between { and } are not balanced.
 * The pair of square brackets encloses a single, unbalanced opening bracket, (, and the pair
 * of parentheses encloses a single, unbalanced closing square bracket, ].
 * <p>
 * By this logic, we say a sequence of brackets is balanced if the following conditions are met:
 * <p>
 * It contains no unmatched brackets.
 * The subset of brackets enclosed within the confines of a matched pair of brackets is also a
 * matched pair of brackets.
 * Given  strings of brackets, determine whether each sequence of brackets is balanced.
 * If a string is balanced, return YES. Otherwise, return NO.
 */
public class BalancedParentheses {
    // Complete the isBalanced function below.
    static String isBalanced(String s) {

        String first = "{[(";
        HashMap<Character, Character> map = new HashMap<>();

        map.put('{', '}');
        map.put('[', ']');
        map.put('(', ')');

        Stack<Character> stack = new Stack<>();
        if ((s.length() % 2) != 0) {
            return "NO";
        }


        for (int index = 0; index < s.length(); index++) {

            char ch = s.charAt(index);
            if (map.containsKey(ch)) {
                stack.push(ch);
            } else {
                if (stack.isEmpty() || map.get(stack.pop()) != ch) {
                    return "NO";
                }

            }

        }

        if (stack.isEmpty()) {
            return "YES";
        } else {
            return "N0";
        }
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("{{}[]}"));
    }
}
