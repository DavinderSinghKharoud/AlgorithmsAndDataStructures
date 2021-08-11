package LeetCode.BiweeklyContest50;

import java.util.Arrays;

public class MaximumXORForEachQuery {
    public static void main(String[] args) {

        MaximumXORForEachQuery obj = new MaximumXORForEachQuery();
        System.out.println(Arrays.toString(obj.getMaximumXor(new int[]{0, 1, 1, 3},
                2)));
        //System.out.println(Arrays.toString(obj.getMaximumXor(new int[]{2, 3, 4, 7}, 3))); ;}
    }

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        int len = nums.length;
        int[] sol = new int[len];
        maximumBit = (int) (Math.pow(2, maximumBit)) - 1;

        int xor = 0;
        for (int i = 0; i < len; i++) {
            xor ^= nums[i];
            int ans = maximumBit;
            int curr = xor;
            int index = 0;
            while (curr > 0) {
                int last = (curr & 1);
                if (last == 1) {
                    int mask = ~ans;
                    mask |= (1 << index);
                    ans = ~mask;
                }
                curr >>= 1;
                index++;
            }
            sol[len - i - 1] = ans;
        }
        return sol;

    }
}
