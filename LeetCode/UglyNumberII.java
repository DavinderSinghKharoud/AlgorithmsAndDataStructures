package Algorithms.LeetCode;

import java.util.*;

public class UglyNumberII {

    public static void main(String[] args) {
        UglyNumberII o = new UglyNumberII();
        System.out.println(o.nthUglyNumber2(1000));
    }

    public int nthUglyNumber(int n) {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add(1L);
        while (n-- > 0) {
            long curr = pq.poll();
            if (n == 0)
                return (int) curr;
            for (int i : new int[]{2, 3, 5}) {
                long mul = curr * i;
                if (!pq.contains(mul))
                    pq.add(mul);
            }
        }
        return -1;
    }

    public int nthUglyNumber2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int a = 1, b = 1, c = 1;

        for (int i = 2; i <= n; i++) {
            int first = dp[a] * 2, second = dp[b] * 3, third = dp[c] * 5;
            int min = Math.min(first, Math.min(second, third));
            if (min != dp[i - 1]) {
                dp[i] = min;
            } else i--;

            if (min == first)
                a++;
            else if (min == second)
                b++;
            else
                c++;
        }
        return dp[n];
    }
}
