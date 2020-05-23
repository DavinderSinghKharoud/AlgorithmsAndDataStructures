/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
 */

import java.util.*;
public class LongestValidParentheses {
	
	//O(n) time complexity and space complexity
	public static int longestValidParentheses(String s) {
       // if( s.length() == 0 ) return 0;
        int max_len = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        
        for( int i = 0; i< s.length(); i++ ){
			char c = s.charAt(i);
			if( c == '(' ){
				stack.push( i );
			}else{
				stack.pop();
				if( stack.isEmpty() ){
					stack.push(i);
				}else{
					max_len = Math.max( max_len, i - stack.peek() );
				}
			}
		}
		
		return max_len;
    }
	public static void main (String[] args) {
		System.out.println( longestValidParentheses(""));
	}
}

