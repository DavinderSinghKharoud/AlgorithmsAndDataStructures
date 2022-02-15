package LeetCode.WeeklyContest280;

import java.util.Arrays;

/**
 * You are given a 0-indexed array nums consisting of n positive integers.
 *
 * The array nums is called alternating if:
 *
 * nums[i - 2] == nums[i], where 2 <= i <= n - 1.
 * nums[i - 1] != nums[i], where 1 <= i <= n - 1.
 * In one operation, you can choose an index i and change nums[i] into any positive integer.
 *
 * Return the minimum number of operations required to make the array alternating.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,3,2,4,3]
 * Output: 3
 * Explanation:
 * One way to make the array alternating is by converting it to [3,1,3,1,3,1].
 * The number of operations required in this case is 3.
 * It can be proven that it is not possible to make the array alternating in less than 3 operations.
 * Example 2:
 *
 * Input: nums = [1,2,2,2,2]
 * Output: 2
 * Explanation:
 * One way to make the array alternating is by converting it to [1,2,1,2,1].
 * The number of operations required in this case is 2.
 * Note that the array cannot be converted to [2,2,2,2,2] because in this case nums[0] == nums[1] which violates the conditions of an alternating array.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 */
public class MinimumOperationsMakeArrayAlternating {
    public static void main(String[] args) {
        MinimumOperationsMakeArrayAlternating o = new MinimumOperationsMakeArrayAlternating();
        System.out.println(o.minimumOperations(new int[]{3, 1, 3, 2, 2, 3, 2, 3}));
    }

    public int minimumOperations(int[] nums) {
        int len = nums.length;
        if (len == 1) return 0;
        Node[] first = new Node[(int) 1e5 + 1];
        Node[] second = new Node[(int) 1e5 + 1];
        for (int i = 0; i < first.length; i++) {
            first[i] = new Node(i);
            second[i] = new Node(i);
        }

        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if ((i & 1) == 0) {
                first[num].count++;
            } else
                second[num].count++;
        }

        Arrays.sort(first, (o1, o2) -> Integer.compare(o2.count, o1.count));
        Arrays.sort(second, (o1, o2) -> Integer.compare(o2.count, o1.count));

        int firstLen = (len + 1) / 2, secondLen = len / 2;
        int ans = len;
        for (int i = 0; i < len; i++) {
            Node a = first[0], b = second[i];
            if (a.num == b.num) a = first[1];
            ans = Math.min(ans, firstLen - a.count + secondLen - b.count);
        }
        return ans;
    }

    static class Node {
        int num, count;

        public Node(int n) {
            num = n;
        }
    }
}
