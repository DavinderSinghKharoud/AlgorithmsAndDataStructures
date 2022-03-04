package LeetCode.WeeklyContest278;

import java.util.*;

public class KeepMultiplyingFoundValues {
    public static void main(String[] args) {
        KeepMultiplyingFoundValues o = new KeepMultiplyingFoundValues();

    }

    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        while (isContain(original, set)) {
            original *= 2;
        }
        return original;
    }

    private boolean isContain(int original, Set<Integer> set) {
        return set.contains(original);
    }
}
