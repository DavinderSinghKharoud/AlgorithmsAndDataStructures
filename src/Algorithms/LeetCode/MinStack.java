package Algorithms.LeetCode;

import java.util.Stack;

public class MinStack {

    Stack<int[]> stack = new Stack<>();

    public void push(int x) {
        int min = Math.min(x, getMin());
        stack.push(new int[]{x, min});

    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        if (stack.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return stack.peek()[1];

    }


    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.top());
        System.out.println(minStack.getMin());
    }
}
