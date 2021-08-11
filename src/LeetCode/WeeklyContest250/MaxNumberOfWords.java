package LeetCode.WeeklyContest250;

import java.util.*;

public class MaxNumberOfWords {
   public static void main(String[] args) {
       System.out.println(new MaxNumberOfWords().canBeTypedWords("Hello", "g"));
   }

   public int canBeTypedWords(String text, String brokenLetters) {
      int res = 0;
      Set<Character> set = new HashSet<>();
      int len = text.length();
      for (int i = 0; i < brokenLetters.length(); i++) {
         set.add(brokenLetters.charAt(i));
      }
      boolean isFound = true;
      for (int i = 0; i < len; i++) {
         char c = text.charAt(i);
         if (c == ' ') {
            if (isFound)
               res++;
            isFound = true;
         } else {
            if (set.contains(c))
               isFound = false;
         }
      }
      if (isFound)
         res++;
      return res;
   }
}
