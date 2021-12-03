package Preparation;

import java.util.*;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * <p>
 * A good array is an array where the number of different integers in that array is exactly k.
 * <p>
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * Example 2:
 * <p>
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i], k <= nums.length
 */
public class SubarrayWithKDifferentIntegers {

    public static void main(String[] args) {

    }

    public int subarraysWithKDistinct(int[] nums, int k) {

        return getArraysWith(nums, k) - getArraysWith(nums, k - 1);
    }

    int getArraysWith(int[] nums, int k) {
        int len = nums.length;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int start = 0, end = 0; end < len; end++) {
            add(nums[end], map);
            //Check if it is not valid
            while (start < end && map.size() > k) {
                remove(nums[start++], map);
            }
            if (map.size() <= k) res += end - start + 1;

        }
        return res;
    }

    void add(int num, Map<Integer, Integer> map) {
        // System.out.println(num);
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    void remove(int num, Map<Integer, Integer> map) {
        //System.out.println(num);
        int count = map.get(num) - 1;
        if (count == 0) map.remove(num);
        else map.put(num, count);
    }
}
