package Algorithms.LeetCode.WeeklyContest240;

import java.util.*;

public class MaximumSubarrayMinProduct {
    public static void main(String[] args) {
        MaximumSubarrayMinProduct obj = new MaximumSubarrayMinProduct();
        System.out.println(obj.maxSumMinProduct(new int[]{1, 2, 3, 2}));
    }

    int mod = (int) 1e9 + 7;

    public int maxSumMinProduct(int[] nums) {
        int len = nums.length;
        long ans = -1;
        // We will consider each index as an min value,
        // Find the left and right min Index;
        // Find the prefix sum
        int[] left = new int[len];
        int[] right = new int[len];

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            int curr = nums[i];
            while (!stack.isEmpty() && nums[stack.peekLast()] >= curr) {
                stack.pollLast();
            }
            left[i] = (stack.isEmpty()) ? -1 : stack.peekLast();
            stack.addLast(i);
        }

        stack = new ArrayDeque<>();
        for (int i = len - 1; i >= 0; i--) {
            int curr = nums[i];
            while (!stack.isEmpty() && nums[stack.peekLast()] >= curr) {
                stack.pollLast();
            }
            right[i] = (stack.isEmpty()) ? len : stack.peekLast();
            stack.addLast(i);
        }

        long[] prefix = new long[len];
        // Find the prefix sum
        prefix[0] = nums[0];
        for (int i = 1; i < len; i++) {
            prefix[i] += prefix[i - 1] + nums[i];
        }

        // Take each nums value as min
        for (int i = 0; i < len; i++) {
            long sum = prefix[right[i] - 1] - ((left[i] >= 0) ? prefix[left[i]] : 0);
            ans = Math.max(ans, sum * nums[i]);
        }
        return (int) (ans % mod);
    }
}
