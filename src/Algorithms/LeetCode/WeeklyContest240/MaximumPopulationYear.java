package Algorithms.LeetCode.WeeklyContest240;

import java.util.*;

public class MaximumPopulationYear {
   public static void main(String[] args) {

   }

   public int maximumPopulation(int[][] logs) {
      Map<Integer, Integer> map = new HashMap<>();
      int max = -1;
      int res = -1;
      Arrays.sort(logs, Comparator.comparingInt(o -> o[0]));
      for (int[] log : logs) {
         int start = log[0], end = log[1] - 1;
         for (int i = start; i <= end; i++) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if (map.get(i) > max) {
               max = map.get(i);
               res = i;
            }
         }
      }
      return res;
   }

   static void shuffle(int[][] aa) {
      int n = aa.length;
      Random rand = new Random();
      for (int i = 1; i < n; i++) {
         int j = rand.nextInt(i + 1);
         int first = aa[i][0];
         int second = aa[i][1];
         aa[i][0] = aa[j][0];
         aa[i][1] = aa[j][1];
         aa[j][0] = first;
         aa[j][1] = second;
      }
   }
}
