package LeetCode.WeeklyContest257;

import java.util.*;

public class GcdSort {
   public static void main(String[] args) {
      GcdSort o = new GcdSort();
       System.out.println(o.gcdSort(new int[]{10,5,9,3,15}));
   }

   public boolean gcdSort(int[] nums) {
      int len = nums.length;
      int max = Arrays.stream(nums).max().getAsInt();
      Set<Integer> set = new HashSet<>();
      for (int num : nums)
         set.add(num);

      Dsu dsu = new Dsu(max);

      // attach every number with its factors
      boolean[] isPrime = new boolean[max + 1];
      Arrays.fill(isPrime, true);
      for (int i = 2; i <= max; i++) {
         if (isPrime[i]) {
            for (int j = 1; j * i <= max; j++) {
               int mul = i * j;
               if (set.contains(mul))
                  dsu.unite(mul, i);
               isPrime[mul] = false;
            }
         }
      }

      int[] sorted = Arrays.copyOf(nums, len);
      shuffleSort(sorted);
      for (int i = 0; i < len; i++) {
         if (dsu.find(nums[i]) != dsu.find(sorted[i]))
            return false;
      }
      return true;
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

   static class Dsu {
      int[] parent, size;

      public Dsu(int len) {
         parent = new int[len + 1];
         size = new int[len + 1];
         Arrays.fill(parent, -1);
         Arrays.fill(size, 1);
      }

      public boolean unite(int a, int b) {
         int p1 = find(a), p2 = find(b);
         if (p1 == p2)
            return false;
         if (size[p1] < size[p2])
            p1 = p2 ^ p1 ^ (p2 = p1);
         parent[p2] = p1;
         size[p1] += size[p2];
         size[p2] = 0;
         return true;
      }

      public int find(int node) {
         if (parent[node] == -1)
            return node;
         return parent[node] = find(parent[node]);
      }
   }
}
