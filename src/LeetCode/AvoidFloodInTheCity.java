package LeetCode;

import java.util.*;

/**
 * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake which is full of water, there will be a flood. Your goal is to avoid the flood in any lake.
 *
 * Given an integer array rains where:
 *
 * rains[i] > 0 means there will be rains over the rains[i] lake.
 * rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
 * Return an array ans where:
 *
 * ans.length == rains.length
 * ans[i] == -1 if rains[i] > 0.
 * ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
 * If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.
 *
 * Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes. (see example 4)
 *
 *
 *
 * Example 1:
 *
 * Input: rains = [1,2,3,4]
 * Output: [-1,-1,-1,-1]
 * Explanation: After the first day full lakes are [1]
 * After the second day full lakes are [1,2]
 * After the third day full lakes are [1,2,3]
 * After the fourth day full lakes are [1,2,3,4]
 * There's no day to dry any lake and there is no flood in any lake.
 */
public class AvoidFloodInTheCity {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(avoidFlood(new int[]{10,20,20})));
    }

    //Time complexity O(n Log(n) )
    public static int[] avoidFlood(int[] rains) {

        int len = rains.length;

        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();

        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int curr = rains[i];
            if (curr != 0) {
                if (map.containsKey(curr)) {
                    if (set.isEmpty()) //If there is no space to fulfill
                        return new int[]{};
                    Integer index = set.ceiling(map.get(curr));
                    if (index == null) return new int[]{};
                    ans[index] = curr;
                    set.remove(index);
                }
                map.put(curr, i);
                ans[i] = -1;
            } else {
                set.add(i);
            }
        }

        while (!set.isEmpty()) {
            ans[set.pollLast()] = 1;
        }
        return ans;
    }
}
