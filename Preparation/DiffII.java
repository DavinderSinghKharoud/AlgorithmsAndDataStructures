package Preparation;

import java.util.*;

/**
 * Given an array A of integers and another non negative integer k, find if there exists 2 indices i and j such that A[i] - A[j] = k, i != j.
 * <p>
 * Example :
 * <p>
 * Input :
 * <p>
 * A : [1 5 3]
 * k : 2
 * Output :
 * <p>
 * 1
 * as 3 - 1 = 2
 * <p>
 * Return 0 / 1 for this problem.
 */
public class DiffII {

    public int diffPossible(final List<Integer> lst, int target) {
        Set<Integer> set = new HashSet<>();

        for (int num : lst) {
            if (set.contains(target + num) || set.contains(num - target)) {
                return 1;
            }
            set.add(num);
        }
        return 0;
    }
}
