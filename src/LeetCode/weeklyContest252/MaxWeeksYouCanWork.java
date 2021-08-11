package LeetCode.weeklyContest252;

import java.util.*;

public class MaxWeeksYouCanWork {
   public static void main(String[] args) {
      MaxWeeksYouCanWork o = new MaxWeeksYouCanWork();
      // System.out.println(o.numberOfWeeks(new int[] { 4, 5, 5, 2 }));
      System.out.println(o.numberOfWeeks(new int[] { 1, 10, 7, 1, 7, 2, 10, 10, 355359359 }));
   }

   public long numberOfWeeks(int[] milestones) {
      int len = milestones.length;
      long sum = Arrays.stream(milestones).asLongStream().sum();
      int max = Arrays.stream(milestones).max().getAsInt();

      if ((sum - max) >= max)
         return sum;
      return 2L * (sum - max) + 1;
   }

}
