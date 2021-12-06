package Preparation;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers nums and an integer k. FindingThreeDigitEvenNumbers continuous subarray is called nice if there are k odd numbers on it.
 * <p>
 * Return the number of nice sub-arrays.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 * <p>
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There is no odd numbers in the array.
 * Example 3:
 * <p>
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */
public class NumberOfNiceSubArrays {
    public int numberOfSubarrays(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) nums[i] = 0;
            else nums[i] = 1; //for odd
        }

        return getSubArr(nums, k) - getSubArr(nums, k - 1);
    }

    int getSubArr(int[] nums, int k) {
        int res = 0;
        int oddCount = 0;

        for (int start = 0, end = 0; end < nums.length; end++) {
            oddCount += nums[end];
            while (start < end && oddCount > k) oddCount -= (nums[start++]);
            if (oddCount <= k) res += (end - start + 1);
        }
        //System.out.println(res);
        return res;
    }

    public int numberOfSubarrays2(int[] nums, int k) {
        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 2 == 0) nums[i] = 0;
            else nums[i] = 1; //for odd
        }
        int res =0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            if(map.containsKey(sum - k)){
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
