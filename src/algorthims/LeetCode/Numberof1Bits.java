package algorthims.LeetCode;

/*Write a function that takes an unsigned integer and return the number of '1' bits it has
(also known as the Hamming weight).
Example 1:

Input: 00000000000000000000000000001011
Output: 3
Explanation: The input binary string 00000000000000000000000000001011 has a total of three '1' bits.
 *
 */
public class Numberof1Bits {
    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {

        int bits = 0;
        int mask = 1; //000000001

        for( int i = 0; i<32; i++ ){
            if( (n&mask) != 0 ){
                bits++;
            }

            mask <<=1;
        }
        return bits;
    }

    public static int hammingWeight1(int n) {

        int sum = 0;

        while ( n != 0 ){
            sum++;
            n = n & n-1;
        }
        return sum;
    }

        public static void main(String[] args) {

        System.out.println( hammingWeight( -3) );
    }
}
