package LeetCode.WeeklyContest260;

public class MaximumDifferenceBetweenIncreasingElements {
   public static void main(String[] args) {
      MaximumDifferenceBetweenIncreasingElements o = new MaximumDifferenceBetweenIncreasingElements();
   }

   public int maximumDifference(int[] nums) {
      int max = -1;

      for (int i = 0; i < nums.length; i++) {
         for (int j = i + 1; j < nums.length; j++) {
            max = Math.max(max, nums[j] - nums[i]);
         }
      }
      return (max <= 0) ? -1 : max;
   }
}
