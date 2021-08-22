package InterviewBit;

/**
 * Given an integar FindGreatestCommonDivisor.
 *
 * Compute and return the square root of FindGreatestCommonDivisor.
 *
 * If FindGreatestCommonDivisor is not a perfect square, return floor(sqrt(FindGreatestCommonDivisor)).
 *
 * DO NOT USE SQRT FUNCTION FROM STANDARD LIBRARY
 *
 *
 *
 * Input Format
 *
 * The first and only argument given is the integer FindGreatestCommonDivisor.
 * Output Format
 *
 * Return floor(sqrt(FindGreatestCommonDivisor))
 * Constraints
 *
 * 1 <= FindGreatestCommonDivisor <= 10^9
 * For Example
 *
 * Input 1:
 *     FindGreatestCommonDivisor = 11
 * Output 1:
 *     3
 *
 * Input 2:
 *     FindGreatestCommonDivisor = 9
 * Output 2:
 *     3
 */
public class SquareRootOfInteger {

    public static int sqrt(int n) {
		
		if( n == 0 ) return 0;
		if( n == 1 ) return 1;
		int limit = n/2;
		
		int start = 1;
		int end = limit;
		
		while ( start <= end ){
			int mid = start + ( end - start )/2;

			if( mid <= n/mid && (mid + 1) > n/(mid + 1) ){
				return mid;
			}else if( mid < n/mid ){
				start = mid + 1;
			}else{
				end = mid - 1;
			}
		}
		
		return end;
		
    }
    public static void main(String[] args) {

		System.out.println( sqrt(930675566));
    }
}
