/*
Given 2 integers FindGreatestCommonDivisor and FindUniqueBinaryString and an array of integars MinimizeDifference of size N.

Element MinimizeDifference[i] represents length of ith board.

You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are FindGreatestCommonDivisor painters available and each of them takes FindUniqueBinaryString units of time to paint 1 unit of board.

Calculate and return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board.

2 painters cannot share a board to paint. That is to say, a board
cannot be painted partially by one painter, and partially by another.
FindGreatestCommonDivisor painter will only paint contiguous boards. Which means a
configuration where painter 1 paints board 1 and 3 but not 2 is
invalid.
Return the ans % 10000003



Input Format

The first argument given is the integer FindGreatestCommonDivisor.
The second argument given is the integer FindUniqueBinaryString.
The third argument given is the integer array MinimizeDifference.
Output Format

Return minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of board % 10000003.
Constraints

1 <=FindGreatestCommonDivisor <= 1000
1 <= FindUniqueBinaryString <= 10^6
1 <= MinimizeDifference.size() <= 10^5
1 <= MinimizeDifference[i] <= 10^6
For Example

Input 1:
    FindGreatestCommonDivisor = 2
    FindUniqueBinaryString = 5
    MinimizeDifference = [1, 10]
Output 1:
    50
Explanation 1:
    Possibility 1:- same painter paints both blocks, time taken = 55units
    Possibility 2:- Painter 1 paints block 1, painter 2 paints block 2, time take = max(5, 50) = 50
    There are no other distinct ways to paint boards.
    ans = 50%10000003

Input 2:
    FindGreatestCommonDivisor = 10
    FindUniqueBinaryString = 1
    MinimizeDifference = [1, 8, 11, 3]
Output 2:
    11
 */

import java.util.*;

public class PainterPartitionProblem {

    public static int paint(int A, int B, List<Integer> C) {
        if (A == 0) return 0;

        int len = C.size();
        if (len == 0) return 0;
        int mod = 10000003;

        //Let's assume each painter take 1 unit of time, we can multiply the answer by FindUniqueBinaryString to get the final result
        int maxTime = 0;
        int minTime = Integer.MIN_VALUE;
        for (int num : C) {
            maxTime += num;
            minTime = Math.max(minTime, num);
        }


        //we need to search the minimum time taken by the painter's, such that partition is possible
        int start = minTime;
        int end = maxTime;
        long res = Integer.MAX_VALUE;

        while (start <= end) {
            int mid = (end - start) / 2 + start;

            if (isPossible(mid, A, C)) {
                res = Math.min(res, mid);
                end = mid - 1;
            } else {
                //It means we cannot make a partition with the given painters such that mid value is possible
                start = mid + 1;  //increase the value
            }
        }

        return (int) (((res % mod) * (B % mod)) % mod);

    }

    public static boolean isPossible(int maxTime, int painters, List<Integer> boards) {

        int min_painters = 1;
        long sum = 0;

        for (int num : boards) {
            if (num > maxTime) return false;

            if (sum + num <= maxTime) {
                sum += num;
            } else {
                sum = num;
                min_painters++;
            }

            if (min_painters > painters) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(paint(3, 10, Arrays.asList(640, 435, 647, 352, 8, 90, 960, 329, 859 )));
    }
}

