/*
Initially on a notepad only one character 'A' is present. You can perform two operations on this notepad for each step:

Copy All: You can copy all the characters present on the notepad (partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
 

Given a number n. You have to get exactly n 'A' on the notepad by performing the minimum number of steps permitted. Output the minimum number of steps to get n 'A'.

Example 1:

Input: 3
Output: 3
Explanation:
Intitally, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
 */


public class TwoKeysKeyboard {
	
	public static int minSteps1(int n) {
		int ans = 0,d = 2;
		while ( n > 1 ){
			while ( n % d == 0 ){
				ans += d;
				n /= d;
			}
			d++;
		}

		return ans;
    }
	public static void main (String[] args) {
		
		System.out.println( minSteps2(7) );
	}

	public static int minSteps2(int n) {
		if( n == 1 ) return 0;
		//even
		if( n % 2 == 0 ) return minSteps2( n / 2 ) + 2;

		//odd
		int sqrt = (int) Math.sqrt(n); //because above square root all numbers can be formed from numbers below sqrt
		for( int i = 3; i <= sqrt; i += 2 ){
			if( n % i == 0 ) {
				return minSteps2( n/i ) + i;
			}
		}
		return n;
	}
}

