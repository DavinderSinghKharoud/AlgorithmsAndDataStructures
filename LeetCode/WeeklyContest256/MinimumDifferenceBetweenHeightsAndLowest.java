package LeetCode.WeeklyContest256;

import java.util.*;

public class MinimumDifferenceBetweenHeightsAndLowest {
   public static void main(String[] args) {
      MinimumDifferenceBetweenHeightsAndLowest o = new MinimumDifferenceBetweenHeightsAndLowest();
   }

   public int minimumDifference(int[] nums, int k) {
      shuffleSort(nums);
      int start = 0, end = k - 1;
      int ans = Integer.MAX_VALUE;
      for (; end < nums.length; end++) {
         ans = Math.min(ans, nums[end] - nums[start]);
         start++;
      }
      return ans;
   }

   static void shuffleSort(int[] aa) {
      int n = aa.length;
      Random rand = new Random();
      for (int i = 1; i < n; i++) {
         int j = rand.nextInt(i + 1);
         int tmp = aa[i];
         aa[i] = aa[j];
         aa[j] = tmp;
      }
      Arrays.sort(aa);
   }
}
