package LeetCode.Biweekly57;

import java.util.*;

public class SmallestUnoccupiedChair {
   public static void main(String[] args) {
      SmallestUnoccupiedChair o = new SmallestUnoccupiedChair();
      System.out.println(o.smallestChair(new int[][] { { 33889, 98676 }, { 80071, 89737 }, { 44118, 52565 },
            { 52992, 84310 }, { 78492, 88209 }, { 21695, 67063 }, { 84622, 95452 }, { 98048, 98856 }, { 98411, 99433 },
            { 55333, 56548 }, { 65375, 88566 }, { 55011, 62821 }, { 48548, 48656 }, { 87396, 94825 }, { 55273, 81868 },
            { 75629, 91467 } }, 6));
   }

   public int smallestChair(int[][] times, int targetFriend) {
      int lim = (int) 1e5 + 1;
      int[] target = new int[] { times[targetFriend][0], times[targetFriend][1] };
      ArrayDeque<Integer>[] chairsUsed = new ArrayDeque[lim];
      ArrayDeque<Integer>[] chairs = new ArrayDeque[lim];
      shuffle(times);
      Arrays.sort(times, Comparator.comparingInt(o -> o[0]));
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for (int i = 0; i <= lim; i++)
         pq.add(i);

      for (int[] time : times) {
         if (chairs[time[0]] == null)
            chairs[time[0]] = new ArrayDeque<>();
         chairs[time[0]].add(time[1]);
      }
      for (int time = 0; time <= lim; time++) {
         ArrayDeque<Integer> addChairs = chairsUsed[time];
         if (addChairs != null) {
            pq.addAll(addChairs);
         }
         if (chairs[time] != null) {
            ArrayDeque<Integer> curr = chairs[time]; // All ending points
            if (time == target[0]) {
               // ans
               return pq.poll();
            } else {
               int chair = pq.poll();
               if (chairsUsed[curr.peek()] == null)
                  chairsUsed[curr.peek()] = new ArrayDeque<>();
               chairsUsed[curr.poll()].add(chair);
            }
         }
      }
      return 0;
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
