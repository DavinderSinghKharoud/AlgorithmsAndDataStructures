package algorithms.InterviewBit;

/**
 * Given a positive integer which fits in a 32 bit signed integer, find if it can be expressed as A^P where P > 1 and A > 0. A and P both should be integers.
 *
 * Example
 *
 * Input : 4
 * Output : True
 * as 2^2 = 4. 
 */
public class PowerOfTwoIntegers {

    public static int isPower(int n) {

        int limit = (int) Math.sqrt(n);

        boolean isEven = ( n % 2 == 0 );
        for (int num = (isEven)?2:3 ; num <= limit; num+=2) {
            long curr = num;
            while( curr <= n ){
                if( curr == n ) return num;
                curr *= num;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        System.out.println(isPower(16));
    }
}
