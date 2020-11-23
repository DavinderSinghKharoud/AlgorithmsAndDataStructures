package algorithms.LeetCode;

/*
Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
 */
public class FactorialTrailingZeroes {

    public static int trailingZeroes(int n) {
        int num = 0;

        while ( n > 0 ){
            n /= 5;
            num += n;
        }

        return num;
    }

    public static void main(String[] args) {

        System.out.println( trailingZeroes(10));
    }
}
