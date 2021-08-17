package LeetCode;

import java.util.*;

public class MakeSumDivisibleByP {
   public static void main(String[] args) {
      MakeSumDivisibleByP o = new MakeSumDivisibleByP();
      // System.out.println(o.minSubarray(new int[] { 26, 19, 11, 14, 18, 4, 7, 1, 30,
      // 23, 19, 8, 10, 6, 26, 3 }, 26));

      System.out.println(o.minSubarray(new int[] { 3, 1, 4, 2 }, 6));
   }

   public int minSubarray(int[] nums, int p) {
      int rem = 0;
      for (int num : nums) {
         rem = (rem + num) % p;
      }
      if (rem == 0)
         return 0;

      Map<Integer, Integer> map = new HashMap<>();
      map.put(0, -1);

      int sum = 0;
      int res = Integer.MAX_VALUE;
      for (int i = 0; i < nums.length; i++) {
         sum = (sum + nums[i]) % p;
         int find = (sum - rem);
         if (find < 0)
            find += p;
         if (map.containsKey(find)) {
            res = Math.min(res, i - map.get(find));
         }
         map.put(sum, i);
      }
      return (res == nums.length) ? -1 : res;

   }

}
