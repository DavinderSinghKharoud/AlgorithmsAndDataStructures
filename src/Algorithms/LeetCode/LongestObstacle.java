package Algorithms.LeetCode;

import java.util.*;

public class LongestObstacle {

   public static void main(String[] args) {
      LongestObstacle o = new LongestObstacle();
      System.out.println(
            Arrays.toString(o.longestObstacleCourseAtEachPosition(new int[] { 3, 1, 1235, 6, 41231, 212312312 })));
   }

   public int[] longestObstacleCourseAtEachPosition2(int[] obstacles) {
      int len = obstacles.length;
      int[] res = new int[len];

      int[] arr = new int[len + 1];
      Arrays.fill(arr, Integer.MAX_VALUE);

      for (int i = 0; i < len; i++) {
         int curr = obstacles[i];
         int upper = upperBound(arr, curr);
         if (upper == len) {
            // Last position
            res[i] = upper;
            arr[upper] = curr;
         } else {
            res[i] = upper + 1;
            arr[upper + 1] = curr;
         }
      }
      return res;
   }

   // Gives strict upperBound that next number would be greater than the target
   int upperBound(int[] arr, int val) {
      int l = 0, r = arr.length - 1;
      while (l < r) {
         int mid = (r + l + 1) >> 1;
         if (arr[mid] <= val) {
            l = mid;
         } else
            r = mid - 1;
      }
      return l;
   }

   // ** *****************************************************/
   public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
      int hash = 1;
      int[] copy = Arrays.copyOf(obstacles, obstacles.length);
      shuffleSort(copy);
      Map<Integer, Integer> map = new HashMap<>();

      for (int i = 0; i < obstacles.length; i++) {
         if (!map.containsKey(copy[i]))
            map.put(copy[i], hash++);
      }

      for (int i = 0; i < obstacles.length; i++) {
         obstacles[i] = map.get(obstacles[i]);
      }

      SegmentTree seg = new SegmentTree(obstacles.length + 1);
      int[] ans = new int[obstacles.length];
      for (int i = 0; i < obstacles.length; i++) {
         int max = seg.query(1, obstacles[i]);
         ans[i] = max + 1;
         seg.update(obstacles[i], max + 1);
      }
      return ans;
   }

   public static class SegmentTree {

      int[] seg;
      int len;

      public SegmentTree(int len) {
         this.len = len;
         seg = new int[len << 1];
      }

      public void update(int target, int value) {
         target += len;

         seg[target] = value;

         while (target > 1) {
            // Move up by one level
            target >>= 1;
            int pos = target << 1;
            seg[target] = Math.max(seg[pos], seg[pos + 1]);
         }

      }

      public int query(int l, int r) {
         l += len;
         r += len;

         int res = Integer.MIN_VALUE;
         while (l <= r) {

            // If left index is odd
            if ((l & 1) == 1) {
               res = Math.max(res, seg[l]);
               l++; // make it even
            }

            // If right index is even
            if ((r & 1) == 0) {
               res = Math.max(res, seg[r]);
               r--;
            }

            // Move to the next higher level
            l >>= 1;
            r >>= 1;
         }
         return res;
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
