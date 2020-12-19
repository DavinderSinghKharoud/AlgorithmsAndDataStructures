package Algorithms.LeetCode;/*
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
	public static int longestValidParentheses1(String s) {
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
		System.out.println( longestValidParentheses1(")(())"));
	}
	
	//O(n) time complexity and O(1) space complexity
	public static int longestValidParentheses2(String s) {
		int left = 0, right = 0;
		int max = 0;
		//left scan
		for( int i = 0; i<s.length(); i++ ){
			char c = s.charAt(i);
			if( c == '(' ){
				left++;
			}else{
				right++;
			}
			
			if( left == right ){
				max = Math.max( max, right * 2 );
			}
			
			if( right > left ){
				left = right = 0;
			}
		}
		
		left = right = 0;
		//right scan 
		for( int index = s.length() - 1; index >= 0; index-- ){
			char c = s.charAt(index);
			
			if( c == '(' ){
				left++;
			}else{
				right++;
			}
			
			if( left == right ){
				max = Math.max( max, right * 2 );
			}
			
			if( left > right ){
				left = right = 0;
			}
		}
		
		return max;
	}
}

