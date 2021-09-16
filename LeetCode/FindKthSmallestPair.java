package LeetCode;

import java.util.*;

public class FindKthSmallestPair {

    public static void main(String[] args) {
        FindKthSmallestPair o = new FindKthSmallestPair();
        System.out.println(o.smallestDistancePair(new int[]{1, 3, 1}, 1));
    }

    int[] nums;

    public int smallestDistancePair(int[] nums, int k) {
        shuffleSort(nums);
        this.nums = nums;
        int max = Arrays.stream(nums).max().getAsInt();
        int start = 0, end = 5;//1000_000;
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if (findPairs(mid) >= k) {
                end = mid;
            } else
                start = mid + 1;
        }
        return end;
    }

    int findPairs(int lim) {
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            while (nums[right] - nums[left] > lim) left++;
            count += (right - left);
        }
        return count;
    }

    static void shuffleSort(int[] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
        Arrays.sort(aa);
    }
}
