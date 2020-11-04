package algorthims.HackerRank;

import java.util.*;

public class JimAndSkyscrapers {

    //O(n ^ 3) time complexity and O(n) space complexity
    static int solve(int[] arr) {
        int res = 0;

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int index = 0; index < arr.length; index++) {
            int num = arr[index];
            List<Integer> lst = map.getOrDefault(num, new ArrayList<>());
            lst.add(index);

            map.put(num, lst);

        }

        for (int key : map.keySet()) {
            List<Integer> lst = map.get(key);

            for (int index1 = 0; index1 < lst.size(); index1++) {
                for (int index2 = index1 + 1; index2 < lst.size(); index2++) {

                    if (isValid(lst.get(index1), lst.get(index2), arr)) {
                        res += 2;
                    }
                }
            }
        }

        return res;
    }

    public static boolean isValid(int start, int end, int[] arr) {
        int limit = arr[start];
        for (int index = start + 1; index < end; index++) {
            if (arr[index] > limit) return false;
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(solve2(new int[]{3, 2, 1, 2, 3, 3}));
    }

    //O(n) time and space complexity
    static long solve2(int[] arr) {
        long res = 0;

        Stack<int[]> stack = new Stack<>();

        for (int height : arr) {

            while (!stack.isEmpty() && height > stack.peek()[0]) {
                stack.pop();
            }

            if (!stack.isEmpty() && stack.peek()[0] == height) {
                res += stack.peek()[1]++;
            } else {
                stack.add(new int[]{height, 1});
            }
        }
        return res * 2;
    }

}
