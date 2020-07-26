package algorthims.InterviewBit;

import java.util.*;
public class WindowString {

	//Time Limit Exceeded But correct
    public static String minWindow(String s, String t) {
		
		int len1 = s.length();
		int len2 = t.length();
		
		if( len2 > len1 ) return "";
		
		int[] tArray = new int[128];
		for( char c: t.toCharArray() ){
			tArray[c]++;
		}
		
		int start = 0; 
		int end = len2 - 1;
		int min = Integer.MAX_VALUE;
		int resStart = -1;
		int resEnd = -1;
		
		char []sArray = s.toCharArray();
		while( end < len1 && start <= end ){
			
			if( isContained( sArray, start, end, tArray) ){

				if( end - start < min ){
					min = end - start;
					resStart = start;
					resEnd = end;
				}
				start++;
				
			}else{
				end++;
			}
			
			
		}
		
		if( resStart == -1 ) return "";
		
		StringBuilder sbr = new StringBuilder();
		for( int index = resStart; index <= resEnd; index++ ){
			sbr.append( sArray[index] );
		}
		
		return sbr.toString();
		
    }
    
    public static boolean isContained( char[] sArray, int start, int end, int[] tArray ){
		
		int[] curr = new int[128];
		for( int index = start; index <= end; index++ ){
			curr[ sArray[index]]++;
			}
		for( int index = 0; index < tArray.length; index++ ){
			if( tArray[index] > curr[index] ){
				return false;
			}
		}
		
		return true;
	}
    public static void main(String[] args) {

		System.out.println( minWindow("w", "o"));
    }
}
