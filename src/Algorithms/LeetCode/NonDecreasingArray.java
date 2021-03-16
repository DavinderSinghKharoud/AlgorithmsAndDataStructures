package Algorithms.LeetCode;

public class NonDecreasingArray {

   public static void main(String[] args) {
      System.out.println(checkPossibility(new int[] { 4, 2, 3 }));
   }

   public static boolean checkPossibility(int[] nums) {

      int index = -1;

      for (int i = 0; i < nums.length - 1; i++) {
         if (nums[i] > nums[i + 1]) {
            if (index != -1)
               return false;
            index = i;
         }
      }

      return (index == 0 || index == nums.length - 2 || index == -1) || (nums[index - 1] <= nums[index + 1])
            || (nums[index] <= nums[index + 2]);
   }

}
