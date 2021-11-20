package Preparation;

import java.util.*;

/**
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * <p>
 * Return the maximum valued number you can get.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: num = 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 * Example 2:
 * <p>
 * Input: num = 9973
 * Output: 9973
 * Explanation: No swap.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= num <= 108
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        List<Integer> lst = new ArrayList<>();
        while (num > 0) {
            lst.add(num % 10);
            num /= 10;
        }

        Collections.reverse(lst);

        for (int i = 0; i < lst.size(); i++) {
            int max = i;
            for (int j = i + 1; j < lst.size(); j++) {
                if (lst.get(max) <= lst.get(j)) {
                    max = j;
                }
            }
            if (!lst.get(max).equals(lst.get(i))) {
                //System.out.println(i + " " + j);
                //swap
                int temp = lst.get(max);
                lst.set(max, lst.get(i));
                lst.set(i, temp);
                break;
            }
        }


        int res = 0;
        for (int n : lst) {
            res *= 10;
            res += n;
        }
        return res;
    }
}
