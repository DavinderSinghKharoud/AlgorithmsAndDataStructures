package LeetCode;

import java.util.*;

public class DistinctSubsequencesII {

    public static void main(String[] args) {
        DistinctSubsequencesII o = new DistinctSubsequencesII();
        System.out.println(o.distinctSubseqII("aaa"));
    }

    int a = 'a';
    int mod = (int) 1e9 + 7;

    public int distinctSubseqII(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        int[] last = new int[26];
        Arrays.fill(last, -1);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            char c = s.charAt(i - 1);
            int sum;
            if (last[c - a] == -1) {
                sum = sum(dp[i - 1], 1);
            } else {
                // repeated
                sum = subtract(dp[i - 1], dp[last[c - a] - 1]); // one for "" empty space
            }
            last[c - a] = i;
            dp[i] = sum(dp[i - 1], sum);
        }
        return dp[len];
    }

    int subtract(int a, int b) {
        int sum = a - b;
        while (sum < 0)
            sum += mod;
        return sum % mod;
    }

    int sum(int a, int b) {
        if (a > mod)
            a -= mod;
        if (b > mod)
            b -= mod;
        int sum = (a + b) % mod;
        if (sum < 0)
            sum += mod;
        return sum;
    }
}
