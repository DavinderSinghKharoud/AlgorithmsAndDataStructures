package InterviewBit;

import java.util.Arrays;

public class ChainOfPairs {

    public static int solve(int[][] arr) {

        int[] dp = new int[arr.length];
        Arrays.fill(dp, 1);
        int res = -1;
        for (int row = 1; row < arr.length; row++) {
            int val = arr[row][0];
            for (int index = 0; index < row; index++) {
                if (val > arr[index][1]) {
                    dp[row] = Math.max(dp[row], dp[index] + 1);
                }
            }
            res = Math.max(res, dp[row]);
        }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(solve(new int[][]{{5, 24}, {39, 60}, {15, 28}, {27, 40}, {50, 90},}));
    }
}
