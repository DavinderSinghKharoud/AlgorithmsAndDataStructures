package Algorithms.LeetCode.Biweekly57;

import java.util.*;

public class DescribeThaPainting {
   public static void main(String[] args) {
      DescribeThaPainting o = new DescribeThaPainting();
   }

   public List<List<Long>> splitPainting(int[][] segments) {
      List<List<Long>> res = new ArrayList<>();
      TreeMap<Integer, Long> map = new TreeMap<>();
      for (int[] seg : segments) {
         map.put(seg[0], map.getOrDefault(seg[0], 0L) + seg[2]);
         map.put(seg[1], map.getOrDefault(seg[1], 0L) - seg[2]);
      }

      int prev = 0;
      long sum = 0;
      for (int key : map.keySet()) {
         if (prev != 0 && sum > 0) {
            res.add(Arrays.asList((long) prev, (long) key, sum));
         }
         sum += map.get(key);
         prev = key;
      }
      return res;
   }
}
