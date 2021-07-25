package Algorithms.LeetCode.Biweekly57;

import java.util.*;

public class NumberOfVisiblePeopleInQueue {
   public static void main(String[] args) {
      NumberOfVisiblePeopleInQueue o = new NumberOfVisiblePeopleInQueue();
      System.out.println(Arrays.toString(o.canSeePersonsCount(new int[]{5,4,3,2,8})));
   }

   public int[] canSeePersonsCount(int[] heights) {
      int[] res = new int[heights.length];
      Stack<Integer> stack = new Stack<>();

      for (int i = 0; i < heights.length; i++) {
         while (!stack.isEmpty() && heights[stack.peek()] < heights[i]) {
            res[stack.pop()]++;
         }
         if (!stack.isEmpty())
            res[stack.peek()]++;
         stack.add(i);
      }
      return res;
   }
}
