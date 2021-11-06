package Preparation;

import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 *
 *
 *
 * Input Format
 *
 * The only argument given is character array ScoreSmallestIndexWithEqualValue.
 * Output Format
 *
 * Return the value of arithmetic expression formed using reverse Polish Notation.
 * For Example
 *
 * Input 1:
 *     ScoreSmallestIndexWithEqualValue =   ["2", "1", "+", "3", "*"]
 * Output 1:
 *     9
 * Explaination 1:
 *     starting from backside:
 *     *: ( )*( )
 *     3: ()*(3)
 *     +: ( () + () )*(3)
 *     1: ( () + (1) )*(3)
 *     2: ( (2) + (1) )*(3)
 *     ((2)+(1))*(3) = 9
 *
 * Input 2:
 *     ScoreSmallestIndexWithEqualValue = ["4", "13", "5", "/", "+"]
 * Output 2:
 *     6
 * Explaination 2:
 *     +: ()+()
 *     /: ()+(() / ())
 *     5: ()+(() / (5))
 *     1: ()+((13) / (5))
 *     4: (4)+((13) / (5))
 *     (4)+((13) / (5)) = 6
 */
public class EvaluateExpression {

    public static void main(String[] args) {
//        System.out.println(new EvaluateExpression().evalRPN(new String[]{"-2", "-1", "2", "+", "-1", "-", "-", "2", "-2", "1", "-", "+", "-", "-2", "-2", "-", "-1", "2", "-2", "-", "-2", "-1", "+", "1", "1", "-", "-1", "+", "1", "*", "*", "2", "+", "*", "-", "-2", "1", "-2", "*", "+", "-2", "*", "1", "*", "-", "*", "*" }));
        System.out.println(new EvaluateExpression().evalRPN(new String[]{"-500", "-10", "/" }));


    }

    public int evalRPN(String[] arr) {
        int len = arr.length;
        Stack<String> stack = new Stack<>();


        for (int i = len - 1; i >= 0; i--) {
            if (isSpecial(arr[i])) {
                stack.add(arr[i]);
            } else {
                //It is a number
                stack.add(arr[i]);
                if (!stack.isEmpty()) {
                    while (stack.size() > 2) {
                        String curr = stack.pop();
                        String prev = stack.pop();
                        if (isSpecial(prev)) {
                            stack.add(prev);
                            stack.add(curr);
                            break;
                        } else {
                            String sign = stack.pop();
                            String val = String.valueOf(getV(sign, getS(curr), getS(prev)));
                            stack.add(val);
                        }
                    }

                }
            }

        }
        return getS(stack.peek());
    }

    int getV(String sign, int prev, int curr) {

        switch (sign) {
            case "+":
                return prev + curr;
            case "-":
                return prev - curr;
            case "*":
                return prev * curr;
            case "/":
                return prev / curr;
        }
        return 0;
    }

    int getS(String s) {
        return Integer.valueOf(s);
    }

    boolean isSpecial(String s) {
        char c = s.charAt(0);
        return c == '+' || (c == '-' && s.length() == 1) ||  c == '*' || c == '/';
    }
}
