package Preparation;

import java.util.*;

/**
 * Design a data structure to find the frequency of a given value in a given subarray.
 * <p>
 * The frequency of a value in a subarray is the number of occurrences of that value in the subarray.
 * <p>
 * Implement the RangeFreqQuery class:
 * <p>
 * RangeFreqQuery(int[] arr) Constructs an instance of the class with the given 0-indexed integer array arr.
 * int query(int left, int right, int value) Returns the frequency of value in the subarray arr[left...right].
 * FindingThreeDigitEvenNumbers subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).
 */
public class RangeFrequency {
    public static void main(String[] args) {
        RangeFrequency o = new RangeFrequency(new int[]{12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56});
        System.out.println(o.query(1, 2, 4));
        System.out.println(o.query(0, 11, 33));
        System.out.println(o.query(5, 8, 22));
    }

    Map<Integer, List<Integer>> map = new HashMap<>();

    public RangeFrequency(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int curr = arr[i];
            if (!map.containsKey(curr)) {
                map.put(curr, new ArrayList<>());
            }
            map.get(curr).add(i);
        }

        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            entry.getValue().sort(Comparator.comparingInt(o -> o));
        }

    }

    public int query(int left, int right, int value) {
        if (!map.containsKey(value))
            return 0;
        List<Integer> lst = map.get(value);
        int upper = findUpper(lst, left);
        int lower = findLower(lst, right);
        if (upper == -1)
            return 0;
        return (lower - upper + 1);
    }

    int findLower(List<Integer> lst, int target) {
        int start = 0, end = lst.size() - 1;
        while (start < end) {
            int mid = (end + start + 1) / 2;
            if (lst.get(mid) <= target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return (lst.get(start) <= target) ? start : -1;
    }

    int findUpper(List<Integer> lst, int target) {
        int start = 0, end = lst.size() - 1;
        while (start < end) {
            int mid = (end + start) / 2;
            if (lst.get(mid) >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return (lst.get(end) >= target) ? end : -1;
    }
}
