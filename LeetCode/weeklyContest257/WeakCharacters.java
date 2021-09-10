package LeetCode.weeklyContest257;

import java.util.Arrays;

public class WeakCharacters {
   public static void main(String[] args) {
      WeakCharacters o = new WeakCharacters();
   }

   public int numberOfWeakCharacters(int[][] properties) {
      Arrays.sort(properties, (o1, o2) -> {
         if (o1[0] == o2[0])
            return Integer.compare(o2[1], o1[1]);
         return Integer.compare(o1[0], o2[0]);
      });
      int count = 0;
      int max = -1;
      for (int i = properties.length - 1; i >= 0; i--) {
         int[] curr = properties[i];
         count += (curr[1] < max ? 1 : 0);
         max = Math.max(max, curr[1]);
      }
      return count;
   }

}
