package Algorithms.LeetCode.Biweekly51;

import java.util.*;

public class SeatReservationManager {
   public static void main(String[] args) {

   }

   PriorityQueue<Integer> pq;

   public SeatReservationManager(int n) {
      pq = new PriorityQueue<>();
      for (int i = 1; i <= n; i++)
         pq.add(i);
   }

   public int reserve() {
      return pq.remove();
   }

   public void unreserve(int seatNumber) {
      pq.add(seatNumber);
   }
}
