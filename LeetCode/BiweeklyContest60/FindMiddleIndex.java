package LeetCode.BiweeklyContest60;

public class FindMiddleIndex {
   public static void main(String[] args) {
      FindMiddleIndex o = new FindMiddleIndex();
   }

   public int findMiddleIndex(int[] nums) {
      int len = nums.length;
      int[] left = new int[len + 2], right = new int[len + 2];
      for (int i = 0; i < len; i++) {
         left[i + 1] = left[i] + nums[i];
      }
      for (int i = len - 1; i >= 0; i--) {
         right[i + 1] = right[i + 2] + nums[i];
      }

      for (int i = 1; i <= len; i++) {
         if(left[i - 1] == right[i + 1]) return i - 1;
      }
      return -1;


   }
}
