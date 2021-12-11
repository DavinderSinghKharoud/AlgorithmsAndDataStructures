package Preparation;

/**
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 */
public class kthLargestOrKthSmallest {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        //We will use quick select
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    int quickSelect(int[] nums, int start, int end, int k) {
        int pivot = nums[end];
        int index = start;

        for (int i = start; i < end; i++) {
            if (nums[i] <= pivot) {
                //swap
                swap(nums, index, i);
                index++;
            }
        }
        swap(nums, index, end);
        //System.out.println(nums[index] + " " + (end - index));
        if (end - index == k - 1) return nums[index];
        if (end - index < k) {
            return quickSelect(nums, start, index - 1, k - (end - index + 1));
        } else {
            return quickSelect(nums, index + 1, end, k);
        }
    }

    void swap(int[] nums, int a, int b) {
        nums[a] = nums[a] ^ nums[b] ^ (nums[b] = nums[a]);
    }
}
