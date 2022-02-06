package LeetCode.WeeklyContest279;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortEvenAndOdd {
    public static void main(String[] args) {
        SortEvenAndOdd o = new SortEvenAndOdd();
    }

    public int[] sortEvenOdd(int[] nums) {
        int len = nums.length;
        List<Integer> even = new ArrayList<>(), odd = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) even.add(nums[i]);
            else odd.add(nums[i]);
        }
        Collections.sort(even);
        Collections.sort(odd, Collections.reverseOrder());

        int evenIndex = 0, oddIndex = 0;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            if (i % 2 == 0) {
                ans[i] = even.get(evenIndex++);
            } else {
                ans[i] = odd.get(oddIndex++);
            }
        }
        return ans;
    }
}
