package LeetCode.WeeklyContest272;

import java.util.*;

public class NumberOfSmoothDescent {
   public static void main(String[] args) {
      NumberOfSmoothDescent o = new NumberOfSmoothDescent();
      System.out.println(o.getDescentPeriods(new int[]{
              5, 4, 3, 2, 1, 6, 5
      }));
      System.out.println(o.getDescentPeriods(new int[]{
              8,6,7,7
      }));
   }

   public long getDescentPeriods(int[] prices) {
      long ans = 1;
      int len = prices.length;
      int[] dp = new int[len];
      Arrays.fill(dp, 1);
      for (int i = 1; i < len; i++) {
         if (prices[i] == prices[i - 1] - 1)
            dp[i] += dp[i - 1];
      }

      for (int i = 1; i < len; i++) {
         ans += dp[i];
      }
      return ans;
   }
}
