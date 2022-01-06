package LeetCode;

import java.util.*;

/*
   You are given a 0-indexed integer array nums and a target element target.

FindingThreeDigitEvenNumbers target index is an index i such that nums[i] == target.

Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list. The returned list must be sorted in increasing order.

 

Example 1:

Input: nums = [1,2,5,2,3], target = 2
Output: [1,2]
Explanation: After sorting, nums is [1,2,2,3,5].
The indices where nums[i] == 2 are 1 and 2.
Example 2:

Input: nums = [1,2,5,2,3], target = 3
Output: [3]
Explanation: After sorting, nums is [1,2,2,3,5].
The index where nums[i] == 3 is 3.
   */
public class FindTargetIndicesAfterSortingArray {
   public static void main(String[] args) {
      System.out.println(new FindTargetIndicesAfterSortingArray().targetIndices(new int[] { 1, 2, 5, 2, 3 }, 2));
   }

   public List<Integer> targetIndices(int[] nums, int target) {
      int len = nums.length;
      int count = 0;
      int smaller = 0;
      for (int num : nums) {
         if (num < target)
            smaller++;
         else if (num == target)
            count++;
      }
      List<Integer> ans = new ArrayList<>();
      for (int i = 0; i < count; i++) {
         ans.add(smaller + i);
      }
      return ans;
   }

}
