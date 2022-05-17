package LeetCode;

import java.util.*;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 * Example 1:
 * Given the following words in dictionary,
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * The correct order is: "wertf".
 * Example 2:
 * Given the following words in dictionary,
 * [
 *   "z",
 *   "x"
 * ]
 * The correct order is: "zx".
 * Example 3:
 * Given the following words in dictionary,
 * [
 *   "z",
 *   "x",
 *   "z"
 * ]
 * The order is invalid, so return "".
 * Note:
 * You may assume all letters are in lowercase.
 * You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 * If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.
 */
public class AlienDictionary {

   public static void main(String[] args) {
      System.out.println(new AlienDictionary().find(new String[] { "wrt", "wrf", "er", "ett", "rftt" }));
   }

   int a = 'a';

   String find(String[] arr) {
      int len = arr.length;
      ArrayDeque<Character>[] graph = new ArrayDeque[26];
      int[] inDegree = new int[26];
      Arrays.fill(inDegree, -1);
      for (int i = 1; i < len; i++) {
         String first = arr[i - 1], second = arr[i];
         for (int j = 0; j < Math.min(first.length(), second.length()); j++) {
            char c1 = first.charAt(j), c2 = second.charAt(j);
            if (c1 != c2) {
               if (graph[c1 - a] == null) {
                  graph[c1 - a] = new ArrayDeque<>();
                  if (inDegree[c1 - a] == -1)
                     inDegree[c1 - a] = 0;
               }
               graph[c1 - a].add(c2);
               // Add dependency
               if (inDegree[c2 - a] == -1)
                  inDegree[c2 - a] = 1;
               else
                  inDegree[c2 - a]++;
            }
         }
      }

      //We can append the remaining character if needed because we don't have any information about their order
      return topologicalSort(graph, inDegree);
   }

   String topologicalSort(ArrayDeque<Character>[] graph, int[] count) {
      Queue<Character> queue = new ArrayDeque<>();
      for (int i = 0; i < count.length; i++) {
         if (count[i] == 0) {
            queue.add(getChar(i));
         }
      }
      StringBuilder sbr = new StringBuilder();
      while (!queue.isEmpty()) {
         char curr = queue.poll();
         sbr.append(curr);
         if (graph[curr - a] != null) {
            for (char child : graph[curr - a]) {
               count[child - a]--;
               if (count[child - a] == 0) {
                  queue.add(child);
               }
            }
         }
      }
      return sbr.toString();
   }

   char getChar(int index) {
      return (char) (index + a);
   }
}
