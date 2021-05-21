package Algorithms.LeetCode.Weekly241;

import java.util.*;

public class FindingPairWithCertainSum {
    public static void main(String[] args) {
        FindingPairWithCertainSum o = new FindingPairWithCertainSum(new int[]{1, 1, 2, 2, 2, 3}, new int[]{2, 5, 5, 4, 5, 4});
        System.out.println(o.count(7));

    }

    int[] nums1, nums2;
    Map<Integer, Integer> map = new HashMap<>();

    public FindingPairWithCertainSum(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        for (int num : nums2)
            map.put(num, map.getOrDefault(num, 0) + 1);
    }

    public void add(int index, int val) {
        int count = map.get(nums2[index]) - 1;
        if (count == 0)
            map.remove(nums2[index]);
        else {
            map.put(nums2[index], count);
        }

        nums2[index] += val;
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }

    public int count(int tot) {
        int res = 0;
        for (int num : nums1) {
            int diff = tot - num;
            res += map.getOrDefault(diff, 0);
        }
        return res;
    }
}
