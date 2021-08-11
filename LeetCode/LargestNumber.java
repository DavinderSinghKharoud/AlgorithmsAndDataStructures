package LeetCode;

import java.util.*;

public class LargestNumber {
        
	public static String largestNumber(int[] nums) {
		String[] str = new String[nums.length];
		
		for( int i = 0; i<nums.length; i++ ){
		    str[i] = String.valueOf( nums[i] );
		}
		
		Arrays.sort( str, (o1, o2) -> {
		    String order1 = o1 + o2;
		    String order2 = o2 + o1;

		    //we are comparing ASCII values as for 303 and 330,
			//first step 3 are equal at starting 0 index
			//second step we found 0 != 3 , so we immediately return teh ascii value difference
		    return order2.compareTo( order1 );
		    
		});
		
		//if the largest number is "0", the entire number is zero
		if( str[0].equals("0") ){
		    return "0";
		}
		
		String largestNumberStr = new String();
		
		for( String s: str ){
		    largestNumberStr += s;
		}
		
		return largestNumberStr;
	}
	
	public static void main (String[] args) {
		
		System.out.println( largestNumber(new int[]{
		    3,30,34,5,9
		}));
	}
}

