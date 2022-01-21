package LeetCode.WeeklyContest276;

public class MinMovesReachTarget {
    public static void main(String[] args) {
        MinMovesReachTarget o = new MinMovesReachTarget();
        System.out.println(o.minMoves(100, 4));
    }

    public int minMoves(int target, int maxDoubles) {
        int ans = 0;

        while (maxDoubles-- > 0 && target > 1) {
            if (target % 2 == 0) {
                ans++;
            } else
                ans += 2;
            target /= 2;
        }

        return ans + (target - 1);
    }
}
