package LeetCode;

import java.util.*;

public class SubstringWithConcatenationofAllWords {
   public static void main(String[] args) {

   }

   public List<Integer> findSubstring(String s, String[] words) {
      List<Integer> res = new ArrayList<>();
      Map<String, Integer> wordMap = new HashMap<>();
      for (String word : words) {
         wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
      }

      int wordCount = words.length, wordLength = words[0].length();

      for (int i = 0; i < s.length() - wordCount * wordLength + 1; i++) {
         Map<String, Integer> curr = new HashMap<>();
         for (int j = i; j < s.length(); j++) {
            int nextIndex = j + wordLength;

         }
      }
      return res;
   }
}
