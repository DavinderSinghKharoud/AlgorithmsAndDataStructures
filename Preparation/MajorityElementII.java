package Preparation;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,3]
 * Output: [3]
 * Example 2:
 * <p>
 * Input: nums = [1]
 * Output: [1]
 * Example 3:
 * <p>
 * Input: nums = [1,2]
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 */

import java.util.*;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        int len = nums.length;
        int first = 0;
        int second = 0;
        int count1 = 0, count2 = 0;

        for (int num : nums) {
            if (num == first) count1++;
            else if (num == second) count2++;
            else if (count1 == 0) {
                count1 = 1;
                first = num;
            } else if (count2 == 0) {
                count2 = 1;
                second = num;
            } else {
                count1--;
                count2--;
            }
        }

        List<Integer> ans = new ArrayList<>();
        if (first != second) {
            if (getCount(nums, second) > len / 3) ans.add(second);
        }
        if (getCount(nums, first) > len / 3) ans.add(first);
        return ans;
    }

    int getCount(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) count++;
        }
        return count;
    }
}
