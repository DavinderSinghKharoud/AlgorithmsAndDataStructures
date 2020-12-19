package Algorithms.InterviewBit;

/**
 * Find if Given number is power of 2 or not.
 * More specifically, find if given number can be expressed as 2^k where k >= 1.
 *
 * Input:
 *
 * number length can be more than 64, which mean number can be greater than 2 ^ 64 (out of long long range)
 * Output:
 *
 * return 1 if the number is a power of 2 else return 0
 *
 * Example:
 *
 * Input : 128
 * Output : 1
 */
public class PowerOf2 {

    public static int power(String s) {

        char[] str = s.toCharArray();
        
        int len = s.length();
        
        if( len == 1 && str[len - 1] == '1' ){
			// if the input is 1 return 0
			return 0;
		}
		
		while( len != 1 || str[len - 1] != '1' ){ //we will divide the number until it gets reduced to 1, if we successfully able to reduce the number to 1 it means input is a power of two
			
			if( (str[len - 1] - '0') % 2 == 1 ){
				return 0;
			}
			
			str = divideByTwo( str );
			len = str.length;
		}
		
		return 1;
    }
    
    public static char[] divideByTwo( char[] s ){
		
		StringBuilder sbr = new StringBuilder();
		
		int carry = 0;

        for (char c : s) {
            int curr = carry * 10 + Character.getNumericValue(c);

            sbr.append(curr / 2);
            carry = curr % 2;
        }
		
		//Remove the trailing zeroes
		
		for( int index = 0; index < sbr.length(); index++ ){
			if( sbr.charAt(index) != '0' ){
				return sbr.substring(index).toCharArray();
			}
		}
		
		return sbr.toString().toCharArray();
	}
    public static void main(String[] args) {

        //We can use log or BitWise to check if number is power of two only when number permits up to long values
        //as (n) & (n - 1) == 0 or
        //counting number of 1's if equal to 1 means it is a power of 2 else not
        //ex: = 16( 10000) : count of 1's = 1

		System.out.println( power("17"));

    }
}
