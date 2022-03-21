package LeetCode;

import java.util.*;

/**
 * You are given an integer array nums.
 * <p>
 * You should move each element of nums into one of the two arrays CheckAllAsAppearsBs and NumberLastBeamsBank such that CheckAllAsAppearsBs and NumberLastBeamsBank are non-empty, and average(CheckAllAsAppearsBs) == average(NumberLastBeamsBank).
 * <p>
 * Return true if it is possible to achieve that and false otherwise.
 * <p>
 * Note that for an array arr, average(arr) is the sum of all the elements of arr over the length of arr.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7,8]
 * Output: true
 * Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have an average of 4.5.
 * Example 2:
 * <p>
 * Input: nums = [3,1]
 * Output: false
 */
public class Average {

    public static void main(String[] args) {
//        System.out.println(new Average().splitArraySameAverage(new int[]{
//                3863,703,1799,327,3682,4330,3388,6187,5330,6572,938,6842,678,9837,8256,6886,2204,5262,6643,829,745,8755,3549,6627,1633,4290,7
//        }));
        System.out.println(new Average().splitArraySameAverage(new int[]{
                1, 2}));
    }
    int[] nums;
    int len, sum;

    public boolean splitArraySameAverage(int[] nums) {
        len = nums.length;
        this.nums = nums;
        sum = Arrays.stream(nums).sum();
        Arrays.sort(nums);


        for (int count = 1; count <= len/2; count++) {
            if ((count * sum) % len == 0 && find(0, count, (count * sum) / len)) return true;
        }
        return false;
    }

    boolean find(int index, int count, int target) {
        if (count == 0) return target == 0;
        if (target < count * nums[index]) return false;

        for (int i = index; i <= len - count; i++) {
            if(i > index && nums[i - 1] == nums[i]) continue;
            if (nums[i] <= target && find(i + 1, count - 1, target - nums[i])) return true;
        }
        return false;
    }
}
