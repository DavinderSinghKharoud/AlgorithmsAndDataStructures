package LeetCode.WeeklyContest257;

public class CountQuadruplets {
   public static void main(String[] args) {
      CountQuadruplets o = new CountQuadruplets();
       System.out.println(o.countQuadruplets(new int[]{35,15,38,1,10,26}));
   }

   public int countQuadruplets(int[] nums) {
      int count = 0;
      int len = nums.length;
      for (int i = len - 1; i >= 0; i--) {
         int first = nums[i];
         for (int j = i - 1; j >= 0; j--) {
            int second = first - nums[j];
            for (int k = j - 1; k >= 0; k--) {
               int third = second - nums[k];
               for (int l = k - 1; l >= 0; l--) {
                  if (third - nums[l] == 0)
                     count++;
               }
            }
         }
      }
      return count;
   }
}
