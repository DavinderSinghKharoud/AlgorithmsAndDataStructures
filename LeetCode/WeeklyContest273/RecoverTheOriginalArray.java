package LeetCode.WeeklyContest273;

import java.util.*;

/**
 * Alice had a 0-indexed array arr consisting of n positive integers. She chose an arbitrary positive integer k and created two new 0-indexed integer arrays lower and higher in the following manner:
 * <p>
 * lower[i] = arr[i] - k, for every index i where 0 <= i < n
 * higher[i] = arr[i] + k, for every index i where 0 <= i < n
 * Unfortunately, Alice lost all three arrays. However, she remembers the integers that were present in the arrays lower and higher, but not the array each integer belonged to. Help Alice and recover the original array.
 * <p>
 * Given an array nums consisting of 2n integers, where exactly n of the integers were present in lower and the remaining in higher, return the original array arr. In case the answer is not unique, return any valid array.
 * <p>
 * Note: The test cases are generated such that there exists at least one valid array arr.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,10,6,4,8,12]
 * Output: [3,7,11]
 * Explanation:
 * If arr = [3,7,11] and k = 1, we get lower = [2,6,10] and higher = [4,8,12].
 * Combining lower and higher gives us [2,6,10,4,8,12], which is a permutation of nums.
 * Another valid possibility is that arr = [5,7,9] and k = 3. In that case, lower = [2,4,6] and higher = [8,10,12].
 * Example 2:
 * <p>
 * Input: nums = [1,1,3,3]
 * Output: [2,2]
 * Explanation:
 * If arr = [2,2] and k = 1, we get lower = [1,1] and higher = [3,3].
 * Combining lower and higher gives us [1,1,3,3], which is equal to nums.
 * Note that arr cannot be [1,3] because in that case, the only possible way to obtain [1,1,3,3] is with k = 0.
 * This is invalid since k must be positive.
 * Example 3:
 * <p>
 * Input: nums = [5,435]
 * Output: [220]
 * Explanation:
 * The only possible combination is arr = [220] and k = 215. Using them, we get lower = [5] and higher = [435].
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 * n == nums.length
 * 1 <= n <= 1000
 * 1 <= nums[i] <= 109
 * The test cases are generated such that there exists at least one valid array arr.
 */
public class RecoverTheOriginalArray {
    public static void main(String[] args) {
        RecoverTheOriginalArray o = new RecoverTheOriginalArray();
        System.out.println(Arrays.toString(o.recoverArray(new int[]{
                11, 6, 3, 4, 8, 7, 8, 7, 9, 8, 9, 10, 10, 2, 1, 9
        })));
    }

    public int[] recoverArray(int[] nums) {
        int len = nums.length;
        Arrays.sort(nums);
        // try every k possible b/w pairs;
        int first = nums[0];
        for (int i = 1; i < len; i++) {
            int diff = Math.abs(first - nums[i]);
            if (diff <= 1) continue;
            if (isPossible(diff, nums)) {
                return findAns(nums, diff);
            }
        }
        return null;
    }

    int[] findAns(int[] nums, int k) {
        int len = nums.length;
        int[] ans = new int[len / 2];
        int i = 0;
        int half = k / 2;
        int first = 0, second = 1;
        while (first < len && second < len && i < len / 2) {
            while (first < len && nums[first] == -1)
                first++;
            while (second < len && ((second <= first) || nums[second] == -1 || nums[first] + k != nums[second]))
                second++;
            ans[i++] = nums[first] + half;
            nums[first] = -1;
            nums[second] = -1;
            first++;
            second++;
        }
        return ans;
    }

    boolean isPossible(int val, int[] nums) {
        int len = nums.length;
        nums = Arrays.copyOf(nums, len);

        int first = 0, second = 1;
        while (first < len && second < len) {
            while (first < len && nums[first] == -1)
                first++;
            while (second < len && ((second <= first) || nums[second] == -1 || nums[first] + val != nums[second]))
                second++;
            if (first < len && second < len) {
                nums[first] = -1;
                nums[second] = -1;
                first++;
                second++;
            }
        }

        for (int num : nums) {
            if (num != -1)
                return false;
        }
        return true;
    }
}
