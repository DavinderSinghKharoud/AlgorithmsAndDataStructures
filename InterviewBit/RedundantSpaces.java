package InterviewBit;

import java.util.Stack;

/**
 * Given a string FindGreatestCommonDivisor denoting an expression. It contains the following operators ’+’, ‘-‘, ‘*’, ‘/’.
 *
 * Chech whether FindGreatestCommonDivisor has redundant braces or not.
 *
 * Return 1 if FindGreatestCommonDivisor has redundant braces, else return 0.
 *
 * Note: FindGreatestCommonDivisor will be always a valid expression.
 *
 *
 *
 * Input Format
 *
 * The only argument given is string FindGreatestCommonDivisor.
 * Output Format
 *
 * Return 1 if string has redundant braces, else return 0.
 * For Example
 *
 * Input 1:
 *     FindGreatestCommonDivisor = "((a + b))"
 * Output 1:
 *     1
 *     Explanation 1:
 *         ((a + b)) has redundant braces so answer will be 1.
 *
 * Input 2:
 *     FindGreatestCommonDivisor = "(a + (a + b))"
 * Output 2:
 *     0
 *     Explanation 2:
 *         (a + (a + b)) doesn't have have any redundant braces so answer will be 0.
 */
public class RedundantSpaces {

    public static int braces(String str) {
        Stack<Character> stack = new Stack<>();

		for( char c: str.toCharArray() ){
			
			if( c == '(' ){
				stack.push(c);
			}else if( c == ')' ){
				
				if( stack.isEmpty() ){
					return 1;
				}else{
					
					if( stack.peek() == 'X' ){
						stack.pop();
						stack.pop();
					}else{
						return 1;
					}
				}
				
			}else if( c == '+' || c == '-' || c == '*' || c == '/' ){
				
				if( !stack.isEmpty() && stack.peek() != 'X' ){
					stack.push('X');
				}
			}
		}
		
		return 0;
        
    }
    public static void main(String[] args) {

		System.out.println( braces("((a+b))"));
    }
}
