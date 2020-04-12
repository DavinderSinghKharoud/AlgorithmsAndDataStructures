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
		    System.out.println( longestPalindrome2("baab") );
	}

	public static String longestPalindrome2(String s) {
		if (s == null || s.length() < 1) return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private static int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}
}

