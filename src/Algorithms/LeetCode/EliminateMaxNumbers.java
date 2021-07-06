package Algorithms.LeetCode;

import CodeForces.Round712.E;

import java.util.*;

public class EliminateMaxNumbers {
   public static void main(String[] args) {
       EliminateMaxNumbers o = new EliminateMaxNumbers();
       System.out.println(o.eliminateMaximum(new int[]{3,2,4}, new int[]{5,3,2}));
   }

   public int eliminateMaximum(int[] dist, int[] speed) {
      int res = 0;
      int len = dist.length;
      int[] time = new int[len];
      for (int i = 0; i < len; i++) {
         if (dist[i] == 0)
            return 0;
         time[i] = (int) Math.ceil((double) dist[i] / speed[i]);
      }

      shuffleSort(time);

      int start = 0, end = len;
      while (start <= end) {
         int mid = (end + start) / 2;
         if (canKill(mid, time)) {
            res = mid;
            start = mid + 1;
         } else {
            end = mid - 1;
         }
      }
      return res;
   }

   boolean canKill(int kill, int[] time) {
      int count = 0;
      for (int i = 0; i < time.length; i++) {
         if (time[i] - count == 0)
            break;
         count++;
      }
      return count >= kill;
   }

   static void shuffleSort(int[] aa) {
      int n = aa.length;
      Random rand = new Random();
      for (int i = 1; i < n; i++) {
         int j = rand.nextInt(i + 1);
         int tmp = aa[i];
         aa[i] = aa[j];
         aa[j] = tmp;
      }
      Arrays.sort(aa);
   }
}
