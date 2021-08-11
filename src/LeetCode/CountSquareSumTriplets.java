package LeetCode;

public class CountSquareSumTriplets {

   public static void main(String[] args) {
      CountSquareSumTriplets obj = new CountSquareSumTriplets();
      System.out.println(obj.countTriples(5));
   }

   public int countTriples(int n) {
      int count = 0;
      for (int i = 1; i <= n; i++) {
         for (int j = i + 1; j <= n; j++) {
            if (sqrt((long) i * i + (long) j * j) <= n)
               count++;
         }
      }
      return count * 2;
   }

   long sqrt(long a) {
      long sq = (long) Math.sqrt(a);
      if (sq * sq == a) {
         return sq;
      } else
         return Long.MAX_VALUE;
   }
}
