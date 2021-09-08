package LeetCode;

import java.util.*;

public class MinimumRemoveValidParentheses {
    public String minRemoveToMakeValid(String s) {
        int len = s.length();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.add(i);
            } else if (c == ')') {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else stack.add(i);
            }
        }

        Set<Integer> set = new HashSet<>();
        while (!stack.isEmpty()) {
            set.add(stack.pop());
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (!set.contains(i)) ans.append(s.charAt(i));
        }
        return ans.toString();
    }
}
