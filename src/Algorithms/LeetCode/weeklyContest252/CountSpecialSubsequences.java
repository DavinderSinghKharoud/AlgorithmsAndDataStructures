package Algorithms.LeetCode.weeklyContest252;

public class CountSpecialSubsequences {
   public static void main(String[] args) {
      CountSpecialSubsequences o = new CountSpecialSubsequences();
      System.out.println(o.countSpecialSubsequences(new int[] { 0,1,2,0,1,2}));
   }

   int mod = (int) (1e9) + 7;

   public int countSpecialSubsequences(int[] nums) {

      int zeroes = 0;// Ends with zeroes
      int ones = 0; // Ends with ones
      int twos = 0; // Ends with twos

      for (int num : nums) {
         if (num == 0) {
            zeroes = sum(mul(2, zeroes), 1);
         } else if (num == 1) {
            ones = sum(mul(2, ones), zeroes);
         } else {
            twos = sum(mul(2, twos), ones);
         }
      }
      return twos;
   }

   int sum(int a, int b) {
      return (a + b) % mod;
   }

   int mul(int a, int b) {
      return (a * b) % mod;
   }
}
