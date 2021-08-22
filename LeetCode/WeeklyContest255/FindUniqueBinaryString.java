package LeetCode.WeeklyContest255;

import java.util.*;

public class FindUniqueBinaryString {
   public static void main(String[] args) {
      FindUniqueBinaryString o = new FindUniqueBinaryString();
      System.out.println(o.findDifferentBinaryString(new String[] { "111","011","001" }));
   }

   Set<String> set = new HashSet<>();

   public String findDifferentBinaryString(String[] nums) {
      for (String num : nums)
         set.add(num);

      return find(new StringBuilder(), nums[0].length());
   }

   String find(StringBuilder sbr, int len) {
      if (sbr.length() == len) {
         String s = sbr.toString();
         if (!set.contains(s)) {
            return s;
         }
         return null;
      } else {
         sbr.append('0');
         String s = find(sbr, len);
         if (s != null)
            return s;
         sbr.deleteCharAt(sbr.length() - 1);

         sbr.append('1');
         s = find(sbr, len);
         if (s != null)
            return s;
         sbr.deleteCharAt(sbr.length() - 1);
         return null;
      }
   }
}
