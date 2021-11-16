package LeetCode.WeeklyContest265;

import java.util.*;

public class MinAndMaxNumberOfNodes {
   public static void main(String[] args) {
      MinAndMaxNumberOfNodes o = new MinAndMaxNumberOfNodes();
   }

   public int[] nodesBetweenCriticalPoints(ListNode head) {
      List<Integer> points = new ArrayList<>();
      int index = 1;
      ListNode temp = head.next, prev = head;
      while (temp != null) {
         ListNode next = temp.next;
         if (next != null) {
            if ((temp.val > prev.val && temp.val > next.val) || (temp.val < prev.val && temp.val < next.val)) {
               points.add(index);
            }
         }
         prev = temp;
         temp = next;
         index++;
      }

      int[] res = new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };
      for (int i = 1; i < points.size(); i++) {
         res[0] = Math.min(res[0], points.get(i) - points.get(i - 1));
      }
      if (points.size() >= 2) {
         res[1] = points.get(points.size() - 1) - points.get(0);
      }
      if (res[0] == Integer.MAX_VALUE)
         res[0] = -1;
      if (res[1] == Integer.MIN_VALUE)
         res[1] = -1;
      return res;
   }

   public class ListNode {
      int val;
      ListNode next;

      ListNode() {
      }

      ListNode(int val) {
         this.val = val;
      }

      ListNode(int val, ListNode next) {
         this.val = val;
         this.next = next;
      }
   }
}
