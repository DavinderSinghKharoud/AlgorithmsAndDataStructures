package LeetCode.WeeklyContest244;

import java.util.*;

public class MakeArrayElementsEqual {
    public static void main(String[] args) {
        MakeArrayElementsEqual o = new MakeArrayElementsEqual();
        System.out.println(o.reductionOperations(new int[]{1, 2, 1, 2, 3, 4, 3}));
    }

    public int reductionOperations(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] arr = new int[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr[index++] = entry.getKey();
        }
        shuffle(arr);

        index = arr.length - 1;
        while (arr[0] != arr[index]) {
            int count = map.get(arr[index]);
            res += count;
            index--;
            map.put(arr[index], map.get(arr[index]) + count);
        }

        return res;
    }

    static void shuffle(int[] aa) {
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
