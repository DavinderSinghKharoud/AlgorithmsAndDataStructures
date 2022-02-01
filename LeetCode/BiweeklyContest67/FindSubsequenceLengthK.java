package LeetCode.BiweeklyContest67;

import java.util.*;

public class FindSubsequenceLengthK {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FindSubsequenceLengthK().maxSubsequence(
                new int[]{2, 1, 3, 3}, 2
        )));
    }

    public int[] maxSubsequence(int[] nums, int k) {
        int len = nums.length;
        int[][] val = new int[len][2];
        int ii = 0;
        for (int i = 0; i < len; i++) {
            val[ii++] = new int[]{nums[i], i};
        }
        Arrays.sort(val, Comparator.comparingInt(o -> o[0]));
        int[] ans = new int[k];
        Set<Integer> set = new HashSet<>();
        for (int i = nums.length - 1; i >= 0 && set.size() < k; i--) {
            set.add(val[i][1]);
        }

        ii = 0;
        for (int i = 0; i < len && ii < k; i++) {
            if (set.contains(i)) {
                ans[ii++] = nums[i];
            }
        }
        return ans;
    }
}
