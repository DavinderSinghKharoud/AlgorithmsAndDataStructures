package LeetCode;

import java.util.*;

public class NumberOfStringsAsSubstring {

   public static void main(String[] args) {

   }

   public int numOfStrings(String[] patterns, String word) {
      int count = 0;
      for (String pattern : patterns) {
         if (word.contains(pattern))
            count++;
      }
      return count;
   }
}
