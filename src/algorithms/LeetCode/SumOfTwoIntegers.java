package algorithms.LeetCode;

/**
 * Calculate the sum of two integers a and b, but you are not allowed to use the
 * operator + and -.
 *
 * Example 1:
 *
 * Input: a = 1, b = 2
 * Output: 3
 */
public class SumOfTwoIntegers {

    public static int getSum(int a, int b) {

        while ( b != 0 ){
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;

        }
        return a;

    }

    public static void main(String[] args) {

        System.out.println( getSum(1,2));

    }
}
