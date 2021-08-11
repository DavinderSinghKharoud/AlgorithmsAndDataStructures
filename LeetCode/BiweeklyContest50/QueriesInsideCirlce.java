package LeetCode.BiweeklyContest50;

import java.util.Arrays;

public class QueriesInsideCirlce {
   public static void main(String[] args) {

      QueriesInsideCirlce obj = new QueriesInsideCirlce();
       System.out.println(Arrays.toString(obj.countPoints(new int[][]{{1, 3}, {3, 3}, {5, 3}, {2, 2}}, new int[][]{{1, 1, 2}})));
   }

   public int[] countPoints(int[][] points, int[][] queries) {
      int[] ans = new int[queries.length];
      for (int i = 0; i < queries.length; i++) {
         int[] query = queries[i];
         int count = 0;
         for (int[] point : points) {
            if (isInside(point, query)) {
               count++;
            }
         }
         ans[i] = count;
      }
      return ans;
   }

   boolean isInside(int[] point, int[] query) {
      double radius = query[2];
      double dis = Math.pow(query[0] - point[0], 2) + Math.pow(query[1] - point[1], 2);
      return dis <= radius * radius;
   }
}
