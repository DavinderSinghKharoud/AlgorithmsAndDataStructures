package LeetCode.Biweekly57;

import java.util.*;

public class EqualOccurrences {
   public static void main(String[] args) {
      EqualOccurrences o = new EqualOccurrences();

   }

   public boolean areOccurrencesEqual(String s) {
      Map<Character, Integer> map = new HashMap<>();
      for (char c : s.toCharArray()) {
         map.put(c, map.getOrDefault(c, 0) + 1);
      }
      int count = -1;
      for (Map.Entry<Character, Integer> entry : map.entrySet()) {
         if (count == -1) {
            count = entry.getValue();
         } else {
            if (count != entry.getValue())
               return false;
         }
      }
      return true;
   }
}
