package Algorithms.LeetCode;

import java.util.*;

/**
 * Given an input string s, reverse the order of the words.
 *
 * A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
 *
 * Return a string of the words in reverse order concatenated by a single space.
 *
 * Note that s may contain leading or trailing spaces or multiple spaces between two words. The returned string should only have a single space separating the words. Do not include any extra spaces.
 */
public class ReverseWordsInAString {
   public static void main(String[] args) {

       System.out.print(reverseWords2("the sky is blue"));
   }

   //Time and space complexity O(N)
   public static String reverseWords(String s) {
      ArrayDeque<String> stack = new ArrayDeque<>();

      StringBuilder sbr = new StringBuilder();
      for (char c : s.toCharArray()) {
         if (c == ' ') {
            if (sbr.length() != 0) {
               stack.addLast(sbr.toString());
            }
            sbr = new StringBuilder();
         } else {
            sbr.append(c);
         }
      }
      if(sbr.length() != 0){
          stack.add(sbr.toString());
      }
      sbr = new StringBuilder();
      while (!stack.isEmpty()) {
         sbr.append(stack.pollLast()).append(" ");
      }
      if (!sbr.isEmpty()) {
         sbr.deleteCharAt(sbr.length() - 1);
      }
      return sbr.toString();
   }

    public static String reverseWords2(String s) {
       StringBuilder res = new StringBuilder();

        StringBuilder sbr = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (sbr.length() != 0) {
                    res.insert(0, " ").insert(0, sbr.toString());
                }
                sbr = new StringBuilder();
            } else {
                sbr.append(c);
            }
        }
        if(sbr.length() != 0){
            res.insert(0, " ").insert(0, sbr.toString());
        }
        if (!res.isEmpty()) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }
}
