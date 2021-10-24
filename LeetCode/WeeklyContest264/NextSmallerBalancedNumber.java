package LeetCode.WeeklyContest264;

import java.util.ArrayList;
import java.util.List;

public class NextSmallerBalancedNumber {
   public static void main(String[] args) {
      NextSmallerBalancedNumber o = new NextSmallerBalancedNumber();
      System.out.println(o.nextBeautifulNumber2(12));
   }

   public int nextBeautifulNumber2(int n) {
      if(n == 0) return 1;
      int digitsCount = (int) (Math.log10(n) + 1);

      for (int len = digitsCount; len <= digitsCount + 1; len++) {
         int[] count = new int[len + 1];
         List<Integer> lst = new ArrayList<>();
         backTracking(0, len, count, 0, lst);
         for (int num : lst) {
            if (num > n)
               return num;
         }
      }
      return -1;
   }

   void backTracking(int index, int len, int[] count, int num, List<Integer> lst) {
      if (index == len) {
         if (isValid(num)) {
            lst.add(num);
         }
         return;
      }

      for (int digit = 1; digit <= len; digit++) {
         if (count[digit] >= digit)
            continue;
         if (count[digit] + (len - index) < digit)
            continue;
         count[digit]++;
         backTracking(index + 1, len, count, num * 10 + digit, lst);
         count[digit]--;
      }
   }

   public int nextBeautifulNumber(int n) {
      n++;
      while (!isValid(n)) {
         n++;
      }
      return n;
   }

   boolean isValid(int n) {
      int[] count = new int[10];
      while (n > 0) {
         int last = n % 10;
         count[last]++;
         n /= 10;
      }

      for (int i = 0; i < count.length; i++) {
         if (count[i] > 0 && count[i] != i) {
            return false;
         }
      }
      return true;
   }
}
