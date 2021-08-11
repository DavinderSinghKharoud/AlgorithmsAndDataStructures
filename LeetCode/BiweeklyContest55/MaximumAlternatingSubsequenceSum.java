package LeetCode.BiweeklyContest55;

public class MaximumAlternatingSubsequenceSum {
   public static void main(String[] args) {
      MaximumAlternatingSubsequenceSum o = new MaximumAlternatingSubsequenceSum();
      System.out.println(o.maxAlternatingSum2(new int[] { 5, 6, 7, 8 }));
   }

   public long maxAlternatingSum(int[] nums) {
      int len = nums.length;
      long[][] dp = new long[len][2];
      return dfs(nums, 0, dp, 0);
   }

   public long dfs(int[] nums, int index, long[][] dp, int state) {
      if (index >= nums.length)
         return 0;
      if (dp[index][state] == 0) {
         int curr = (state == 0) ? nums[index] : -nums[index];
         dp[index][state] = Math.max(curr + dfs(nums, index + 1, dp, (state == 0) ? 1 : 0),
               dfs(nums, index + 1, dp, state));
      }
      return dp[index][state];
   }

   public long maxAlternatingSum2(int[] nums) {
      long odd = 0, even = 0;
      for (int i = nums.length - 1; i >= 0; i--) {
         long tempEven = Math.max(odd + nums[i], even);
         long tempOdd = Math.max(even - nums[i], odd);
         even = tempEven;
         odd = tempOdd;
      }
      return even;
   }
}
