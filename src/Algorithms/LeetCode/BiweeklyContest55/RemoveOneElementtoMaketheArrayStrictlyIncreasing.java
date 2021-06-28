package Algorithms.LeetCode.BiweeklyContest55;

import java.util.*;

public class RemoveOneElementtoMaketheArrayStrictlyIncreasing {
   public static void main(String[] args) {

   }

   public boolean canBeIncreasing(int[] nums) {
      int len = nums.length;
      for (int i = 0; i < len; i++) {
         // try every index
         List<Integer> lst = new ArrayList<Integer>();
         for (int j = 0; j < len; j++) {
            if (j == i)
               continue;
            lst.add(nums[j]);
         }
         boolean isInc = true;
         for (int k = 1; k < lst.size(); k++) {
            if (lst.get(k) <= lst.get(k - 1)) {
               isInc = false;
               break;
            }
         }
         if (isInc)
            return true;
      }
      return false;
   }
}
