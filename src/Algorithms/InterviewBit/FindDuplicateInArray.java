package Algorithms.InterviewBit;

import java.util.*;

/**
 * Given a read only array of n + 1 integers between 1 and n, find one number that repeats in linear time using less than O(n) space and traversing the stream sequentially O(1) times.
 *
 * Sample Input:
 *
 * [3 4 1 4 1]
 * Sample Output:
 *
 * 1
 * If there are multiple possible answers ( like in the sample case above ), output any one.
 *
 * If there is no duplicate, output -1
 */
public class FindDuplicateInArray {

    public static int repeatedNumber(final List<Integer> lst) {
		
		int len = lst.size();
		
		if( len <= 1 ){ return -1;}
		
		int slow = lst.get(0);
		int fast = lst.get(lst.get(0));
		
		while( fast != slow ){ //It means there is a cycle
			slow = lst.get(slow);
			fast = lst.get( lst.get(fast) );
		}
		
		//Loop to find entry point to the cycle
		fast = 0;

		while( fast != slow ){
			slow = lst.get(slow);
			fast = lst.get(fast);
		}

		return slow;
		
    }
    public static void main(String[] args) {
		System.out.println( repeatedNumber(Arrays.asList(3, 4, 1, 4, 1)));
    }
}
