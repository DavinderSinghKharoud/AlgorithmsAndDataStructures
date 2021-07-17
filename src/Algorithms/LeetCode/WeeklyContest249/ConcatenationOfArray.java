package Algorithms.LeetCode.WeeklyContest249;

import java.util.*;

public class ConcatenationOfArray {
   public static void main(String[] args) {
      ConcatenationOfArray o = new ConcatenationOfArray();
   }

   public int[] getConcatenation(int[] nums) {
      int len = nums.length;
      int[] res = new int[2 * len];
      for (int i = 0; i < len; i++) {
         res[i] = nums[i];
      }
      for (int i = 0; i < len; i++) {
         res[i + len] = nums[i];
      }
      return res;
   }
}
