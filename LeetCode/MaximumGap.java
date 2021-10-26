package LeetCode;

import java.util.*;

/**
 * Given an integer array nums, return the maximum difference between two successive elements in its sorted form. If the array contains less than two elements, return 0.
 *
 * You must write an algorithm that runs in linear time and uses linear extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,6,9,1]
 * Output: 3
 * Explanation: The sorted form of the array is [1,3,6,9], either (3,6) or (6,9) has the maximum difference 3.
 * Example 2:
 *
 * Input: nums = [10]
 * Output: 0
 * Explanation: The array contains less than 2 elements, therefore return 0.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 109
 */
public class MaximumGap {
    public int maximumGap(int[] nums) {
        int len = nums.length;

        //Use radix sort
        int digitsCount = (int) (Math.log10(Arrays.stream(nums).max().getAsInt()) + 1);
        int index = 0; //From right side
        while (index < digitsCount) {
            nums = countingSort(nums, index, len);
            index++;
        }
        int max = 0;
        for (int i = 1; i < len; i++) {
            max = Math.max(nums[i] - nums[i - 1], max);
        }

        return max;
    }

    int[] countingSort(int[] nums, int index, int len) {
        int[] count = new int[10];
        for (int num : nums) {
            int digit = getDigit(num, index);
            count[digit]++;
        }

        int[] prefix = new int[count.length + 1];
        for (int i = 0; i < count.length; i++) {
            prefix[i + 1] = prefix[i] + count[i];
        }

        int[] res = new int[prefix[prefix.length - 1]];


        for (int num : nums) {
            int digit = getDigit(num, index);
            int start = prefix[digit];
            int fitIndex = start;
            res[fitIndex] = num;
            prefix[digit]++;

        }

        return res;
    }

    int getDigit(int num, int index) {
        int div = (int) Math.pow(10, index);
        if (div <= 0) div = 1;
        return (num / div) % 10;
    }
}
