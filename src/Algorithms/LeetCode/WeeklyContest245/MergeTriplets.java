package Algorithms.LeetCode.WeeklyContest245;

import java.util.*;

public class MergeTriplets {
   public static void main(String[] args) {
    MergeTriplets o =new MergeTriplets();
       System.out.println(o.mergeTriplets(new int[][]{{2,5,3}, {1, 8, 4},{1, 7, 5}}, new int[]{
               2, 7, 5}));
   }

   public boolean mergeTriplets(int[][] triplets, int[] target) {

      int len = triplets.length;
      boolean[] isPoss = new boolean[len];
      Arrays.fill(isPoss, true);

      for (int i = 0; i < len; i++) {
         int[] triplet = triplets[i];
         if (triplet[0] > target[0] || triplet[1] > target[1] || triplet[2] > target[2]) {
            isPoss[i] = false;
         }
      }

      // Merge all
      int[] ans = new int[3];
      for (int i = 0; i < len; i++) {
         if (isPoss[i]) {
            int[] triplet = triplets[i];
            ans[0] = max(ans[0], triplet[0]);
            ans[1] = max(ans[1], triplet[1]);
            ans[2] = max(ans[2], triplet[2]);
         }
      }

      return ans[0] == target[0] && ans[1] == target[1] && ans[2] == target[2];
   }

   int max(int a, int b) {
      return Math.max(a, b);
   }
}
