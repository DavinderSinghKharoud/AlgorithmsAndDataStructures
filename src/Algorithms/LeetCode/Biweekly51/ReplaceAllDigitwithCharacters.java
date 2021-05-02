package Algorithms.LeetCode.Biweekly51;

import java.util.*;

public class ReplaceAllDigitwithCharacters {
   public static void main(String[] args) {
       System.out.println(replaceDigits("a1b2c3d4e"));
   }

   public static String replaceDigits(String s) {
      StringBuilder sbr = new StringBuilder();
      for (int i = 0; i < s.length(); i++) {
         char c = s.charAt(i);
         if (c < 'a' || c > 'z') {
            // It is a digit
            char prev = s.charAt(i - 1);
            int a = prev + Character.getNumericValue(c);
            sbr.append((char) a);
         } else {
            sbr.append(c);
         }
      }
      return sbr.toString();
   }
}
