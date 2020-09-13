package algorthims.InterviewBit;

import java.util.*;
public class GenerateAllParentheses {

    public static int isValid(String str) {
		
		int len = str.length();
		Map<Character,Character> map = new HashMap<>();
		
		map.put(']', '[');
		map.put(')', '(');
		map.put('}', '{');
		
		Stack<Character> stack = new Stack<>();
		
		for( int index = 0; index < len; index++ ){
			char c = str.charAt(index);
			
			if( c == '[' || c == '(' || c == '{' ){
				stack.push(c);
			}else{
				
				if( stack.isEmpty() || map.get(c) != stack.peek()) return 0;

				
				stack.pop();
			}
		}
		
		return ( stack.isEmpty() ) ? 1: 0;
    }
    public static void main(String[] args) {

		System.out.println( isValid("(({))"));
    }
}
