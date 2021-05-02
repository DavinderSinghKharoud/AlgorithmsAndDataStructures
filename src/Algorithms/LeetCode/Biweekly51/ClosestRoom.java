package Algorithms.LeetCode.Biweekly51;

import java.util.*;

public class ClosestRoom {
   public static void main(String[] args) {
      System.out.println(Arrays.toString(closestRoom(new int[][]{{1,4},{2,3},{3,5},{4,1},{5,2}},
              new int[][]{{2, 3}, {2, 4}, {2, 5}, {2, 6},{2, 6}})));
   }

   public static int[] closestRoom(int[][] rooms, int[][] queries) {
      int len = queries.length;
      int[] ans = new int[len];
      shuffle(rooms);
      TreeMap<Integer, Integer> map = new TreeMap<>();

      Query[] queries2 = new Query[queries.length];
      for (int i = 0; i < queries.length; i++) {
         queries2[i] = new Query(queries[i][0], queries[i][1], i);
      }
      Arrays.sort(queries2, Comparator.comparingInt(o -> o.size));
      Arrays.sort(rooms, Comparator.comparingInt(o -> o[1]));
      for (int i = 0; i < rooms.length; i++) {
         int count = 0;
         if (map.containsKey(rooms[i][0]))
            count = map.get(rooms[i][0]);
         map.put(rooms[i][0], count + 1);
      }
      int start = 0;
      for (Query query : queries2) {
         int size = query.size;
         while (start < rooms.length && rooms[start][1] < size) {
            int count = map.getOrDefault(rooms[start][0], 1) - 1;
            if (count == 0)
               map.remove(rooms[start][0]);
            else
               map.put(rooms[start][0], count);
            start++;
         }
         if (!map.isEmpty()) {
            int room = query.room;
            Integer low = map.floorKey(room);
            Integer high = map.ceilingKey(room);
            if (low != null && high != null) {
               int abs = Math.abs(room - low);
               int abs2 = Math.abs(room - high);
               if (abs <= abs2)
                  query.res = low;
               else
                  query.res = high;
            } else if (low != null) {
               query.res = low;
            } else
               query.res = high;
         }
      }

      for (Query query : queries2) {
         ans[query.index] = query.res;
      }

      return ans;
   }

   static class Query {
      int room, size, index;
      int res = -1;

      public Query(int r, int size, int i) {
         this.room = r;
         this.size = size;
         index = i;
      }
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
