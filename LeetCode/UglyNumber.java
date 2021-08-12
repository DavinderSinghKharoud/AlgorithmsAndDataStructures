package Algorithms.LeetCode;

import java.util.*;

public class UglyNumber {

   public static void main(String[] args) {

   }

   public boolean isUgly(int n) {
      if (n < 0)
         return false;
      int[] arr = new int[] { 2, 3, 5 };
      for (int i = 0; i < arr.length; i++) {
         while ((n > 1) && n % arr[i] == 0) {
            n /= arr[i];
         }
      }
      return n == 1;
   }
}
