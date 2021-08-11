package LeetCode;

import java.util.*;

public class KConcatenationMax {

    public static void main(String[] args) {
        KConcatenationMax o = new KConcatenationMax();
        System.out.println(o.kConcatenationMaxSum(new int[]{1, -2, 1}, 5));
    }

    int mod = (int) 1e9 + 7;

    public int kConcatenationMaxSum(int[] arr, int k) {
        int len = arr.length;
        if (k <= 1)
            return getAns(kadane(arr));
        long sum = Arrays.stream(arr).asLongStream().sum();
        int[] joint = new int[2 * len];
        for (int i = 0; i < len; i++) {
            joint[i] = arr[i];
            joint[i + len] = arr[i];
        }
        if (sum < 0) {
            return getAns(kadane(joint));
        } else {
            return getAns(kadane(joint) % mod + (k - 2) * sum % mod);
        }
    }

    int getAns(long a) {
        if (a < 0) return 0;
        return (int) (a % mod);
    }

    long kadane(int[] arr) {
        long max = Long.MIN_VALUE;
        long sum = 0;
        for (int num : arr) {
            if (sum + num >= num) {
                sum += num;
            } else sum = num;
            max = Math.max(max, sum);
        }
        return max;
    }

}
