package LeetCode;

import java.util.*;

public class FirstAndLastPosition {

    public static void main(String[] args) {
        FirstAndLastPosition o = new FirstAndLastPosition();
        System.out.println(Arrays.toString(o.searchRange(new int[]{5,7,7,8,8,10}, 5)));
    }

    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1, -1};
        int lower = lowest(nums, target);
        int upper = upper(nums, target);
        return new int[]{lower, upper};
    }

    int lowest(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (end + start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return (nums[end] == target) ? end : -1;
    }

    int upper(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            int mid = (end + start + 1) / 2;
            if (nums[mid] <= target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return (nums[start] == target) ? end : -1;
    }
}
