package LeetCode;

import java.util.*;

public class ArrayElementNotEqualNeighbors {

    public static void main(String[] args) {
        ArrayElementNotEqualNeighbors o = new ArrayElementNotEqualNeighbors();
        System.out.println(Arrays.toString(o.rearrangeArray(new int[]{2, 6, 8, 9, 10})));
    }

    public int[] rearrangeArray(int[] nums) {
        int len = nums.length;
        shuffleSort(nums);
        for (int i = 1; i < len; i += 2) {
            nums[i] = nums[i] ^ nums[i - 1] ^ (nums[i - 1] = nums[i]);
        }
        return nums;
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
