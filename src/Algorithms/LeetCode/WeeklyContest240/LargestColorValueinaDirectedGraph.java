package Algorithms.LeetCode.WeeklyContest240;

import java.util.*;

public class LargestColorValueinaDirectedGraph {
   public static void main(String[] args) {
      LargestColorValueinaDirectedGraph o = new LargestColorValueinaDirectedGraph();
      System.out.println(o.largestPathValue("abaca", new int[][] { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 3, 4 } }));
   }

   ArrayDeque<Integer>[] arr;
   int[] inDegree;
   int[][] ncolors;

   public int largestPathValue(String colors, int[][] edges) {
      int len = colors.length();
      arr = new ArrayDeque[len];
      ncolors = new int[len][26];
      inDegree = new int[len];
      for (int[] edge : edges) {
         if (arr[edge[0]] == null)
            arr[edge[0]] = new ArrayDeque<>();
         arr[edge[0]].add(edge[1]);
         inDegree[edge[1]]++;
      }

      ArrayDeque<Integer> queue = new ArrayDeque<>();
      // We start from the node which has 0 incoming edges
      for (int i = 0; i < len; i++) {
         if (inDegree[i] == 0) {
            queue.add(i);
            ncolors[i][colors.charAt(i) - 'a']++;
         }
      }

      int ans = -1;
      int seen = 0;
      while (!queue.isEmpty()) {
         int curr = queue.poll();
         int maxVal = Arrays.stream(ncolors[curr]).max().getAsInt();
         ans = Math.max(ans, maxVal);
         seen++;

         if(arr[curr] == null) continue;
         for (int adj : arr[curr]) {
            // Update and get the maxValue for each color
            for (int i = 0; i < 26; i++)
               ncolors[adj][i] = Math.max(ncolors[adj][i], ncolors[curr][i] + (i == colors.charAt(adj) - 'a' ? 1 : 0));
            if (--inDegree[adj] == 0) {
               queue.add(adj);
            }
         }
      }
      return (seen < len) ? -1 : ans;
   }

}
