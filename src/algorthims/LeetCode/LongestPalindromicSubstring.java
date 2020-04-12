package algorthims.LeetCode;


public class LongestPalindromicSubstring {

	//Time Limit Exceeded, But works
	public static String longestPalindrome(String s) {

		boolean[][] dp = new boolean[s.length()][s.length()];
		int max = 0;
		int start = 0;
		int end = 0;
		if( s.length() == 0 ) return "";

		for( int i = 0; i<s.length() - 1; i++ ){
		    for( int j = i + 1; j<s.length(); j++ ){
			if( check( s, i, j, dp) && j - i > max){
			   max = j - i;
			   start = i;
			   end = j;
			}
		    }
		    
			
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = start; i<= end; i++ ){
		    sb.append(s.charAt(i));
		}
	    return sb.toString();
	}
	
	
	public static boolean check( String s, int i, int j, boolean[][] dp){
		
		while( i<j ){
		    if (dp[i][j]) return true;
		    if( s.charAt(i) == s.charAt(j) ){
			i++;
			j--;
		    }else{ return false; }
		    
		}
		
		return true;
	}
	
	public static void main (String[] args) {
		    System.out.println( longestPalindrome("babad") );
	}

	public String longestPalindrome2(String s) {
		if( s.length() == 0 ) return "";
		int start,end = 0;
		return "";
	}
}

