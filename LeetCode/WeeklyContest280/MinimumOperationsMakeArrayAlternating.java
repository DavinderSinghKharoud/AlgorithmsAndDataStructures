package LeetCode.WeeklyContest280;

import java.util.Arrays;

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
        int index1 = 0;
        for (int i = 0; i < len && index1 < len; i++) {
            index1 = 0;
            while (index1 < len) {
                Node a = first[index1], b = second[i];
                if (a.num == b.num) index1++;
                else {
                    ans = Math.min(ans, firstLen - a.count + secondLen - b.count);
                    break;
                }
            }
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
