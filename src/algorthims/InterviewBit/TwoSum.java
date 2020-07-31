package algorthims.InterviewBit;

import java.util.*;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.
 *
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 < index2. Please note that your returned answers (both index1 and index2 ) are not zero-based.
 * Put both these numbers in order in an array and return the array from your function ( Looking at the function signature will make things clearer ). Note that, if no pair exists, return empty list.
 *
 * If multiple solutions exist, output the one where index2 is minimum. If there are multiple solutions with the minimum index2, choose the one with minimum index1 out of them.
 *
 * Input: [2, 7, 11, 15], target=9
 * Output: index1 = 1, index2 = 2
 */
public class TwoSum {

    public static ArrayList<Integer> twoSum(final List<Integer> lst, int target) {
        int len = lst.size();

        ArrayList<Integer> res = new ArrayList<>();
        if (len == 0) return res;

        Map<Integer, Integer> map = new HashMap<>();

        for (int index = 0; index < len; index++) {
            int num = lst.get(index);
            if (map.containsKey(target - num)) {
                add(res, map.get(target - num) + 1, index + 1);
            }

            int value = map.containsKey(num) ? Math.min(map.get(num), index) : index;
            map.put(num, value);
        }

        return res;
    }

    public static void add(ArrayList<Integer> res, int index1, int index2) {
        index1 = Math.min(index1, index2);
        index2 = Math.max( index1, index2 );
        if (res.isEmpty()) {
            res.add(index1);
            res.add(index2);
        } else {

            if (res.get(1) == index1) {
                if (res.get(0) >= index1) {
                    res.clear();
                    res.add(index1);
                    res.add(index2);
                }
            } else {

                if (res.get(1) > index2) {
                    res.clear();
                    res.add(index1);
                    res.add(index2);
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(twoSum(Arrays.asList(4, 7, -4, 2, 2, 2, 3, -5, -3), -3));
    }
}
