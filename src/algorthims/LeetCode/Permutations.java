package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    //Simple One
    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return res;
        }

        boolean[] used = new boolean[nums.length];
        List<Integer> permutation = new ArrayList<>();
        helper(nums, permutation, used, res);
        return res;


    }

    private static void helper(int[] nums, List<Integer> permutation, boolean[] used, List<List<Integer>> res) {

        if (permutation.size() == nums.length) {
            res.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            permutation.add(nums[i]);
            helper(nums, permutation, used, res);
            permutation.remove(permutation.size() - 1);
            used[i] = false;
        }
    }


    static List<List<Integer>> res;
    //More Efficient
    public static List<List<Integer>> permute2(int[] nums) {
        res = new ArrayList<>();
        get(nums, 0);
        return res;
    }

    private static void get(int[] nums, int start) {
        if (start == nums.length - 1) {
            res.add(toList(nums));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            int tmp = nums[start];
            nums[start] = nums[i];
            nums[i] = tmp;
            get(nums, start + 1);
            nums[i] = nums[start];
            nums[start] = tmp;
        }
    }

    private static List<Integer> toList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) list.add(i);
        return list;
    }

    public static void main(String[] args) {

        List<List<Integer>> result = permute2(new int[]{
                1,2,3
        });

        for (int i = 0; i < result.size(); i++) {

            System.out.println(result.get(i));
        }
    }
}
