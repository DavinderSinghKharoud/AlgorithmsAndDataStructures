package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        helper(candidates, target, new ArrayList<Integer>(), result, 0);
        return result;

    }

    private static void helper(int[] candidates, int target, List<Integer> temp,
                               List<List<Integer>> result, int index) {

        if (target <= 0) {

            if (target == 0) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            temp.add(candidates[i]);
            helper(candidates, target - candidates[i], temp, result, i);
            temp.remove(temp.size() - 1);
        }
    }



    public static void main(String[] args) {

        List<List<Integer>> lst = combinationSum(new int[]{
                2, 3, 6, 7
        }, 7);

        for (List<Integer> list : lst) {

            System.out.println(list);
        }
    }
}
