package Preparation;

import java.util.*;

public class FindDuplicate {
    public int findDuplicate(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        int len = nums.length;
        int maxNum = len - 1;
        return sum - (maxNum * (maxNum + 1)) / 2;
    }

    public int findDuplicate2(int[] nums) {
        int len = nums.length;
        int start = 1, end = len;

        while (start < end) {
            int mid = (end + start) / 2;
            int lesserCount = getLesserCount(mid, nums);
            if (lesserCount > mid) {
                end = mid;
            } else start = mid + 1;
        }

        return end;
    }

    //Inclusive
    int getLesserCount(int target, int[] nums) {
        int count = 0;
        for (int num : nums) {
            if (num <= target) count++;
        }
        return count;
    }


    public int findDuplicate3(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int curr = Math.abs(nums[i]);
            if (nums[curr] < 0) return curr;
            nums[curr] = -1 * nums[curr];
        }
        return nums[0];
    }
    /**
     * An explanation about finding the entry point part.
     * First assume when fast and slow meet, slow has moved a steps, and fast has moved 2a steps. They meet in the circle, so the difference a must be a multiple of the length of the circle.
     * Next assume the distance between beginning to the entry point is x, then we know that the slow has traveled in the circle for a-x steps.
     * How do we find the entry point? Just let slow move for another x steps, then slow will have moved a steps in the circle, which is a multiple of length of the circle.
     * So we start another pointer at the beginning and let slow move with it. Remember x is the distance between beginning to the entry point, after x steps, both pointer will meet at the entry of circle.
     *
     * @param nums
     * @return
     */
    public int findDuplicate4(int[] nums) {
        int len = nums.length;
        int hare = nums[nums[0]], tortoise = nums[0];
        while (hare != tortoise) {
            hare = nums[nums[hare]];
            tortoise = nums[tortoise];
        }

        tortoise = 0;
        while (tortoise != hare) {
            hare = nums[hare];
            tortoise = nums[tortoise];
        }
        return hare;
    }
}
