package LeetCode.WeeklyContest280;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given an integer array nums of length n and an integer numSlots such that 2 * numSlots >= n. There are numSlots slots numbered from 1 to numSlots.
 *
 * You have to place all n integers into the slots such that each slot contains at most two numbers. The AND sum of a given placement is the sum of the bitwise AND of every number with its respective slot number.
 *
 * For example, the AND sum of placing the numbers [1, 3] into slot 1 and [4, 6] into slot 2 is equal to (1 AND 1) + (3 AND 1) + (4 AND 2) + (6 AND 2) = 1 + 1 + 0 + 2 = 4.
 * Return the maximum possible AND sum of nums given numSlots slots.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5,6], numSlots = 3
 * Output: 9
 * Explanation: One possible placement is [1, 4] into slot 1, [2, 6] into slot 2, and [3, 5] into slot 3.
 * This gives the maximum AND sum of (1 AND 1) + (4 AND 1) + (2 AND 2) + (6 AND 2) + (3 AND 3) + (5 AND 3) = 1 + 0 + 2 + 2 + 3 + 1 = 9.
 * Example 2:
 *
 * Input: nums = [1,3,10,4,7,1], numSlots = 9
 * Output: 24
 * Explanation: One possible placement is [1, 1] into slot 1, [3] into slot 3, [4] into slot 4, [7] into slot 7, and [10] into slot 9.
 * This gives the maximum AND sum of (1 AND 1) + (1 AND 1) + (3 AND 3) + (4 AND 4) + (7 AND 7) + (10 AND 9) = 1 + 1 + 3 + 4 + 7 + 8 = 24.
 * Note that slots 2, 5, 6, and 8 are empty which is permitted.
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= numSlots <= 9
 * 1 <= n <= 2 * numSlots
 * 1 <= nums[i] <= 15
 * Accepted
 * 2,503
 */
public class MaximumANDSumOfArray {
    public static void main(String[] args) {
        MaximumANDSumOfArray o = new MaximumANDSumOfArray();
        System.out.println(o.maximumANDSum(new int[]{
                8, 13, 3, 15, 3, 15, 2, 15, 5, 7, 6
        }, 9));
    }

        int[] nums;
        int numSlots;
        Map<String, Integer> dp = new HashMap<>();

        public int maximumANDSum(int[] nums, int numSlots) {
            this.nums = nums;
            this.numSlots = numSlots;
            int room = getNumRepetitive(0, 2, numSlots);
            return find(0, room);
        }

        private int find(int pos, int room) {
            if (pos == nums.length) {
                return 0;
            }
            String state = pos + ":" + room;
            if (dp.containsKey(state)) return dp.get(state);
            int res = 0;
            //try all slots
            for (int slot = 0; slot < numSlots; slot++) {
                int base = getNumRepetitive(1, 0, slot);
                int left = (room / base) % 10;
                if (left > 0) {
                    res = Math.max(res, (nums[pos] & (slot + 1)) + find(pos + 1, room - base));
                }
            }
            dp.put(state, res);
            return res;
        }

        private int getNumRepetitive(int base, int num, int len) {
            int res = base;
            while (len-- > 0) {
                res *= 10;
                res += num;
            }
            return res;
        }

}

