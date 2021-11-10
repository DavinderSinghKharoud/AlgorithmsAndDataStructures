package LeetCode.WeeklyContest265;

import java.util.*;

public class MinOperationsConvertNumber {
   public static void main(String[] args) {
      MinOperationsConvertNumber o = new MinOperationsConvertNumber();
   }

   public int minimumOperations(int[] nums, int start, int goal) {
      int len = nums.length;
      Queue<Node> queue = new ArrayDeque<>();
      Set<Long> vis = new HashSet<>();

      queue.add(new Node(start, 0));
      vis.add((long) start);

      while (!queue.isEmpty()) {
         Node curr = queue.poll();

         for (int num : nums) {
            long sum = curr.val + num;
            if (sum == goal)
               return curr.steps + 1;
            if (isValid(sum, vis)) {
               queue.add(new Node(sum, curr.steps + 1));
               vis.add(sum);
            }

            long diff = curr.val - num;
            if (diff == goal)
               return curr.steps + 1;
            if (isValid(diff, vis)) {
               queue.add(new Node(diff, curr.steps + 1));
               vis.add(diff);
            }

            long xor = curr.val ^ num;
            if (xor == goal)
               return curr.steps + 1;
            if (isValid(xor, vis)) {
               queue.add(new Node(xor, curr.steps + 1));
               vis.add(diff);
            }
         }
      }
      return -1;
   }

   boolean isValid(long num, Set<Long> set) {
      return (num >= 0 && num <= 1000 && !set.contains(num));
   }

   static class Node {
      long val;
      int steps;

      public Node(long v, int s) {
         val = v;
         steps = s;
      }
   }
}
