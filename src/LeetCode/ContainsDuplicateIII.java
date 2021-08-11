package LeetCode;

import java.util.*;

public class ContainsDuplicateIII {

   public static void main(String[] args) {
      System.out.println(containsNearbyAlmostDuplicate(new int[] { 1,2,3,1}, 3, 0));
   }

   //Time complexity O(n * log(k) )
   public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int diff) {
      k++;
      int len = nums.length;
      TreeMap<Long, Long> map = new TreeMap<>();
      for (int i = 0; i < Math.min(k, len); i++) {
         long curr = nums[i];
         if (map.ceilingKey(curr - diff) != null && Math.abs(map.ceilingKey(curr - diff) - curr) <= diff)
            return true;
         if (map.floorKey(curr + diff) != null && Math.abs(map.floorKey(curr + diff) - curr) <= diff)
            return true;
         map.put(curr, map.getOrDefault(curr, 0L) + 1);
      }

      int end = k;

      for (; end < len; end++) {
         long count = map.get((long) nums[end - k]) - 1;
         if (count == 0) {
            map.remove((long) nums[end - k]);
         } else {
            map.put((long) nums[end - k], count);
         }
         long curr = nums[end];
         if (map.ceilingKey(curr - diff) != null && Math.abs(map.ceilingKey(curr - diff) - curr) <= diff)
            return true;
         if (map.floorKey(curr + diff) != null && Math.abs(map.floorKey(curr + diff) - curr) <= diff)
            return true;
         map.put(curr, map.getOrDefault(curr, 0L) + 1);
      }

      return false;
   }

   //Time Complexity O(n square)
   public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
      if(nums.length>9999)
      {
         return false;
      }
      for(int i=0;i<nums.length;i++){
         for(int j=i+1;j<nums.length;j++){
            if(Math.abs((long)nums[i]-(long)nums[j])<=t && Math.abs(i-j)<=k){
               return true;
            }
         }
      }

      return false;
   }
}
