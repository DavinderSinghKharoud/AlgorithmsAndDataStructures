package LeetCode.WeeklyContest279;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SmallestValueRearrangedNumber {
    public static void main(String[] args) {
        SmallestValueRearrangedNumber o = new SmallestValueRearrangedNumber();
        System.out.println(o.smallestNumber(-100));
    }

    public long smallestNumber(long num) {
        boolean isNegative = (num < 0);
        if (isNegative) {
            return -1 * findLargest(num * -1);
        } else {
            return findSmallest(num);
        }
    }

    private long findSmallest(long num) {
        List<Integer> lst = new ArrayList<>();
        while (num > 0) {
            lst.add((int) (num % 10));
            num /= 10;
        }
        Collections.sort(lst);
        long ans = 0;
        int countZeros = 0;
        for (int n : lst) {
            if (n == 0) {
                countZeros++;
            } else break;
        }

        for (int n : lst) {
            if (n != 0 && ans == 0) {
                ans += n;
                while (countZeros-- > 0) {
                    ans *= 10;
                }
            } else {
                ans *= 10;
                ans += n;
            }
        }
        return ans;
    }

    private long findLargest(long num) {
        List<Integer> lst = new ArrayList<>();
        while (num > 0) {
            lst.add((int) (num % 10));
            num /= 10;
        }
        lst.sort(Collections.reverseOrder());
        long ans = 0;
        for (int n : lst) {
            ans *= 10;
            ans += n;
        }
        return ans;
    }
}
