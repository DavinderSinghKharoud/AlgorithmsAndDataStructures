package LeetCode.WeeklyContest267;

import java.util.*;

public class PriceRestricted {
   public static void main(String[] args) {
      PriceRestricted o = new PriceRestricted();
   }

   public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
      DSU o = new DSU(n);
      boolean[] ans = new boolean[requests.length];
      for (int i = 0; i < requests.length; i++) {
         int[] request = requests[i];
         int a = request[0], b = request[1];
         if (o.isPoss(restrictions, a, b)) {
            o.unite(a, b);
            ans[i] = true;
         }
      }
      return ans;
   }

   static class DSU {
      int len;
      int[] parent, size;

      public DSU(int len) {
         this.len = len;
         parent = new int[len];
         size = new int[len];
         Arrays.fill(size, 1);
         for (int i = 0; i < len; i++)
            parent[i] = i;
      }

      boolean isPoss(int[][] restrictions, int a, int b) {
         int parent1 = findParent(a), parent2 = findParent(b);
         for (int[] restrict : restrictions) {
            int x = restrict[0], y = restrict[1];
            if (findParent(x) == findParent(y))
               return false;
            if (findParent(x) == parent1 && findParent(y) == parent2)
               return false;
            if (findParent(y) == parent1 && findParent(x) == parent2)
               return false;
         }
         return true;
      }

      void unite(int a, int b) {
         int parent1 = findParent(a), parent2 = findParent(b);
         if (parent1 == parent2)
            return;
         if (size[parent1] < size[parent2]) {
            parent1 = parent2 ^ parent1 ^ (parent2 = parent1);
         }

         parent[parent2] = parent1;
         size[parent1] += size[parent2];
         size[parent2] = 0;
      }

      int findParent(int a) {
         if (parent[a] == a)
            return a;
         return parent[a] = findParent(parent[a]);
      }
   }
}
