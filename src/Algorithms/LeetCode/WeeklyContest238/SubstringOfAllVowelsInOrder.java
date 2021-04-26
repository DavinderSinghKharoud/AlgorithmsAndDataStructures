package Algorithms.LeetCode.WeeklyContest238;

import java.util.*;

public class SubstringOfAllVowelsInOrder {

   public static void main(String[] args) {
      SubstringOfAllVowelsInOrder o = new SubstringOfAllVowelsInOrder();
      System.out.println(o.longestBeautifulSubstring("aeiosdfddddddu"));
   }

   public int longestBeautifulSubstring(String word) {
      char[] vowels = new char[] { 'a', 'e', 'i', 'o', 'u' };
      Set<Character> set = new HashSet<>();
      for (char c : vowels) {
         set.add(c);
      }
      int start = -1;
      int curr = 0;
      int ans = 0;
      for (int i = 0; i < word.length(); i++) {
         char c = word.charAt(i);
         if (curr == vowels.length) {
            if (set.contains(c) && curr != vowels[vowels.length - 1]) {
               curr = 0;
               continue;
            }
            ans = Math.max(ans, i - start + 1);
            continue;
         }
         if (!set.contains(c))
            continue;
         if (c == vowels[curr]) {
            if (c == 'a')
               start = i;
            boolean isMoved = false;
            while (i < word.length() && word.charAt(i) == c) {
               isMoved = true;
               i++;
            }
            if (isMoved)
               i--;
            curr++;
            if (curr == vowels.length)
               ans = Math.max(ans, i - start + 1);
         } else {
            curr = 0;
            if (c == 'a') {
               start = i;
               boolean isMoved = false;
               while (i < word.length() && word.charAt(i) == c) {
                  isMoved = true;
                  i++;
               }
               if (isMoved)
                  i--;
               curr++;
            }
         }
      }
      return ans;
   }
}
