package LeetCode;

import java.util.*;

public class RemoveStonesMinimize {

   public static void main(String[] args) {

   }

   public int minStoneSum(int[] piles, int k) {
      PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
      long sum = 0;
      for (int pile : piles) {
         sum += pile;
         pq.add(pile);
      }
      while (k-- > 0 && !pq.isEmpty()) {
         int curr = pq.poll();
         sum -= curr;
         int val = (int) Math.ceil((double) curr / 2);
         sum += val;
         pq.add(val);
      }
      return (int) sum;
   }
}
