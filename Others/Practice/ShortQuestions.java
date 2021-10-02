package Others.Practice;

import java.util.Arrays;

public class ShortQuestions {
   public static void main(String[] args) {

   }

   boolean areTheyEqual(int[] array_a, int[] array_b) {
      // Write your code here
      if (array_a.length != array_b.length)
         return false;
      Arrays.sort(array_a);
      Arrays.sort(array_b);

      for (int i = 0; i < array_a.length; i++) {
         if (array_a[i] != array_b[i])
            return false;
      }
      return true;
   }
}
