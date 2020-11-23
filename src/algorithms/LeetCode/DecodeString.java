package algorithms.LeetCode;

import java.util.*;
/*Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
**/

public class DecodeString {
	
	public static String decodeString(String s) {
        Stack<Integer> counts = new Stack<>();
        Stack<String> str = new Stack<>();
        String curr = "";
        int index = 0;
        
        while( index < s.length() ){
			if( Character.isDigit( s.charAt(index) ) ){
				int count = 0;
				
				while( Character.isDigit( s.charAt(index) )){
					count = 10 * count + ( s.charAt(index) - '0' );
					index++;
				}
				counts.push( count);
				
			}else if( s.charAt(index) == '[' ){
				
				str.push( curr );
				curr = "";
				index += 1;
				
			} else if( s.charAt(index) == ']' ){
				StringBuilder temp = new StringBuilder( str.pop() );
				int count = counts.pop();
				
				for( int i = 0; i < count; i++ ){
					temp.append( curr );
				}

				curr = temp.toString();
				index++;
				
			} else{
				curr += s.charAt( index );
				index++;
			}
		}
		
		return curr;
        
	}

	public static void main (String[] args) {
		
		System.out.println( decodeString(" 3[a]2[bc] "));
	}
}

