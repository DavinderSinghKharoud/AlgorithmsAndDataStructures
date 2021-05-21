package Algorithms.LeetCode.Weekly241;

public class SumofAllSubsetXORTotals {
   public static void main(String[] args) {
   SumofAllSubsetXORTotals o = new SumofAllSubsetXORTotals();
      System.out.println(o.subsetXORSum(new int[]{5,1,6}));
   }

   public int subsetXORSum(int[] nums) {
      int res = 0;

      int numOfSubsets = 1 << nums.length;

      for (int i = 0; i < numOfSubsets; i++) {
         int pos = nums.length - 1;
         int bitmask = i;
         int curr = -1;
         while (bitmask > 0) {
            if ((bitmask & 1) == 1) {
               if (curr == -1) {
                  curr = nums[pos];
               } else {
                  curr = curr ^ nums[pos];
               }
            }
            bitmask >>= 1;
            pos--;
         }
         if (curr != -1) {
            res += curr;
         }
      }
      return res;
   }

   private static void findSubsets(int array[]) {
      int numOfSubsets = 1 << array.length;

      for (int i = 0; i < numOfSubsets; i++) {
         int pos = array.length - 1;
         int bitmask = i;

         System.out.print("{");
         while (bitmask > 0) {
            if ((bitmask & 1) == 1)
               System.out.print(array[pos] + ",");
            bitmask >>= 1;
            pos--;
         }
         System.out.print("}");
      }
   }
}
