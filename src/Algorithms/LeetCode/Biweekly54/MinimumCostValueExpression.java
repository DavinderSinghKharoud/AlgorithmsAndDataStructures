package Algorithms.LeetCode.Biweekly54;

import java.util.*;

public class MinimumCostValueExpression {
    public static void main(String[] args) {
        MinimumCostValueExpression o = new MinimumCostValueExpression();
        System.out.println(o.minOperationsToFlip("(((0)&1&((0&0))))"));
    }

    int[] adj;
    String s;

    public int minOperationsToFlip(String expression) {
        int len = expression.length();
        adj = new int[len];
        s = expression;

        // Find all the adjacent parenthesis
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                stack.add(i);
            } else if (c == ')') {
                adj[i] = stack.pop();
            }
        }

        return dfs(0, len - 1).minOpe;
    }

    Node dfs(int start, int end) {
        if (start == end)
            return new Node(s.charAt(start), 1);
        char endC = s.charAt(end);
        Node first, second;
        char oper;
        if (endC == '0' || endC == '1') {
            first = dfs(start, end - 2);
            second = dfs(end, end);
            oper = s.charAt(end - 1);
        } else {
            if (start == adj[end])// If it is enclosing (.....)
                return dfs(start + 1, end - 1);
            first = dfs(start, adj[end] - 2);
            second = dfs(adj[end] + 1, end - 1);
            oper = s.charAt(adj[end] - 1);
        }

        int val1 = (first.prev - '0'), val2 = (second.prev - '0');
        int sum = val1 + val2;
        if (oper == '|') {
            int ans;
            if (sum == 1) {
                // We need to change only one of value
                ans = 1;
            } else if (sum == 0) {
                // We need to change both, so take the min of one of them and add 1
                ans = Math.min(first.minOpe, second.minOpe);
            } else {
                ans = Math.min(first.minOpe, second.minOpe) + 1;
            }
            return new Node((char) ((val1 | val2) + '0'), ans);
        } else {
            // For And
            int ans;
            if (sum == 1) {
                // We need to change only one of value
                ans = 1;
            } else if (sum == 0) {
                // We need to change both, so take the min of one of them and add 1
                ans = Math.min(first.minOpe, second.minOpe) + 1;
            } else {
                // We only need to change one of the value 1 & 1 == > 1 & 0 ==> 0
                ans = Math.min(first.minOpe, second.minOpe);
            }
            return new Node((char) ((val1 & val2) + '0'), ans);
        }
    }

    static class Node {
        char prev;// Store the previous character
        int minOpe;// Min number of operations to change value

        public Node(char p, int o) {
            prev = p;
            minOpe = o;
        }
    }
}
