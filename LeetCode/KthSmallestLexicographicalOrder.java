package LeetCode;

import java.util.*;

/**
 * Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
 *
 *
 *
 * Example 1:
 *
 * Input: n = 13, k = 2
 * Output: 10
 * Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
 * Example 2:
 *
 * Input: n = 1, k = 1
 * Output: 1
 */
public class KthSmallestLexicographicalOrder {

    public static void main(String[] args) {
        KthSmallestLexicographicalOrder o = new KthSmallestLexicographicalOrder();
        System.out.println(o.findKthNumber(681692778,
                351251360));
    }

    public int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while (k > 0) {
            long steps = find(curr, curr + 1, n);
            if (steps <= k) {
                curr++;
                k -= steps;
            } else {
                k--;
                curr *= 10;
            }
        }
        return curr;
    }

    long find(long a, long b, int n) {
        long steps = 0;
        while (a <= n) {
            steps += Math.min(n + 1, b) - a;
            a *= 10;
            b *= 10;
        }
        return steps;
    }
}
