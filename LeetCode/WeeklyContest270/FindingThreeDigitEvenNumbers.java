package LeetCode.WeeklyContest270;

import java.util.*;

public class FindingThreeDigitEvenNumbers {
    public static void main(String[] args) {
        FindingThreeDigitEvenNumbers o = new FindingThreeDigitEvenNumbers();
        System.out.println(Arrays.toString(o.findEvenNumbers(new int[]{
                1, 7, 3, 0, 7, 3, 0, 9, 5, 0, 4, 5, 6, 6, 5, 7, 4, 8, 9, 5, 2, 3, 3, 5, 0, 6, 3, 2, 0, 6, 6, 2, 9, 0, 7, 3, 8, 1, 3, 1, 8, 8, 1, 5, 9, 5, 5, 4, 1, 7, 8, 4, 3, 4, 3, 4, 7, 4, 0, 3, 6, 6, 8, 6, 6, 3, 7, 2, 7, 1, 0, 9, 7, 2, 2, 6, 9, 5, 2, 6, 9, 7, 6, 6, 8, 1, 5, 7, 4, 0, 8, 4, 4, 6, 2, 0, 6, 3, 6, 5
        })));
    }

    public int[] findEvenNumbers(int[] digits) {
        Set<Integer> set = new HashSet<>();
        int len = digits.length;
        for (int i = 0; i < len; i++) {
            if (digits[i] == 0) continue;
            for (int j = 0; j < len; j++) {
                if (j == i) continue;
                for (int k = 0; k < len; k++) {
                    if (k == j || k == i) continue;
                    int val = (digits[i] * 10 + digits[j]) * 10 + digits[k];
                    if (val % 2 == 0) set.add(val);
                }
            }
        }
        int[] ans = new int[set.size()];
        int i = 0;
        for (int num : set) {
            ans[i++] = num;
        }
        Arrays.sort(ans);
        return ans;
    }
}
