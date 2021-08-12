package LeetCode;

import java.util.*;

public class UglyNumberIII {

   public static void main(String[] args) {
       UglyNumberIII o = new UglyNumberIII();
     //  System.out.println(o.nthUglyNumber(5, 2, 3,3));
       System.out.println(o.nthUglyNumber(3, 2, 3,5));
   }

   public int nthUglyNumber(int n, int a, int b, int c) {
      // Find lcm
      long a_b = ((long) a * b) / find(a, b);
      long a_c =  ((long) a * c) / find(a, c);
      long b_c =  ((long) b * c) / find(b, c);
      long a_b_c =  (a_b * c) / find((int)a_b, c);

      int start = 1, end = 2 * (int) (1e9) + 1;
      while (start < end) {
         int mid = (((end - start) >> 1)) + start;
         long count = mid / a + mid / b + mid / c - mid / a_b - mid / a_c - mid / b_c + mid / a_b_c;
         if (count >= n) {
            end = mid;
         } else {
            start = mid + 1;
         }
      }

      return end;
   }

   int find(int a, int b) {
      if (a < b)
         a = a ^ b ^ (b = a);
      return gcd(a, b);
   }

   int gcd(int a, int b) {
      return (a == 0) ? b : gcd(b % a, a);
   }
}
