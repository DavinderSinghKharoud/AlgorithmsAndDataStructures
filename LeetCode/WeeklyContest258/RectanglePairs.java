package LeetCode.WeeklyContest258;

import java.util.*;

public class RectanglePairs {
   public static void main(String[] args) {
      RectanglePairs o = new RectanglePairs();
       System.out.println(o.interchangeableRectangles(new int[][]{ {4, 8}, {3, 6}, {10, 20}}));
   }


   public long interchangeableRectangles(int[][] rectangles) {
      long count = 0;
      int len = rectangles.length;
      Map<Double, Integer> map = new HashMap<>();
       for (int[] curr : rectangles) {
           double div = (double) curr[0] / curr[1];
           map.put(div, map.getOrDefault(div, 0) + 1);
       }

      for (Map.Entry<Double, Integer> entry : map.entrySet()) {
         long curr = entry.getValue() - 1;
         curr = (curr * (curr + 1))/ 2;
         count += curr;
      }
      return count;
   }


}
