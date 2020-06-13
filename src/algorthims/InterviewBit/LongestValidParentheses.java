/*
 * LongestValidParentheses.java
 * 
 * Copyright 2020 Davinder singh kharoud <davindersinghkharoud@Davinders-MacBook-Pro.local>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */


import java.util.Stack;

public class LongestValidParentheses {

	//time limit exceeded
	public static int longestValidParentheses(String a) {
		int[] max = new int[1];
		helper( a, 0, 0, 0, max );
		 
		return max[0];
    }
    
    public static void helper( String a, int index, int open, int close, int[] max ){
		
		if( open == close ){
			if( max[0] < 2 * open ){
				max[0] = 2 * open;
			}
		}
		
		if( index >= a.length() || close > open ){
			return;
		}
		
		if( a.charAt(index) == '(' ){
			helper( a, index + 1, open + 1, close, max );
		}else{
			helper( a, index + 1, open, close + 1, max );
		}
		
		helper( a, index + 1, open, close, max );
		
	}
	public static void main (String[] args) {
		
		System.out.println( longestValidParentheses2("())" ));
	}

	public static int longestValidParentheses2(String a) {

		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int max = 0;

		for (int index = 0; index < a.length(); index++) {
			char c = a.charAt(index);
			if( c == '(' ){
				stack.push( index );
			}else{
				stack.pop();
				if( stack.isEmpty() ){
					stack.push( index );
				}else{
					max = Math.max( max, index - stack.peek() );
				}
			}
		}
		return max;
	}
}

