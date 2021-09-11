package LeetCode;

import java.util.*;

public class GoodSubsets {

    public static void main(String[] args) {
        GoodSubsets o = new GoodSubsets();
        System.out.println(o.numberOfGoodSubsets(new int[]{10, 11, 5, 1, 10, 1, 3, 1, 26, 11, 6, 1, 1, 15, 1, 7, 22, 1, 1, 1, 1, 1, 23, 1, 29, 5, 6, 1, 1, 29, 1, 1, 21, 19, 1, 1, 1, 2, 1, 11, 1, 15, 1, 22, 14, 1, 1, 1, 1, 6, 7, 1, 14, 3, 5, 1, 22, 1, 1, 1, 17, 1, 29, 2, 1, 15, 10, 1, 5, 7, 1, 1, 1, 30, 1, 30, 1, 21, 10, 1, 1, 1, 1, 1, 2, 6, 5, 7, 3, 1, 1, 19, 29, 1, 7, 13, 14, 1, 5, 26, 19, 11, 1, 1, 1, 1, 1, 1, 1, 1, 22, 15, 1, 1, 13, 1, 17, 1, 1, 1, 13, 6, 1, 10, 1, 1, 17, 1, 1, 3, 14, 7, 17, 1, 13, 1, 1, 1, 1, 1, 11, 1, 1, 6, 1, 1, 1, 1, 1, 2, 1, 30, 2, 26, 1, 1, 14, 1, 26, 29, 30, 1, 13, 21, 1, 1, 14, 21, 1, 23, 1, 15, 23, 21, 1, 30, 19, 19, 1, 10, 23, 3, 3, 17, 22, 2, 26, 1, 11, 1, 23, 1, 1, 1, 15, 1, 1, 13, 1, 1}));
        System.out.println(o.numberOfGoodSubsets(new int[]{1, 2, 3, 5, 6, 7, 10, 11, 13, 14, 15, 17, 19, 21, 22, 23, 26, 29, 30, 1, 2, 3, 5, 6, 7, 10, 11, 13, 14, 15, 17, 19, 21, 22}));
        System.out.println(o.numberOfGoodSubsets(new int[]{1, 2, 3, 4}));
    }
    static int mod = (int) 1e9 + 7;
    static int[] map = new int[31];

    static {
        int[] prime = new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        for (int i = 2; i <= 30; ++i) {
            if (0 == i % 4 || 0 == i % 9 || 25 == i) continue;
            int mask = 0;
            for (int j = 0; j < 10; ++j) {
                if (0 == i % prime[j]) mask |= 1 << j;
            }
            map[i] = mask;
        }
    }


    public int numberOfGoodSubsets(int[] nums) {
        int n = nums.length, one = 0;
        int[] dp = new int[1024], cnt = new int[31];
        dp[0] = 1;
        for (int i : nums) {
            if (1 == i) one++;
            else if (0 != map[i]) cnt[i]++;
        }
        for (int i = 0; i < 31; ++i) {
            if (0 == cnt[i]) continue;
            for (int j = 0; j < 1024; ++j) {
                if (0 != (j & map[i])) continue;
                dp[j | map[i]] = (int) ((dp[j | map[i]] + dp[j] * (long) cnt[i]) % mod);
            }
        }
        long res = 0;
        for (int i : dp) res = (res + i) % mod;
        res--;
        if (0 != one) res = res * pow(one) % mod;
        return (int) res;
    }
    private long pow(int n) {
        long res = 1, m = 2;
        while (0 != n) {
            if (1 == (n & 1)) res = (res * m) % mod;
            m = m * m % mod;
            n >>= 1;
        }
        return res;
    }
}
