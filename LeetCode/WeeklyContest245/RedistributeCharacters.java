package LeetCode.WeeklyContest245;

import java.util.*;

public class RedistributeCharacters {
   public static void main(String[] args) {

   }

   public boolean makeEqual(String[] words) {
      int len = words.length;
      Map<Character, Integer> map = new HashMap<>();
      for (String word : words) {
         for (int i = 0; i < word.length(); i++) {
            map.put(word.charAt(i), map.getOrDefault(word.charAt(i), 0) + 1);
         }
      }

      for (Map.Entry<Character, Integer> entry : map.entrySet()) {
         if (entry.getValue() % len != 0)
            return false;
      }
      return true;
   }
}
