package Algorithms.LeetCode.WeeklyContest238;

import java.util.*;

public class MaximumBuildingHeight {
   public static void main(String[] args) {
      MaximumBuildingHeight o = new MaximumBuildingHeight();
      System.out.print(o.maxBuilding(5, new int[][] { { 2, 1 }, { 4, 1 } }));
   }

       public int maxBuilding(int n, int[][] restrictions) {
          int len = restrictions.length;
          Arrays.sort(restrictions, Comparator.comparingInt(o -> o[0]));
          List<int[]> arr = new ArrayList<>();
          arr.add(new int[] { 1, 0 });
           arr.addAll(Arrays.asList(restrictions));
          if (len == 0 || restrictions[len - 1][0] != n) {
             arr.add(new int[] { n, Integer.MAX_VALUE });
          }
          traverse( arr);
          Collections.reverse(arr);
          return traverse( arr);
       }

       int traverse(List<int[]> arr) {
          int res = 0;
          for (int i = 0; i < arr.size() - 1; i++) {
             int h1 = arr.get(i)[1], h2 = arr.get(i + 1)[1];
             int h = h1 + Math.abs(arr.get(i + 1)[0] - arr.get(i)[0]);
             if (h > h2) {
                h = h2 + (h - h2) / 2;
             }
             res = Math.max(res, h);
             arr.get(i + 1)[1] = Math.min(h2, h);
          }
          return res;
       }

}
