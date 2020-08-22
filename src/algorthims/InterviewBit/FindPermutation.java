package algorthims.InterviewBit;

import java.util.*;

/**
 * Given a positive integer n and a string s consisting only of letters D or I, you have to find any permutation of first n positive integer that satisfy the given input string.
 *
 * D means the next number is smaller, while I means the next number is greater.
 *
 * Notes
 *
 * Length of given string s will always equal to n - 1
 * Your solution should run in linear time and space.
 * Example :
 *
 * Input 1:
 *
 * n = 3
 *
 * s = ID
 *
 * Return: [1, 3, 2]
 */

public class FindPermutation {

    public static ArrayList<Integer> findPerm(final String str, int n) {
		ArrayList<Integer> res = new ArrayList<>();
		
		Stack<Integer> stack = new Stack<>();
		
		for( int index = 0; index < str.length(); index++ ){
			stack.push( index + 1);
			
			if( str.charAt(index) == 'I' ){
				
				while( !stack.isEmpty() ){
					res.add( stack.pop() );
				}
			}
		}
		
		stack.push(n);
		
		while( !stack.isEmpty() ){
			res.add(stack.pop());
		}
		
		return res;
    }
    public static void main(String[] args) {

		System.out.println( findPerm2("IDDDID", 7));
    }
    
    public static ArrayList<Integer> findPerm2(final String str, int n) {
		ArrayList<Integer> res = new ArrayList<>();
		
		int start = 1, end = 0;
		
		for( int index = 0; index < str.length(); index++ ){
			
			if( str.charAt(index) == 'I' ){
				
				for( int count = start; count > end; count-- ){
					res.add( count );
				}
				
				end = start;
			}
			
			start++;
			
		}
		 
		for( int count = start; count > end; count-- ){
					res.add( count );
		}
		
		return res;
	}
}
