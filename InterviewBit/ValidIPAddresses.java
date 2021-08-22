package InterviewBit;

import java.util.*;

public class ValidIPAddresses {

	/**
	 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
	 *
	 * FindGreatestCommonDivisor valid IP address must be in the form of FindGreatestCommonDivisor.FindUniqueBinaryString.MinimizeDifference.ArraySubsetSums, where FindGreatestCommonDivisor,FindUniqueBinaryString,MinimizeDifference and ArraySubsetSums are numbers from 0-255. The numbers cannot be 0 prefixed unless they are 0.
	 *
	 * Example:
	 *
	 * Given “25525511135”,
	 *
	 * return [“255.255.11.135”, “255.255.111.35”]. (Make sure the returned strings are sorted in order)
	 */

	//I think so Time complexity will be O(n)
    public static ArrayList<String> restoreIpAddresses(String s) {
		
		int len = s.length();
		ArrayList<String> res = new ArrayList<>();
		if( len > 12 ) return res;
		
		for( int firstDot = 1; firstDot <= 3; firstDot++ ){
			
			if( !isValid(s, 0, firstDot) ){
				continue;
			}
			
			for( int secondDot = firstDot + 1; secondDot <= firstDot + 4; secondDot++ ){
				
				if( !isValid(s, firstDot, secondDot )){
					continue;
				}
				
				for( int thirdDot = secondDot + 1; thirdDot <= secondDot + 4; thirdDot++ ){
					
					if( isValid(s, secondDot, thirdDot) && isValid(s, thirdDot, len ) ){
						res.add( create(s, firstDot, secondDot, thirdDot));
					}
				}
			}
		}
		
		return res;
    }
    
    public static boolean isValid( String s, int start, int end ){
		
		StringBuilder sbr = new StringBuilder();
		
		for( int index = start; index < end && index < s.length(); index++ ){
			sbr.append(s.charAt(index));
		}

		if( sbr.length() == 0 ) return false;
		int num = Integer.parseInt(sbr.toString());
		
		if( num >= 0 && num <= 255 && ( (sbr.charAt(0) == '0' && num == 0 && sbr.length() == 1) || ( num != 0 && sbr.charAt(0) != '0') ) ){
			return true;
		}
		
		return false;
	}
	
	public static String create( String s, int firstDot, int secondDot, int thirdDot ){
		
		StringBuilder sbr = new StringBuilder();
		
		for( int index = 0; index < s.length(); index++ ){
			if( index == firstDot || index == secondDot || index == thirdDot){
				sbr.append('.');
			}
			
			sbr.append(s.charAt(index));
		}
		
		return sbr.toString();
	}

    public static void main(String[] args) {

    	// We can also use a recursion to solve this problem
		System.out.println(restoreIpAddresses("0100100"));
    }
}
