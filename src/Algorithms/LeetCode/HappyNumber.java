package Algorithms.LeetCode;

import java.util.HashMap;

/**
 * Write an algorithm to determine if a number is "happy".
 *
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 *
 * Example:
 *
 * Input: 19
 * Output: true
 * Explanation:
 * 12 + 92 = 82
 * 82 + 22 = 68
 * 62 + 82 = 100
 * 12 + 02 + 02 = 1
 */
public class HappyNumber {

    public static boolean isHappy(int n) {

        HashMap<Integer, Integer> map = new HashMap<>();


        while ( map.get(n) == null){

            if( n == 1){
                return true;
            }
            map.put(n, 1);

            int calculate = n;
            int sum = 0;

            while( calculate != 0){

                int temp = calculate%10;
                int square = temp * temp;

                sum += square;
                calculate = calculate/10;
            }

            n = sum;
        }

        return false;

    }

    public static void main(String[] args) {

        System.out.println( isHappy(19));
    }
}
