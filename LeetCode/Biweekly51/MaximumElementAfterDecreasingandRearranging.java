package LeetCode.Biweekly51;

import java.util.*;

public class MaximumElementAfterDecreasingandRearranging {
   public static void main(String[] args) {
       System.out.println(maximumElementAfterDecrementingAndRearranging(new int[]{123123}));
   }

   public static int maximumElementAfterDecrementingAndRearranging(int[] arr) {
      int len = arr.length;
      long max = 1;
      shuffle(arr);
      Arrays.sort(arr);

      if (arr[0] != 1)
         arr[0] = 1;
      for (int i = 1; i < len; i++) {
         if (arr[i] - arr[i - 1] > 1) {
            arr[i] = arr[i - 1] + 1;
         }
         max = Math.max(arr[i], max);
      }
      return (int)max;
   }

   static void shuffle(int[] aa) {
      int n = aa.length;
      Random rand = new Random();
      for (int i = 1; i < n; i++) {
         int j = rand.nextInt(i + 1);
         int tmp = aa[i];
         aa[i] = aa[j];
         aa[j] = tmp;
      }
   }
}
