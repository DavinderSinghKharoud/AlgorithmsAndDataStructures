package Algorithms.LeetCode.Biweekly53;

import java.util.*;

public class MinimizeMaximizaPairSum {
   public static void main(String[] args) {

   }

   public int minPairSum(int[] nums) {
      int res = 0;
      Arrays.sort(nums);
      int start = 0, end = nums.length - 1;
      while (start < end) {
         res = Math.max(nums[start] + nums[end], res);
      }
      return res;
   }
}
