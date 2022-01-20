package LeetCode.WeeklyContest275;

/**
 * DivideStringKGroups swap is defined as taking two distinct positions in an array and swapping the values in them.
 *
 * DivideStringKGroups circular array is defined as an array where we consider the first element and the last element to be adjacent.
 *
 * Given a binary circular array nums, return the minimum number of swaps required to group all 1's present in the array together at any location.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [0,1,0,1,1,0,0]
 * Output: 1
 * Explanation: Here are a few of the ways to group all the 1's together:
 * [0,0,1,1,1,0,0] using 1 swap.
 * [0,1,1,1,0,0,0] using 1 swap.
 * [1,1,0,0,0,0,1] using 2 swaps (using the circular property of the array).
 * There is no way to group all 1's together with 0 swaps.
 * Thus, the minimum number of swaps required is 1.
 * Example 2:
 *
 * Input: nums = [0,1,1,1,0,0,1,1,0]
 * Output: 2
 * Explanation: Here are a few of the ways to group all the 1's together:
 * [1,1,1,0,0,0,0,1,1] using 2 swaps (using the circular property of the array).
 * [1,1,1,1,1,0,0,0,0] using 2 swaps.
 * There is no way to group all 1's together with 0 or 1 swaps.
 * Thus, the minimum number of swaps required is 2.
 * Example 3:
 *
 * Input: nums = [1,1,0,0,1]
 * Output: 0
 * Explanation: All the 1's are already grouped together due to the circular property of the array.
 * Thus, the minimum number of swaps required is 0.
 */
public class MinimumSwaps {
    public static void main(String[] args) {
        MinimumSwaps o = new MinimumSwaps();
        System.out.println(o.minSwaps(new int[]{
                0,1,1,1,0,0,1,1,0
        }));
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        int ones = 0;
        for (int num : nums) {
            if (num == 1) ones++;
        }

        int[] arr = new int[2 * n];
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i + n] = nums[i];
        }

        int count = 0;

        for (int i = 0; i < ones; i++) {
            if (arr[i] == 1) count++;
        }
        int min = ones - count;
        for (int i = ones; i < arr.length; i++) {
            if (arr[i - ones] == 1) count--;
            if (arr[i] == 1) count++;
            min = Math.min(min, ones - count);
        }
        return min;
    }
}
