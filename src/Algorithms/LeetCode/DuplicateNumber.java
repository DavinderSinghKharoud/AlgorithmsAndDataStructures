package Algorithms.LeetCode;

import java.util.Arrays;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only
 * one duplicate number, find the duplicate one.
 */
public class DuplicateNumber {

    public static void main(String[] args) {

        System.out.println( findDuplicateLeet(new int[]{
                1,3,4,2,2
        }));

    }

    public static int findDuplicate(int[] nums) {

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                return nums[i];
            }
        }

        return -1;
    }

    /**
     * First off, we can easily show that the constraints of the problem imply that a cycle
     * must exist. Because each number in nums is between 11 and nn, it will necessarily
     * point to an index that exists. Therefore, the list can be traversed infinitely,
     * which implies that there is a cycle. Additionally, because 00 cannot appear as a
     * value in nums, nums[0] cannot be part of the cycle. Therefore, traversing the array
     * in this manner from nums[0] is equivalent to traversing a cyclic linked list.
     */
    public static int findDuplicateLeet(int[] nums) {
        // Find the intersection point of the two runners.
        int tortoise = nums[0];
        int hare = nums[0];
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Find the "entrance" to the cycle.
        int ptr1 = nums[0];
        int ptr2 = tortoise;
        while (ptr1 != ptr2) {
            ptr1 = nums[ptr1];
            ptr2 = nums[ptr2];
        }

        return ptr1;
    }
}
