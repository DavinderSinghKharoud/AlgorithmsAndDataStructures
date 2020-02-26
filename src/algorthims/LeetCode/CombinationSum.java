package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    static List<List<Integer>> result = new ArrayList<>();
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        helper(candidates, target, new ArrayList<Integer>(), 0);
        return result;

    }

    private static void helper(int[] candidates, int target, List<Integer> temp, int index) {

        if (target <= 0) {

            if (target == 0) {
                result.add(new ArrayList<>(temp));
            }
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            temp.add(candidates[i]);
            helper(candidates, target - candidates[i], temp, i);
            temp.remove(temp.size() - 1);
        }
    }

    static List<List<Integer>> res = new ArrayList<>();
    //More Efficient One
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {


        Arrays.sort(candidates);
        combine(candidates, 0, new ArrayList<Integer>(), 0, target);
        return res;
    }

    private static void combine(int[] candidates, int i, ArrayList<Integer> list, int sum, int target) {
         if( sum == target ){
             res.add( new ArrayList<>(list));
         }

         //Adding candidates
         if ( sum + candidates[i] <= target ){
             list.add( candidates[i] );
             combine( candidates , i, list, sum + candidates[i], target );
             list.remove( list.size() - 1);
         }

         //Removing candidates
        if( i + 1 < candidates.length && sum + candidates[i + 1 ] <= target ){
            combine( candidates, i+1, list, sum, target );
        }


    }


    public static void main(String[] args) {

        List<List<Integer>> lst = combinationSum(new int[]{
                3,2
        }, 8);

        for (List<Integer> list : lst) {

            System.out.println(list);
        }
    }
}
