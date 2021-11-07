package Preparation;

import java.util.*;


/*
Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 < index2. Please note that your returned answers (both index1 and index2 ) are not zero-based. Put both these numbers in order in an array and return the array from your function ( Looking at the function signature will make things clearer ). Note that, if no pair exists, return empty list.

If multiple solutions exist, output the one where index2 is minimum. If there are multiple solutions with the minimum index2, choose the one with minimum index1 out of them.

Input: [2, 7, 11, 15], target=9
Output: index1 = 1, index2 = 2
 */
public class TwoSumWithConstraints {
    public ArrayList<Integer> twoSum(final List<Integer> lst, int target) {
        ArrayList<Integer> res = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();


        for (int i = 0; i < lst.size(); i++) {
            int diff = target - lst.get(i);
            if (map.containsKey(diff)) {
                updateRes(res, i + 1, map.get(diff) + 1);
            }
            if (!map.containsKey(lst.get(i))) map.put(lst.get(i), i);
        }
        return res;
    }

    void updateRes(ArrayList<Integer> res, int a, int b) {
        if (a > b) a = a ^ b ^ (b = a);
        if (res.isEmpty()) {
            res.add(a);
            res.add(b);
        } else {
            if (res.get(1) > b) {
                res.clear();
                res.add(a);
                res.add(b);
            } else if (res.get(1) == b) {
                if (res.get(0) > a) {
                    res.clear();
                    res.add(a);
                    res.add(b);
                }
            }
        }
    }

}
