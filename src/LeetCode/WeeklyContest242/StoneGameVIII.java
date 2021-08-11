package LeetCode.WeeklyContest242;

public class StoneGameVIII {
    public static void main(String[] args) {
        StoneGameVIII o = new StoneGameVIII();
        System.out.println(o.stoneGameVIII(new int[]{-1, 2, -3, 4, -5}));
    }

    public int stoneGameVIII(int[] stones) {
        int len = stones.length;
        int[] prefix = new int[len];
        prefix[0] = stones[0];
        for (int i = 1; i < len; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        int[] dp = new int[len];
        dp[len - 1] = prefix[len - 1];

        for (int i = len - 2; i >= 0; i--) {
            // Optimize using sum[j] - game[j] is max j > i
            //We do not take the value
            int max = Math.max(prefix[len - 1], dp[i + 1]);
            if (i < len - 2) {
                // Because for n - 2 there is only one way
                //We take the value
                max = Math.max(max, prefix[i + 1] - dp[i + 1]);
            }

            dp[i] = max;
        }
        return dp[0];
    }
}
