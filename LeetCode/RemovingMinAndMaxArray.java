
package LeetCode;

import java.util.*;

/**
 * You are given a 0-indexed array of distinct integers nums.
 * <p>
 * There is an element in nums that has the lowest value and an element that has the highest value. We call them the minimum and maximum respectively. Your goal is to remove both these elements from the array.
 * <p>
 * A deletion is defined as either removing an element from the front of the array or removing an element from the back of the array.
 * <p>
 * Return the minimum number of deletions it would take to remove both the minimum and maximum element from the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,10,7,5,4,1,8,6]
 * Output: 5
 * Explanation:
 * The minimum element in the array is nums[5], which is 1.
 * The maximum element in the array is nums[1], which is 10.
 * We can remove both the minimum and maximum by removing 2 elements from the front and 3 elements from the back.
 * This results in 2 + 3 = 5 deletions, which is the minimum number possible.
 * Example 2:
 * <p>
 * Input: nums = [0,-4,19,1,8,-2,-3,5]
 * Output: 3
 * Explanation:
 * The minimum element in the array is nums[1], which is -4.
 * The maximum element in the array is nums[2], which is 19.
 * We can remove both the minimum and maximum by removing 3 elements from the front.
 * This results in only 3 deletions, which is the minimum number possible.
 * Example 3:
 * <p>
 * Input: nums = [101]
 * Output: 1
 * Explanation:
 * There is only one element in the array, which makes it both the minimum and maximum element.
 * We can remove it with 1 deletion.
 */
public class RemovingMinAndMaxArray {

    public static void main(String[] args) {
        System.out.println(new RemovingMinAndMaxArray().minimumDeletions(
                new int[]{2, 10, 7, 5, 4, 1, 8, 6}
        ));
    }

    public int minimumDeletions(int[] nums) {
        int len = nums.length;
        int minIndex = 0, maxIndex = 0;
        for (int i = 0; i < len; i++) {
            int curr = nums[i];
            if (curr < nums[minIndex]) {
                minIndex = i;
            }
            if (curr > nums[maxIndex])
                maxIndex = i;
        }

        return Math.min(find(nums, nums[minIndex], nums[maxIndex], 0, true),
                find(nums, nums[minIndex], nums[maxIndex], 0, false));
    }

    int find(int[] nums, int minNum, int maxNum, int step, boolean isMin) {
        int len = nums.length;
        int total = 0;
        int target = (isMin) ? minNum : maxNum;

        boolean found = false;
        // try left
        for (int i = 0; i < len; i++) {
            total++;
            if (nums[i] == target) {
                found = true;
                break;
            }
        }

        if (!found)
            return step;
        int[] copy = Arrays.copyOf(nums, len);
        for (int i = 0; i < total; i++)
            copy[i] = Integer.MIN_VALUE;
        int min = find(copy, minNum, maxNum, step + total, !isMin);

        total = 0;
        for (int i = len - 1; i >= 0; i--) {
            total++;
            if (nums[i] == target) {
                break;
            }
        }
        copy = Arrays.copyOf(nums, len);
        for (int i = len - total; i < len; i++) {
            copy[i] = Integer.MIN_VALUE;
        }

        return Math.min(min, find(copy, minNum, maxNum, step + total, !isMin));
    }

}
