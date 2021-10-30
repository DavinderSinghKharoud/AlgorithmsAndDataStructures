package LeetCode.WeeklyContest264;

import java.util.*;

public class ParallelCoursesIII {
   public static void main(String[] args) {
      ParallelCoursesIII o = new ParallelCoursesIII();
      System.out.println(o.minimumTime(9,
            new int[][] { { 2, 7 }, { 2, 6 }, { 3, 6 }, { 4, 6 }, { 7, 6 }, { 2, 1 }, { 3, 1 }, { 4, 1 }, { 6, 1 },
                  { 7, 1 }, { 3, 8 }, { 5, 8 }, { 7, 8 }, { 1, 9 }, { 2, 9 }, { 6, 9 }, { 7, 9 } },
            new int[] { 9, 5, 9, 5, 8, 7, 7, 8, 4 }));
   }

   public int minimumTime(int n, int[][] relations, int[] time) {
      int[] count = new int[n + 1];
      ArrayDeque<Integer>[] courses = new ArrayDeque[n + 1];
      for (int[] relation : relations) {
         int curr = relation[0], next = relation[1];
         if (courses[curr] == null)
            courses[curr] = new ArrayDeque<>();
         courses[curr].add(next);
         count[next]++;
      }

      ArrayDeque<Integer> queue = new ArrayDeque<>();
      for (int i = 1; i <= n; i++) {
         if (count[i] == 0) {
            queue.add(i);
         }
      }

      int[] max = new int[n + 1];
      for(int i = 0; i < n; i++){
         max[i + 1] = time[i];
      }
      while (!queue.isEmpty()) {
         int len = queue.size();

         while (len-- > 0) {
            int curr = queue.pollFirst();
            if (courses[curr] != null) {
               for (int course : courses[curr]) {
                  max[course] = Math.max(max[course], max[curr] + time[course - 1]);
                  count[course]--;
                  if (count[course] == 0)
                     queue.addLast(course);
               }
            }
         }
      }
      return Arrays.stream(max).max().getAsInt();
   }
}
