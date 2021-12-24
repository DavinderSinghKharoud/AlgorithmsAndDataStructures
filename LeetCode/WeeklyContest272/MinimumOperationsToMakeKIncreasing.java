package LeetCode.WeeklyContest272;

import java.util.*;
import java.util.stream.Collectors;

public class MinimumOperationsToMakeKIncreasing {
    public static void main(String[] args) {
        MinimumOperationsToMakeKIncreasing o = new MinimumOperationsToMakeKIncreasing();
        System.out.println(o.kIncreasing(new int[]{
                4, 1, 5, 2, 6, 2
        }, 3));
    }

    public int kIncreasing(int[] arr, int k) {
        int len = arr.length;
        if (k == 1) return operationsNeeded(Arrays.stream(arr)
                .boxed()
                .collect(Collectors.toList()));
        int min = 0;
        for (int i = 0; i < k; i++) {
            List<Integer> lst = new ArrayList<>();
            for (int j = i; j < len; j += k) {
                lst.add(arr[j]);
            }
            min += operationsNeeded(lst);
        }
        return min;
    }

    public int operationsNeeded(List<Integer> nums) {
        int len = nums.size();
        if (len <= 1) return 0;
        ArrayList<Integer> lst = new ArrayList<>();
        for (int num : nums) {
            if (lst.isEmpty() || lst.get(lst.size() - 1) <= num) lst.add(num);
            else {
                int index = upper(lst, num);
                lst.set(index, num);
            }
        }
        return nums.size() - lst.size();
    }

    private int upper(ArrayList<Integer> lst, int num) {
        int start = 0, end = lst.size() - 1;
        while (start < end) {
            int mid = (end + start + 1) / 2;
            if (lst.get(mid) <= num) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        if (lst.get(start) > num) return start;
        return start + 1;
    }

}
