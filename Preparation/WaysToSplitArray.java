package Preparation;

/**
 * A split of an integer array is good if:
 * <p>
 * The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
 * The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
 * Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 1
 * Explanation: The only good way to split nums is [1] [1] [1].
 * Example 2:
 * <p>
 * Input: nums = [1,2,2,2,5,0]
 * Output: 3
 * Explanation: There are three good ways of splitting nums:
 * [1] [2] [2,2,5,0]
 * [1] [2,2] [2,5,0]
 * [1,2] [2,2] [5,0]
 * Example 3:
 * <p>
 * Input: nums = [3,2,1]
 * Output: 0
 * Explanation: There is no good way to split nums.
 */
public class WaysToSplitArray {

    public static void main(String[] args) {
        System.out.println(new WaysToSplitArray().waysToSplit(new int[]{
                3, 2, 1
        }));
    }

    int mod = (int) (1e9 + 7);
    int[] presum;

    public int waysToSplit(int[] nums) {
        long ways = 0;
        int len = nums.length;
        presum = new int[len];
        for (int i = 0; i < len; i++) {
            if (i == 0) presum[0] = nums[0];
            else presum[i] += presum[i - 1] + nums[i];
        }

        for (int i = 0; i < len - 2; i++) { //inclusive
            int currSum = getSum(0, i);
            //find the left and right poss
            int left = getIndex(currSum, i + 1, nums, true);
            int right = getIndex(currSum, i + 1, nums, false);
            if (left == -1 && right == -1) continue;
            ways = (right - left + 1 + ways) % mod;
        }
        return (int) ways;
    }

    int getIndex(int leftSum, int index, int[] nums, boolean findLeft) {
        int start = index, end = nums.length - 2;
        int res = -1;
        while (start <= end) {
            int mid = (end + start) / 2;
            int midSum = getSum(index, mid);
            int rightSum = getSum(mid + 1, nums.length - 1);

            if (midSum >= leftSum && midSum <= rightSum) {
                res = mid;
                if (findLeft) end = mid - 1;
                else start = mid + 1;
            } else if (leftSum > midSum) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

    int getSum(int a, int b) {
        if (a > b) return 0;
        if (a - 1 < 0) return presum[b];
        return presum[b] - presum[a - 1];
    }
}
