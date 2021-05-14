package Algorithms.LeetCode;

import java.util.*;

public class ReversePairs {
    public static void main(String[] args) {
        ReversePairs obj = new ReversePairs();


        System.out.println(obj.reversePairs2(new int[]{1, 3, 2, 3, 1}));
        System.out.println(obj.reversePairs2(new int[]{2, 4, 3, 5, 1}));

        System.out.println(obj.reversePairs2(new int[]{-5, -5}));
    }

    /************************************************************************************************************************************************/

    public int reversePairs2(int[] nums) {
        int res = 0;
        int len = nums.length;
        int[] copy = Arrays.stream(nums).distinct().toArray();
        shuffle(copy);
        Arrays.sort(copy);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < copy.length; i++) {
            map.put(copy[i], i);
        }
        Fenwick tree = new Fenwick(copy.length);

        for (int i = 0; i < len; i++) {
            long curr = (long) 2 * nums[i];
            int index = lowerBound(copy, curr);
            if (copy[index] <= curr) index++;
                if (index < copy.length) {
                    res += tree.getSumRange(map.get(copy[index]), copy.length - 1);
                }
            tree.update(map.get(nums[i]), 1);
        }
        return res;
    }

    int lowerBound(int[] arr, long val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] >= val) {
                r = mid;
            } else
                l = mid + 1;
        }
        return l;
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
    }

    public class Fenwick {
        long[] prefix;
        int len;

        public Fenwick(int len) {
            this.len = len;
            prefix = new long[len + 1];

        }

        public void update(int index, int val) {
            index++;
            while (index <= len) {
                prefix[index] += val;
                index += (index & -index);
            }
        }

        public long getSumRange(int index1, int index2) {
            return getSum(index2) - getSum(index1 - 1);
        }

        private long getSum(int index) {
            index++;
            long sum = 0;
            while (index > 0) {
                sum += prefix[index];
                index -= (index & -index);
            }
            return sum;
        }

    }
    /************************************************************************************************************************************************/

    //Time Limit Exceeded
    public int reversePairs(int[] nums) {
        int res = 0;
        int len = nums.length;
        long[] modified = new long[len];

        for (int i = 0; i < len; i++) {
            modified[i] = nums[i] * 2L;
        }

        TreeSet<Pair> set = new TreeSet<>((o1, o2) -> {
            if (o1.x == o2.x)
                return Long.compare(o1.y, o2.y);
            return Long.compare(o1.x, o2.x);
        });

        for (int i = len - 1; i >= 0; i--) {
            SortedSet<Pair> headSet = set.headSet(new Pair(nums[i], 0), false);
            res += headSet.size();
            set.add(new Pair(modified[i], i));
        }

        return res;
    }

    static class Pair {
        long x, y;

        public Pair(long xx, long yy) {
            x = xx;
            y = yy;
        }
    }
}
