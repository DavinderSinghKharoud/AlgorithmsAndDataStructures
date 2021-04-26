package Algorithms.LeetCode.WeeklyContest238;

public class SumOfDigitsInBaseK {
   public static void main(String[] args) {

      SumOfDigitsInBaseK sumOfDigitsInBaseK = new SumOfDigitsInBaseK();
      System.out.println(sumOfDigitsInBaseK.sumBase(68, 2));
   }

   public int sumBase(int n, int k) {
      int ans = 0;
      while (n / k > 0) {
         ans += n % k;
         n /= k;
      }
      return ans + n;
   }
}
