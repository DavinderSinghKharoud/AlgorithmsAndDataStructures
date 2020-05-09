
/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Output: true
Example 2:

Input: 14
Output: false**/



public class  ValidPerfectSquare {

	//O(n) time complexity
	 public static boolean isPerfectSquare1(int num) {
        if( num == 1 ){
           return true;
       }
        
        for( int i  = 1; i<=num/2; i++ ){
			
			if( i * i == num ){
				return true;
			}
		}
		
		return false;
    }
	public static void main (String[] args) {

		System.out.println( isPerfectSquare2( 808201 ));
	}

	//O(log(n) ) time complexity
	public static boolean isPerfectSquare2(int num) {

	 	if( num == 1 ){
	 		return true;
		}
	 	long low = 1;
	 	long high = num/2;

	 	while( low <= high ){
	 		long mid = low + ( high - low )/2;
	 		long cur = mid * mid;

	 		if( cur == num ){
	 			return true;
			}else if( cur < num ){
	 			low = mid + 1;
			}else{
	 			high = mid - 1;
			}
		}
		return false;
	}
}

