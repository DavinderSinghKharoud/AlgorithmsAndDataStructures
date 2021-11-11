package LeetCode.WeeklyContest266;

import java.util.*;

public class VowelsAllString {
   public static void main(String[] args) {
      VowelsAllString o = new VowelsAllString();
      System.out.println(o.countVowels("bba"));
   }

   public long countVowels(String word) {
      long res = 0;

      Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
      for (int i = 0; i < word.length(); i++) {
         char c = word.charAt(i);
         if (vowels.contains(c)) {
            // Check its contribution
            int left = i;
            int right = word.length() - i; // inclusive
            res += ((long) left * right);
            if (right - 1 > 0)
               res += right - 1;
            res++;
         }
      }
      return res;
   }
}
