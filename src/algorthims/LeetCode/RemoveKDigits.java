
/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

Note:

The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
* **/

/**
 * Suppose If we have num 1543, k = 2
 * Traverse through each digit in num,
 * if you found, previous digit is greater than the current digit, delete it.
 *
 * DIGITS = 1, 5, 6, 3    K = 2  res = ""
 * current_digit = 1, So, res = 1
 * current_digit = 5, So, res = 15
 * current_digit = 6, So, res = 156
 * current_digit = 3,
 * 		Now, previous digit (6) greater than current digit (3).
 * 		So, del previous digit.
 * 		res = 15  K = 1
 * 		Still previous digit(5) is greater than current digit (3)
 * 		res = 1, K = 0
 * 		Now, K becomes 0
 * 		and add remaining digits to res.
 * 		res = 13
 * So, smallest number is 13.
 *
 * -> From this, we can understand if last digit of
 * result greater than the current_digit, then we need to delete it
 * to get smallest number.
 * -> By using this point, let's develop
 * 	an algorithm to solve this problem.
 */

import java.util.*;
public class RemoveKDigits {
	
	public static String removeKdigits(String num, int k) {
        if( k >= num.length() ){
			return "0";
		}
		
		Stack<Character> stack = new Stack<>();
		
		for( int index = 0; index<num.length(); index++ ){
			char c = num.charAt(index);
			while( k > 0 && !stack.isEmpty() && stack.peek() > c ){
				stack.pop();
				k--;
			}
			
			stack.push( c );
		}
		
		//if there are still some numbers need to remove
		while( k --> 0 ){
			stack.pop();
		}
		
		StringBuilder smallest = new StringBuilder();
		while( !stack.isEmpty() ){
			smallest.append( stack.pop() );
		}
		smallest.reverse();
		//delete the leading zeroes
		while( smallest.length() > 1 && smallest.charAt(0) == '0' ){
			smallest.deleteCharAt(0);
		}
		return smallest.toString();
    }
	public static void main (String[] args) {

		System.out.println( removeKdigits("1432219", 3));
	}
}

