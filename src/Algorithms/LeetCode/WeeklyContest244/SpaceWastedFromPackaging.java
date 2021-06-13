package Algorithms.LeetCode.WeeklyContest244;

import java.util.*;

public class SpaceWastedFromPackaging {
   public static void main(String[] args) {
      SpaceWastedFromPackaging o = new SpaceWastedFromPackaging();
      System.out
            .println(o.minWastedSpace(new int[] { 17, 6, 17, 4, 10, 17, 20, 7, 5, 12 }, new int[][] { { 1, 20, 11 } }));
   }

   int mod = (int) 1e9 + 7;

   public int minWastedSpace(int[] packages, int[][] boxes) {
      Fenwick count = new Fenwick(100_000);
      Fenwick values = new Fenwick(100_000);
      int maxPackage = 0;
      for (int p : packages) {
         count.update(p, 1);
         values.update(p, p);
         maxPackage = Math.max(maxPackage, p);
      }

      long res = Long.MAX_VALUE;
      for (int[] box : boxes) {
         shuffleSort(box);
         if (box[box.length - 1] < maxPackage)
            continue;
         long curr = 0;
         for (int i = 0; i < box.length; i++) {
            long total = count.getSum(box[i]) - ((i > 0) ? count.getSum(box[i - 1]) : 0);
            long currSum = values.getSum(box[i]) - ((i > 0) ? values.getSum(box[i - 1]) : 0);
            curr += total * box[i] - currSum;
         }
         res = Math.min(res, curr);
      }
      if (res == Long.MAX_VALUE)
         return -1;
      return ((int) (res % mod));
   }

   public class Fenwick {
      long[] prefix;
      int len;

      public Fenwick(int len) {
         this.len = len;
         prefix = new long[len + 1];
      }

      public void update(int index, int val) {
         index++;
         while (index <= len) {
            prefix[index] += val;
            index += (index & -index);
         }
      }

      public long getSumRange(int index1, int index2) {
         return getSum(index2) - getSum(index1 - 1);
      }

      private long getSum(int index) {
         index++;
         long sum = 0;
         while (index > 0) {
            sum += prefix[index];
            index -= (index & -index);
         }
         return sum;
      }

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
