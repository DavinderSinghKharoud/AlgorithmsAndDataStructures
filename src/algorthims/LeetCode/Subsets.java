package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> lst = new ArrayList<>();
        backTrack(res, lst, nums, 0);
        res.add(lst);
        return res;
    }

    private static void backTrack(List<List<Integer>> res, List<Integer> lst, int[] nums, int index) {

        for (int i = index; i < nums.length; i++) {
            lst.add(nums[i]);
            backTrack(res, lst, nums, i + 1);
            res.add(new ArrayList<>(lst));
            lst.remove(lst.size() - 1);
        }


    }


    /**
     * Let's start from empty subset in output list. At each step one takes new integer into
     * consideration and generates new subsets from the existing ones.
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr) {{
                    add(num);
                }});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }


    public static void main(String[] args) {
        List<List<Integer>> lst = subsets(new int[]{
                1, 2, 3
        });

        for (List num : lst) {

            System.out.println(num);
        }

    }
}
