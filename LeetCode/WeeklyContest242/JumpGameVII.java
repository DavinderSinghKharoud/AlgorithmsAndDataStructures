package LeetCode.WeeklyContest242;

import java.util.*;

public class JumpGameVII {
   public static void main(String[] args) {
      JumpGameVII o = new JumpGameVII();
      StringBuilder s = new StringBuilder();
      while (s.length() < 100000) {
         s.append(0);
      }
   //  System.out.println(o.canReach2(s.toString(), 1, 1));

      System.out.println(o.canReach3("0000000000", 2, 5));
   }

   public boolean canReach2(String s, int minJump, int maxJump) {
      ArrayDeque<Integer> queue = new ArrayDeque<>();
      if (s.charAt(s.length() - 1) != '0')
         return false;

      queue.add(0);
      int prev = -1;
      while (!queue.isEmpty()) {
         int curr = queue.pollFirst();
         if (curr == s.length() - 1)
            return true;
         int lower = Math.max(curr + minJump, prev), high = Math.min(s.length() - 1, curr + maxJump);
         while (lower <= high) {
            if (s.charAt(lower) == '0') {
               queue.add(lower);
            }
            lower++;
         }
         prev = lower;
      }
      return false;
   }

   public boolean canReach3(String s, int minJump, int maxJump) {
      int index = 0;
      Deque<Integer> queue = new ArrayDeque<>();
      queue.add(0);
      while (++index < s.length()) {
         while (!queue.isEmpty() && queue.peekFirst() < index - maxJump) {
            queue.pollFirst();
         }
         if (s.charAt(index) == '0' && !queue.isEmpty() && queue.peekFirst() <= index - minJump) {
            queue.add(index);
         }
      }
      return !queue.isEmpty() && queue.peekLast() == s.length() - 1;
   }

   // Although it is O(n), but time limit exceeded due to java stack overflow
   public boolean canReach(String s, int minJump, int maxJump) {
      if (s.charAt(s.length() - 1) != '0')
         return false;
      if (s.length() == 1)
         return true;
      List<Integer> indexes = new ArrayList<>();
      for (int i = minJump; i <= Math.min(s.length() - 1, maxJump); i++) {
         if (s.charAt(i) == '0')
            indexes.add(i);
      }
      return isPossible(s, indexes, minJump, maxJump);
   }

   boolean isPossible(String s, List<Integer> indexes, int minJump, int maxJump) {
      if (indexes.size() == 0)
         return false;
      for (int i : indexes)
         if (i == s.length() - 1)
            return true;
      int prev = -1;
      List<Integer> lst = new ArrayList<>();
      for (int i : indexes) {
         int lower = Math.max(i + minJump, prev), high = Math.min(s.length() - 1, i + maxJump);
         while (lower <= high) {
            if (s.charAt(lower) == '0')
               lst.add(lower);
            lower++;
         }
         prev = high + 1;
      }
      return isPossible(s, lst, minJump, maxJump);
   }
}
