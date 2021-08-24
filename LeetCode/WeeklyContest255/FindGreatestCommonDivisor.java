package LeetCode.WeeklyContest255;

public class FindGreatestCommonDivisor {
   public static void main(String[] args) {
      FindGreatestCommonDivisor o = new FindGreatestCommonDivisor();
   }

   public int findGCD(int[] nums) {
      int min = nums[0], max = nums[0];
      for (int num : nums) {
         min = Math.min(min, num);
         max = Math.max(max, num);
      }

      return gcd(min, max);
   }

   int gcd(int a, int b) {
      return (a == 0) ? b : gcd(b % a, a);
   }

}
