package LeetCode.WeeklyContest271;

/**
 * Fruits are available at some positions on an infinite x-axis. You are given a
 * 2D integer array fruits where fruits[i] = [positioni, amounti] depicts
 * amounti fruits at the position positioni. fruits is already sorted by
 * positioni in ascending order, and each positioni is unique.
 * <p>
 * You are also given an integer startPos and an integer k. Initially, you are
 * at the position startPos. From any position, you can either walk to the left
 * or right. It takes one step to move one unit on the x-axis, and you can walk
 * at most k steps in total. For every position you reach, you harvest all the
 * fruits at that position, and the fruits will disappear from that position.
 * <p>
 * Return the maximum total number of fruits you can harvest.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: fruits = [[2,8],[6,3],[8,6]], startPos = 5, k = 4 Output: 9
 * Explanation: The optimal way is to: - Move right to position 6 and harvest 3
 * fruits - Move right to position 8 and harvest 6 fruits You moved 3 steps and
 * harvested 3 + 6 = 9 fruits in total. Example 2:
 * <p>
 * <p>
 * Input: fruits = [[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]], startPos = 5, k = 4
 * Output: 14 Explanation: You can move at most k = 4 steps, so you cannot reach
 * position 0 nor 10. The optimal way is to: - Harvest the 7 fruits at the
 * starting position 5 - Move left to position 4 and harvest 1 fruit - Move
 * right to position 6 and harvest 2 fruits - Move right to position 7 and
 * harvest 4 fruits You moved 1 + 3 = 4 steps and harvested 7 + 1 + 2 + 4 = 14
 * fruits in total. Example 3:
 * <p>
 * <p>
 * Input: fruits = [[0,3],[6,4],[8,5]], startPos = 3, k = 2 Output: 0
 * Explanation: You can move at most k = 2 steps and cannot reach any position
 * with fruits.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= fruits.length <= 105 fruits[i].length == 2 0 <= startPos, positioni <= 2
 * * 105 positioni-1 < positioni for any i > 0 (0-indexed) 1 <= amounti <= 104 0
 * <= k <= 2 * 105
 */
public class MaxFruitsHarvestedMostKSteps {
    public static void main(String[] args) {
        MaxFruitsHarvestedMostKSteps o = new MaxFruitsHarvestedMostKSteps();
        System.out.println(
                o.maxTotalFruits2(new int[][]{{0, 9}, {4, 1}, {5, 7}, {6, 2}, {7, 4}, {10, 9}}, 5, 4));
    }

    /**
     * O(n)
     */
    public int maxTotalFruits2(int[][] fruits, int startPos, int k) {
        int len = Math.max(findMaxIndex(fruits) + 1, startPos + 1);
        int[] arr = new int[len];
        int[] prefix = new int[len];
        int ans = 0;
        for (int[] fruit : fruits) {
            int index = fruit[0], amount = fruit[1];
            arr[index] = amount;
        }

        for (int i = 0; i < len; i++) {
            prefix[i] = ((i - 1 >= 0) ? prefix[i - 1] : 0) + arr[i];
        }
        int left = 0, right = 0;
        if (arr[startPos] > 0) {
            left = right = ans = arr[startPos];
        }

        for (int i = startPos - 1; i >= Math.max(0, startPos - k); i--) {
            if (arr[i] > 0) {
                left += arr[i];
                // Check how much you can make to the right
                int rem = k - (startPos - i);
                int lim = Math.min(len - 1, i + rem);
                ans = Math.max(ans, Math.max(left, getSum(prefix, i, lim)));
            }
        }
        for (int i = startPos + 1; i < Math.min(len, startPos + k + 1); i++) {
            if (arr[i] > 0) {
                right += arr[i];
                // Check how much you can make to the left
                int rem = k - (i - startPos);
                int lim = Math.max(0, i - rem);
                ans = Math.max(ans, Math.max(right, getSum(prefix, lim, i)));
            }
        }
        return ans;
    }

    int getSum(int[] prefix, int a, int b) {
        int sum = prefix[b];
        if (a - 1 >= 0)
            sum -= prefix[a - 1];
        return sum;
    }

    /**
     * O(n logn) using segment trees
     */
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int len = Math.max(findMaxIndex(fruits) + 1, startPos + 1);
        int[] arr = new int[len];
        int ans = 0;
        for (int[] fruit : fruits) {
            int index = fruit[0], amount = fruit[1];
            arr[index] = amount;
        }

        SegmentTree seg = new SegmentTree(arr);
        int left = 0, right = 0;
        if (arr[startPos] > 0) {
            left = right = ans = arr[startPos];
            seg.update(startPos, 0);
        }
        for (int i = startPos - 1; i >= Math.max(0, startPos - k); i--) {
            if (arr[i] > 0) {
                left += arr[i];
                seg.update(i, 0);
                // Check how much you can make to the right
                int rem = k - (startPos - i);
                ans = Math.max(ans, Math.max(left, left + seg.query(i, Math.min(len - 1, i + rem))));
            }
        }

        seg = new SegmentTree(arr);
        seg.update(startPos, 0);

        for (int i = startPos + 1; i < Math.min(len, startPos + k + 1); i++) {
            if (arr[i] > 0) {
                right += arr[i];
                seg.update(i, 0);
                // Check how much you can make to the left
                int rem = k - (i - startPos);
                ans = Math.max(ans, Math.max(right, right + seg.query(Math.max(0, i - rem), i)));
            }
        }
        return ans;
    }

    private int findMaxIndex(int[][] fruits) {
        int max = 0;
        for (int[] fruit : fruits) {
            if (fruit[0] > max)
                max = fruit[0];
        }
        return max;
    }

    static class SegmentTree {

        int[] seg;
        int[] arr;
        int len;

        public SegmentTree(int[] arr) {
            len = arr.length;
            this.arr = arr;
            seg = new int[len << 1];
            construct();
        }

        private void construct() {

            // Assign values to leaves of the segment Tree
            for (int i = 0; i < len; i++) {
                seg[len + i] = arr[i];
            }

            // Compute sum
            for (int i = len - 1; i >= 1; i--) {
                int pos = i << 1;
                seg[i] = seg[pos] + seg[pos + 1];
            }
        }

        public void update(int target, int value) {
            target += len;

            seg[target] = value;

            while (target > 1) {
                // Move up by one level
                target >>= 1;
                int pos = target << 1;
                seg[target] = seg[pos] + seg[pos + 1];
            }

        }

        public int query(int l, int r) {
            l += len;
            r += len;

            int res = 0;
            while (l <= r) {

                // If left index is odd
                if ((l & 1) == 1) {
                    res += seg[l];
                    l++; // make it even
                }

                // If right index is even
                if ((r & 1) == 0) {
                    res += seg[r];
                    r--;
                }

                // Move to the next higher level
                l >>= 1;
                r >>= 1;
            }
            return res;
        }

    }
}
