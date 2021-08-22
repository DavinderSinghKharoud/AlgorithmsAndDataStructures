/*
Given a number FindGreatestCommonDivisor, return number of ways you can draw FindGreatestCommonDivisor chords in a circle with 2 x FindGreatestCommonDivisor points such that no 2 chords intersect.

Two ways are different if there exists a chord which is present in one way and not in other.

Return the answer modulo 109 + 7.



Input Format:

The first and the only argument contains the integer FindGreatestCommonDivisor.
Output Format:

Return an integer answering the query as described in the problem statement.
Constraints:

1 <= FindGreatestCommonDivisor <= 1000
Examples:

Input 1:
    FindGreatestCommonDivisor = 1

Output 1:
    1

Explanation 1:
    If points are numbered 1 to 2 in clockwise direction, then different ways to draw chords are:
    {(1-2)} only.
    So, we return 1.

Input 2:
    FindGreatestCommonDivisor = 2

Output 2:
    2

Explanation 2:
    If points are numbered 1 to 4 in clockwise direction, then different ways to draw chords are:
    {(1-2), (3-4)} and {(1-4), (2-3)}.
    So, we return 2.
 */

/*
 Before proceeding to the solution we can see that the recursive solution will be hard to implement so we will proceed to the dynamic programming approach. In this approach, we select a point and a variable point and then make a chord using both points to divide the circle into two halves then using dp matrix we will find the number of ways to form rest of the chords in both halves multiply them and add them to the solution of ith column. We will proceed this with same fixed point and different variable point and store the answer in ith column.
 
 Create dp matrix in which ith cell represent the number of ways to form ith points.
Start filling dp matrix from the 4th row (no need to fill odd columns as there answer will be zero).
Find the answer of each row by using dp relations.
If points in any of the half is 0 then add the number of ways of another half in the answer.
Otherwise, multiple numbers of ways in both the half and add to the answer of ith cell.
Store the answer in ith cell.
* f we draw a chord between any two points, can you observe the current set of points getting broken into two smaller sets S_1 and S_2. If we draw a chord from a point in S_1 to a point in S_2, it will surely intersect the chord we’ve just drawn.
So, we can arrive at a recurrence that Ways(n) = sum[i = 0 to n-1] { Ways(i)*Ways(n-i-1) }.
Here we iterate over i, assuming that size of one of the sets is i and size of another set automatically is (n-i-1) since we’ve already used a pair of points and i pair of points in one set.*/

public class IntersectingChordsInaCircle {

	//The pattern gives Catalan numbers 1,1,2,5,14...
 	//So the solution is to find nth catalan number.
	public static int chordCnt(int n) {
		int num = 2 * n;
		int mod = 1000000007;

		//dp containing the sum
		long[] dp = new long[num + 1];
		dp[2] = 1;

		for (int i = 4; i <= num; i += 2) {
			int sum = 0;

			for (int j = 0; j < i; j += 2) {
				//number of points in the first half
				int fh = j;
				//number 0f points in the second half
				int sh = i - j - 2;

				if( fh == 0 ){
					sum += dp[sh];
					sum %= mod;
				}else if( sh == 0 ){
					sum += dp[fh];
					sum %= mod;
				}else{
					sum += ( ( dp[fh] * dp[sh] ) % mod );
					sum %= mod;
				}

			}
			dp[i] = sum;
		}

		return (int)dp[num];
    }
	public static void main (String[] args) {
		
		System.out.println( chordCnt(22) );
	}
}

