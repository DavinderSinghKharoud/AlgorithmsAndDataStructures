package LeetCode.WeeklyContest256;

import java.util.*;

public class UniqueSubsequences {
   public static void main(String[] args) {
      UniqueSubsequences o = new UniqueSubsequences();
      System.out.println(o.numberOfUniqueGoodSubsequences("111"));
   }

   int mod = (int) 1e9 + 7;

   public int numberOfUniqueGoodSubsequences(String binary) {
      int len = binary.length();
      int end0 = 0, end1 = 0;
      boolean has0 = false;
      for (int i = 0; i < len; i++) {
         char c = binary.charAt(i);
         if (c == '0') {
            has0 = true;
            end0 = sum(end0, end1);
         } else {
            end1 = sum(end0, sum(end1, 1)); // one for single '1'
         }
      }
      return sum(end0, sum(end1, (has0) ? 1 : 0));// one for adding alone '0'
   }

   int sum(int a, int b) {
      int sum = a + b;
      while (sum < 0)
         sum += mod;
      return sum % mod;
   }
}
