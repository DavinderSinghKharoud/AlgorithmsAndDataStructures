package Algorithms.LeetCode.Biweekly54;

import java.util.*;

public class IntegerRange {
   public static void main(String[] args) {

   }

   public boolean isCovered(int[][] ranges, int left, int right) {
      Set<Integer> set = new HashSet<>();
      for (int i = left; i <= right; i++)
         set.add(i);
      boolean res = false;
      for (int[] range : ranges) {
         for (int i = range[0]; i <= range[1]; i++) {
            if (set.contains(i))
               set.remove(i);
         }
      }
      return set.size() == 0;
   }
}
