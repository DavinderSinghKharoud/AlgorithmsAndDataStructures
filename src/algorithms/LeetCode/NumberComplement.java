package algorithms.LeetCode;

/**
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary representation.
 *
 * Example 1:
 * Input: 5
 * Output: 2
 * Explanation: The binary representation of 5 is 101
 *  (no leading zero bits), and its complement is 010.
 *   So you need to output 2.
 */
public class NumberComplement {


    public static int findComplement(int num) {
        //Total bits is 3, using log(5)/log(2)+1, neglect decimal value .
        int totalBits = (int) Math.floor( Math.log(num) / Math.log(2) ) + 1;

        //which describes maximum decimal number formed using n number of total bits, here total bits is 3, which means maximum number formed will be 7(111)
        //Finally, XOR of maximum number and given number will produce result. [7(111) ^ 5(101) = 2(010)]
        return (int)( Math.pow(2, totalBits) - 1) ^ num;
    }
    public static void main(String[] args) {
        System.out.print( findComplement(5));
    }
}
