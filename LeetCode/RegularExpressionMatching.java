/*
 Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:

s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.
Example 1:

Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
Example 5:

Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
 */


public class RegularExpressionMatching {
	
	public static boolean isMatch(String s, String p) {
        
        if( p.length() == 0 ) return s.length() == 0;
        
        boolean firstMatch = ( s.length() > 0 && ( s.charAt(0) == p.charAt(0) || p.charAt(0) == '.' )  );
        
        if( p.length() >= 2 && p.charAt(1) == '*' ){
			
			return isMatch( s, p.substring(2) ) || ( firstMatch && isMatch( s.substring(1) , p ) );
		}else{
			return firstMatch && isMatch( s.substring(1), p.substring(1) );
		}
    }
    
	public static void main (String[] args) {
		
		System.out.println( isMatch2( "ab", ".*"));
		
	}

	//O( sp ) time complexity and space complexity
	public static boolean isMatch2(String s, String p) {
		
		int len1 = s.length();
		int len2 = p.length();
		
		boolean[][] check = new boolean[ len1 + 1 ][ len2 + 1 ];
		
		check[0][0] = true;
		
		for( int i = 1; i< check[0].length; i++ ){
			if( p.charAt( i - 1) == '*' ){
				check[0][i] = check[0][i - 2];
			}
		}
		
		for( int i = 1; i<check.length; i++ ){
			for( int j  = 1; j<check[0].length; j++ ){

				if( p.charAt( j - 1 ) == '.' || p.charAt( j - 1 ) == s.charAt( i - 1 ) ){
					check[i][j] = check[i - 1][j - 1];
				}
				else if( p.charAt(j - 1) == '*' ){

					check[i][j] = check[i][ j - 2 ];

					if( p.charAt( j - 2 ) == '.' || p.charAt( j - 2 ) == s.charAt( i - 1 ) ){
						check[i][j] = check[i][j] || check[i - 1][j];
					}
				}
				else {
					check[i][j] = false;
				}
			}
		}
		
		return check[ len1 ][ len2 ];	
	}
}







