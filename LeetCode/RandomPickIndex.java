package LeetCode;

import java.util.*;

public class RandomPickIndex {

    public static void main(String[] args) {
        RandomPickIndex randomPickIndex = new RandomPickIndex(new int[]{1, 2, 3, 3, 3});
        System.out.println(randomPickIndex.pick(3));
    }

    Random random = new Random();
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] nums;

    public RandomPickIndex(int[] nums) {
        this.nums = nums;
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexes = map.getOrDefault(nums[i], new ArrayList<>());
            indexes.add(i);
            map.put(nums[i], indexes);
        }
    }

    public int pick(int target) {
        List<Integer> indexes = map.get(target);
        return indexes.get(getRandom(indexes.size()));
    }

    public int getRandom(int lim) {
        return random.nextInt(lim);
    }
}
