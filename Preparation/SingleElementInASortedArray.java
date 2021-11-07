package Preparation;

/**
 * You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
 *
 * Return the single element that appears only once.
 *
 * Your solution must run in O(log n) time and O(1) space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 */
public class SingleElementInASortedArray {

    public static void main(String[] args) {

    }

    public int singleNonDuplicate(int[] nums) {
        int len = nums.length;
        int start = 0, end = len - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            int currLen = mid + 1;
            boolean isEven = (currLen % 2 == 0);
            int curr = nums[mid];
            if (isEven) {
                int prev = nums[mid - 1];
                if (prev == curr) {
                    start = mid + 1;
                } else end = mid;
            } else {
                int next = nums[mid + 1];
                if (next == curr) {
                    start = mid + 1;
                } else end = mid;
            }
        }
        return nums[start];
    }
}
