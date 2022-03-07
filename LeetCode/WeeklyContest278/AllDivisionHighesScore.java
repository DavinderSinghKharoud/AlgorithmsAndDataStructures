package LeetCode.WeeklyContest278;

import java.util.*;

public class AllDivisionHighesScore {
    public static void main(String[] args) {
        AllDivisionHighesScore o = new AllDivisionHighesScore();
        System.out.println(o.maxScoreIndices(new int[]{0, 0, 1, 0}));
    }

    public List<Integer> maxScoreIndices(int[] nums) {
        int len = nums.length;
        int[] zeros = new int[len];
        int[] ones = new int[len];
        if (nums[0] == 0) {
            zeros[0] = 1;
        } else {
            ones[0] = 1;
        }
        for (int i = 1; i < len; i++) {
            zeros[i] = ((nums[i] == 0) ? 1 : 0) + zeros[i - 1];
            ones[i] = ((nums[i] == 1) ? 1 : 0) + ones[i - 1];
        }

        int max = 0;
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i <= len; i++) {
            int score;
            if (i == 0) {
                score = ones[len - 1];
            } else if (i == len) {
                score = zeros[len - 1];
            } else {
                score = zeros[i - 1] + (ones[len - 1] - ones[i - 1]);
            }
            if (score >= max) {
                if (score > max) lst.clear();
                max = score;
                lst.add(i);
            }
        }
        return lst;
    }


}
