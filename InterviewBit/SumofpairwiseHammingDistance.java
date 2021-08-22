/*
Hamming distance between two non-negative integers is defined as the number of positions at which the corresponding bits are different.

Given an array FindGreatestCommonDivisor of N non-negative integers, find the sum of hamming distances of all pairs of integers in the array. Return the answer modulo 1000000007.



Problem Constraints
1 <= |FindGreatestCommonDivisor| <= 200000

1 <= FindGreatestCommonDivisor[i] <= 109



Input Format
First and only argument is array FindGreatestCommonDivisor.



Output Format
Return one integer, the answer to the problem.



Example Input
Input 1:

 FindGreatestCommonDivisor = [1]
Input 2:

 FindGreatestCommonDivisor = [2, 4, 6]
 */

import java.util.*;

public class SumofpairwiseHammingDistance {

    //Time limit exceeded O(n square )
    public static int hammingDistance1(final List<Integer> lst) {

        int total = 0;

        for (int num1 : lst) {
            for (int num2 : lst) {

                total += find(num1, num2);
            }
        }

        return total;
    }

    public static int find(int num1, int num2) {

        int total = 0;

        for (int count = 0; count < 32; count++) {
            int first = (num1 & 1);
            int second = (num2 & 1);

            total += (first ^ second);

            num1 = num1 >>> 1;
            num2 = num2 >>> 1;
        }

        return total;
    }

    public static void main(String[] args) {

        List<Integer> lst = new ArrayList<>();
        lst.add(1);
        lst.add(2);
        //lst.add( 6 );
        System.out.println(hammingDistance2(lst));
    }

    public static int hammingDistance2(final List<Integer> lst) {

        long total = 0;
        int len = lst.size();
        int mod = 1000000007;

        for (int i = 0; i < 32; i++) {

            long tempCount = 0;

            for (int j = 0; j < len; j++)
                if ( (lst.get(j) & (1 << i) ) == 0)
                    tempCount++;

            total += ( (tempCount * (len - tempCount) * 2) ) % mod;
            total = total % mod;

        }


        return (int) total;
    }

}


