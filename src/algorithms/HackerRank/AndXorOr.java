package algorithms.HackerRank;

import java.util.*;

/**
 * Given an array  of  distinct elements. Let  and  be the smallest and the next smallest element in the interval where .
 * .
 * where , are the bitwise operators ,  and  respectively.
 * Your task is to find the maximum possible value of .
 * Input Format
 * First line contains integer .
 * Second line contains  integers, representing elements of the array .
 * Constraints
 *
 *
 * Output Format
 * Print the value of maximum possible value of .
 * Sample Input
 * 5
 * 9 6 3 5 2
 * Sample Output
 * 15
 * Explanation
 * Consider the interval  the result will be maximum.
 */
public class AndXorOr {

    static int andXorOr(int[] arr) {


        int res = arr[0] ^ arr[1];
        Stack<Integer> stack = new Stack<>();

        for( int num: arr ){

            while( !stack.isEmpty() ){
                res = Math.max( res, stack.peek() ^ num );
                if( stack.peek() > num ){
                    stack.pop();
                }else break;

            }

            stack.push(num);
        }



        return res;
    }

    public static void main(String[] args) {

        System.out.println(andXorOr(new int[]{9, 6, 3, 5, 2}));
    }
}

