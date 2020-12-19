package Algorithms.InterviewBit;

import java.util.*;
public class ShortestCommonSuperstring {

	//Ok for InterviewBit but for leetcode remove the usage of isEmbedded() function
    //O( n * n ) time complexity and
	public static int solve(String[] arr) {
		
		List<String> lst = new ArrayList<>(Arrays.asList(arr));
		
		while( true ){
			int n = lst.size();
			if( n == 1 ) break;
			
			int maxLength = -1;
			int index1 = 0, index2 = 0;
			String newStr = "";
			
			for( int i = 0; i < n - 1; i++ ){
				for( int j = i + 1; j < n; j++ ){

					String s1 = lst.get(i);
					String s2 = lst.get(j);
					
					String merged = merge( s1, s2 );
					
					int saved = s1.length() + s2.length() - merged.length();
					
					if( saved > maxLength ){
						maxLength = saved;
						index1 = i;
						index2 = j;
						newStr = merged;
					}
			}
			}
			
			String str1 = lst.get(index1);
			String str2 = lst.get(index2);
			lst.remove( str1 );
			lst.remove( str2 );
			lst.add( newStr );
		}
		
		return lst.get(0).length();
    }
    
    public static String merge( String s1, String s2 ){
		int len1 = s1.length();
		int len2 = s2.length();
		
		//abcd
		//cdef
		int overlapped1 = 0, overlapped2 = 0;
		
		//comparing end suffix of str1 and prefix of str2
		for( int len = 1; len1 - len >= 0 && len <= len2; len++ ){
			if( s1.substring( len1 - len ).equals( s2.substring( 0, len ) ) ){
				overlapped1 = len;
			}	
		}
		
		//comparing end suffix of str2 and prefix of str1
		for( int len = 1; len2 - len >= 0  && len <= len1; len++ ){
			if( s2.substring( len2 - len ).equals( s1.substring( 0, len ) ) ){
				overlapped2 = len;
			}
		}

		if( overlapped1 == overlapped2 && overlapped2 == 0 ){
			if( s1.length() > s2.length() ){
				if(isEmbedded( s1, s2 )){
					return s1;
				}
			}else if(isEmbedded( s2, s1 )){
					return s2;
			}else{
				return s1.substring( 0, len1 - overlapped1 ) + s2;
			}
		}
		if( overlapped1 > overlapped2 ){
			return s1.substring( 0, len1 - overlapped1 ) + s2;
		}else{
			return s2.substring( 0, len2 -overlapped2 ) + s1;
		}

	}



	/**
	 * use isEmbedded for interviewBit like
	 *     A = ["abcd", "cdef", "fgh", "de"]
	 *     answer should be "abcdefgh" but for leetcode accepts deabcdefgh or abcdefghde
	 * @param s1
	 * @param s2
	 * @return
	 */
	private static boolean isEmbedded(String s1, String s2) {
    	int len = s2.length();
    	for( int start = 0; start < s1.length() - len + 1; start++ ){
    		if( s1.substring(start, start + len ).equals(s2)){
    			return true;
			}
		}
    	return false;
	}

	public static void main(String[] args) {

		System.out.println( solve( new String[]{
			"abcd", "cdef", "fgh", "de"
		}) );
    }
}
