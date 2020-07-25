/*
Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example :
[1,1,2] have the following unique permutations:

[1,1,2]
[1,2,1]
[2,1,1]
 */

import java.util.*;

public class AllUniquePermutations {

    //O(n square) time complexity and O(n) space complexity
    public static ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> lst) {

        int len = lst.size();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Collections.sort(lst);
        if (lst == null || len == 0) {
            return res;
        }


        Set<Integer> set = new HashSet<>();
        ArrayList<Integer> curr = new ArrayList<>();
        helper(lst, set, res, curr);

        return res;

    }

    public static void helper(ArrayList<Integer> lst, Set<Integer> set, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> curr) {

        if (curr.size() == lst.size()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int index = 0; index < lst.size(); ++index) {
            if (set.contains(index)) continue;
            set.add(index);
            curr.add(lst.get(index));
            helper(lst, set, res, curr);
            curr.remove(curr.size() - 1);
            set.remove(index);

            //for duplicate numbers
            while (index + 1 < lst.size() && lst.get(index).equals(lst.get(index + 1))) {
                index++;
            }
        }


    }

    public static void main(String[] args) {

        ArrayList<Integer> lst = new ArrayList<>();
        //lst.add(1);lst.add(1);lst.add(2);
        lst.add(3);
        lst.add(3);
        lst.add(0);
        lst.add(3);
        ArrayList<ArrayList<Integer>> res = permute2(lst);

        for (ArrayList<Integer> curr : res) {
            System.out.println(curr);
        }
    }

    public static ArrayList<ArrayList<Integer>> permute2(ArrayList<Integer> lst) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (lst.size() == 0) return res;
        helper2(lst, res, 0);
        return res;
    }

    private static void helper2(ArrayList<Integer> lst, ArrayList<ArrayList<Integer>> res, int start) {
        if (start == lst.size() - 1) {
            res.add(new ArrayList<>(lst));
            return;
        }

        Set<Integer> set = new HashSet<>();

        for (int index = start; index < lst.size(); index++) {
            if (set.contains(lst.get(index))) continue;
            set.add(lst.get(index));
            swapNumbers(lst, index, start);
            helper2(lst, res, start + 1);
            swapNumbers(lst, start, index);
        }
    }

    private static void swapNumbers(ArrayList<Integer> lst, int index, int start) {

        int temp = lst.get(index);
        lst.set(index, lst.get(start));
        lst.set(start, temp);
    }
}

