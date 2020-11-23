package algorithms.LeetCode;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 *
 * Example 1:
 *
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 */
public class DecodeWays {

	//O(n) time complexity and space complexity
	 public static int numDecodings(String s) {

	 	int dp[] = new int[s.length() + 1];

	 	dp[0] = 1;
	 	dp[1] = (s.charAt(0) == '0') ? 0: 1;

	 	for( int i = 2; i<=s.length(); i++ ){
	 		int oneDigit = Integer.parseInt( s.substring( i - 1, i));
	 		int twoDigit = Integer.parseInt( s.substring( i - 2, i));

	 		if( oneDigit >= 1 ){
	 			dp[i] += dp[i - 1];
			}

	 		if( twoDigit >= 10 && twoDigit <= 26 ){
	 			dp[i] += dp[ i - 2 ];
			}
		}
	 	return dp[ s.length()];
	}

	
	public static int helper( String s, int start, int end ){
	    

		if( start >= end -1  ){
			return 1;
		}

		int count1 = ( s.startsWith("0", start) )? 0: helper( s, start + 1, end );
		int count2 = 0;
		if( start + 2 <= end && Integer.parseInt( s.substring(start, start + 2 )) <= 26 ){

			count2 = helper(s, start + 2, end );
		}

		return count1 + count2;
	    
	}
	public static void main (String[] args) {
		
		System.out.println( numDecodings2("1120"));
	}

	//O(n) time complexity and O(1) space complexity
	public static int numDecodings2(String s) {

	 	if( s.length() == 0 || s.charAt(0) == '0'){
	 		return  0;
		}

	 	int oneStepBack = 1;
	 	int twoStepBack = 1;
	 	int count = 1;

		for (int i = 1; i < s.length(); i++) {
			count = 0;

			if( s.charAt(i) != '0'){
				count += oneStepBack;
			}
			if( s.charAt(i - 1) == '1' || ( s.charAt(i - 1) == '2' && s.charAt(i) < '7') ){

				count += twoStepBack;
			}

			twoStepBack = oneStepBack;
			oneStepBack = count;
		}

		return count;
	}

	public static int numDecodings3(String s) {

	 	return helper(s, 0, s.length() );
	}
	}

