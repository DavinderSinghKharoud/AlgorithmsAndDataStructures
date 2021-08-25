package LeetCode;

import java.util.*;

public class ArrayDoubledPairs {

    public static void main(String[] args) {

        ArrayDoubledPairs o = new ArrayDoubledPairs();
        System.out.println(o.canReorderDoubled(new int[]{-33, 0}));
    }

    Map<Integer, Integer> map = new HashMap<>();

    public boolean canReorderDoubled(int[] arr) {
        int zeroesCount = 0;
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (num == 0) zeroesCount++;
        }
        if (zeroesCount % 2 != 0) return false;
        shuffleSort(arr);

        for (int num : arr) {
            if (map.containsKey(num)) {
                int doub = 2 * num;
                if (map.containsKey(doub)) {
                    remove(num);
                    remove(doub);
                } else {
                    // Check if it has half
                    if (num % 2 == 0) {
                        int half = num / 2;
                        if (map.containsKey(half)) {
                            remove(num);
                            remove(half);
                        }
                    }
                }
            }
        }

        return map.size() == 0;
    }

    static void shuffleSort(int[] aa) {
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

    void remove(int num) {
        int count = map.get(num) - 1;
        if (count != 0) {
            map.put(num, count);
        } else
            map.remove(num);
    }
}
