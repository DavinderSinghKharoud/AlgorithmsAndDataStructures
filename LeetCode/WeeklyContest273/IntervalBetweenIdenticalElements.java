package LeetCode.WeeklyContest273;

import java.util.*;

/**
 * You are given a 0-indexed array of n integers arr.
 *
 * The interval between two elements in arr is defined as the absolute difference between their indices. More formally, the interval between arr[i] and arr[j] is |i - j|.
 *
 * Return an array intervals of length n where intervals[i] is the sum of intervals between arr[i] and each element in arr with the same value as arr[i].
 *
 * Note: |x| is the absolute value of x.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,1,3,1,2,3,3]
 * Output: [4,2,7,2,4,4,5]
 * Explanation:
 * - Index 0: Another 2 is found at index 4. |0 - 4| = 4
 * - Index 1: Another 1 is found at index 3. |1 - 3| = 2
 * - Index 2: Two more 3s are found at indices 5 and 6. |2 - 5| + |2 - 6| = 7
 * - Index 3: Another 1 is found at index 1. |3 - 1| = 2
 * - Index 4: Another 2 is found at index 0. |4 - 0| = 4
 * - Index 5: Two more 3s are found at indices 2 and 6. |5 - 2| + |5 - 6| = 4
 * - Index 6: Two more 3s are found at indices 2 and 5. |6 - 2| + |6 - 5| = 5
 * Example 2:
 *
 * Input: arr = [10,5,10,10]
 * Output: [5,0,3,4]
 * Explanation:
 * - Index 0: Two more 10s are found at indices 2 and 3. |0 - 2| + |0 - 3| = 5
 * - Index 1: There is only one 5 in the array, so its sum of intervals to identical elements is 0.
 * - Index 2: Two more 10s are found at indices 0 and 3. |2 - 0| + |2 - 3| = 3
 * - Index 3: Two more 10s are found at indices 0 and 2. |3 - 0| + |3 - 2| = 4
 *
 *
 * Constraints:
 *
 * n == arr.length
 * 1 <= n <= 105
 * 1 <= arr[i] <= 105
 */
public class IntervalBetweenIdenticalElements {
    public static void main(String[] args) {
        IntervalBetweenIdenticalElements o = new IntervalBetweenIdenticalElements();
        System.out.println(Arrays.toString(o.getDistances(new int[]{
                2, 1, 3, 1, 2, 3, 3
        })));
    }

    public long[] getDistances(int[] arr) {
        int len = arr.length;
        long[] ans = new long[len];
        HashMap<Integer, ArrayList<long[]>> map = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int num = arr[i];
            map.computeIfAbsent(num, k -> new ArrayList<>());
            map.get(num).add(new long[]{i, 0});
        }

        // evaluatePrefix
        for (Map.Entry<Integer, ArrayList<long[]>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                prefix(entry.getValue());
            }
        }

        for (Map.Entry<Integer, ArrayList<long[]>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                ArrayList<long[]> curr = entry.getValue();
                for (int i = 0; i < curr.size(); i++) {
                    long sum1 = ((long) i * curr.get(i)[0]) - (getSum(curr, 0, i - 1));
                    long sum2 = getSum(curr, i + 1, curr.size() - 1) - ((long) (curr.size() - i - 1) * curr.get(i)[0]);
                    ans[(int) curr.get(i)[0]] = sum1 + sum2;
                }
            }
        }
        return ans;
    }

    long getSum(ArrayList<long[]> lst, int a, int b) {
        if (b < a)
            return 0;
        long sum = lst.get(b)[1];
        if (a - 1 >= 0)
            sum -= lst.get(a - 1)[1];
        return sum;
    }

    void prefix(ArrayList<long[]> lst) {
        lst.get(0)[1] = lst.get(0)[0];
        for (int i = 1; i < lst.size(); i++) {
            lst.get(i)[1] = lst.get(i)[0] + lst.get(i - 1)[1];
        }
    }
}
