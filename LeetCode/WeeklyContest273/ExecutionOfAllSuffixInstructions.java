package LeetCode.WeeklyContest273;

import java.util.*;

public class ExecutionOfAllSuffixInstructions {
   public static void main(String[] args) {
      ExecutionOfAllSuffixInstructions o = new ExecutionOfAllSuffixInstructions();
   }

   int n, m;

   public int[] executeInstructions(int n, int[] startPos, String s) {
      this.n = n;
      m = n;

      int[] ans = new int[s.length()];

      for (int i = 0; i < s.length(); i++) {
         ans[i] = find(s, i, startPos);
      }
      return ans;
   }

   int find(String s, int index, int[] startPos) {
      int count = 0;
      int[] start = Arrays.copyOf(startPos, startPos.length);
      for (int i = index; i < s.length(); i++) {
         if (isValid(move(s.charAt(i), start))) {
            start = move(s.charAt(i), start);
            count++;
         } else
            break;
      }
      return count;
   }

   boolean isValid(int[] arr) {
      return arr[0] >= 0 && arr[1] >= 0 && arr[0] < n && arr[1] < m;
   }

   int[] move(char state, int[] arr) {
      int[] mod = new int[] { arr[0], arr[1] };
      if (state == 'R') {
         mod[1]++;
      } else if (state == 'L') {
         mod[1]--;
      } else if (state == 'U') {
         mod[0]--;
      } else
         mod[0]++;
      return mod;
   }
}
