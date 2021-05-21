package Algorithms.LeetCode.Weekly241;

public class RearrangeSticks {
   public static void main(String[] args) {
      RearrangeSticks o = new RearrangeSticks();
      System.out.println(o.rearrangeSticks(20, 11));
   }

   int[][] dp = new int[1001][1001];
   int mod = (int) 1e9 + 7;

   public int rearrangeSticks(int n, int k) {
      if (n == k)
         return 1;
      if (k == 0)
         return 0;
      if (dp[n][k] == 0) {
         dp[n][k] = add(rearrangeSticks(n - 1, k - 1), mul(n - 1, rearrangeSticks(n - 1, k)));
      }
      return dp[n][k];
   }

   int add(long x, long y) {
      if (x > mod)
         x -= mod;
      if (y > mod)
         y -= mod;
      return (int) ((x + y) % mod);
   }

   int mul(long x, long y) {
      if (x > mod)
         x -= mod;
      if (y > mod)
         y -= mod;
      return (int) ((x * y) % mod);
   }
}
