package LeetCode.BiweeklyContest67;

import java.util.*;
import java.util.stream.Collectors;

public class FindGoodDaysToRobBank {
   public static void main(String[] args) {
      FindGoodDaysToRobBank o = new FindGoodDaysToRobBank();
       System.out.println(o.goodDaysToRobBank(new int[]{
               1,2 , 3, 4
       }, 1));
   }

   public List<Integer> goodDaysToRobBank(int[] security, int time) {
      List<Integer> ans = new ArrayList<>();
      int len = security.length;
      if(time == 0){
          for(int i = 0; i < len; i++) ans.add(i);
          return ans;
      }
      boolean[] left = new boolean[len];
      boolean[] right = new boolean[len];
      ArrayDeque<Integer> stack = new ArrayDeque<>();

      for (int i = 0; i < len; i++) {
         int curr = security[i];
         if (i - time >= 0 && !stack.isEmpty()) {
            if (stack.peekFirst() == (i - time))
               stack.pollFirst();
         }
         while (!stack.isEmpty() && security[stack.peekLast()] < curr)
            stack.pollLast();
         stack.addLast(i);
         if (stack.size() == time)
            left[i] = true;
      }
      stack.clear();
      for (int i = len - 1; i >= 0; i--) {
         int curr = security[i];
         if (i + time < len && !stack.isEmpty()) {
            if (stack.peekFirst() == (i + time))
               stack.pollFirst();
         }
         while (!stack.isEmpty() && security[stack.peekLast()] < curr)
            stack.pollLast();
         stack.addLast(i);
         if (stack.size() == time)
            right[i] = true;
      }

      for (int i = time; i < len - time; i++) {
         if (left[i - 1] && right[i + 1] && security[i] <= security[i - 1] && security[i] <= security[i + 1]) {
            ans.add(i);
         }
      }
      return ans;
   }
}
