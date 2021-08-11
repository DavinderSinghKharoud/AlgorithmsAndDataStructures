package LeetCode.Biweekly53;

import java.util.*;

public class SubstringSizeThree {
   public static void main(String[] args) {
    SubstringSizeThree b = new SubstringSizeThree();
       System.out.println(b.countGoodSubstrings("xyzzaz"));
   }

   public int countGoodSubstrings(String s) {
      int res = 0;
      for (int i = 0; i < s.length() - 2; i++) {
         Set<Character> set = new HashSet<>();
         boolean a = true;
         for (int j = i; j < i + 3; j++) {
            if (set.contains(s.charAt(j))) {
               a = false;
               break;
            }
            set.add(s.charAt(j));
         }
         if (a)
            res++;
      }
      return res;
   }
}
