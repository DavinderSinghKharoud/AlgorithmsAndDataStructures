package LeetCode.WeeklyContest257;

import java.util.*;

public class FirstDayAllRooms {
    public static void main(String[] args) {
        FirstDayAllRooms o = new FirstDayAllRooms();
        System.out.println(o.firstDayBeenInAllRooms(new int[]{0, 1, 2, 0}));
    }

    int[][] dp;
    int[] nextVisit;

    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int len = nextVisit.length;
        this.nextVisit = nextVisit;
        if (len == 0)
            return 0;
        dp = new int[len][2];
        for (int[] d : dp)
            Arrays.fill(d, -1);
        dp[0][1] = 1; //odd
        dp[0][0] = 2; //even

        for (int i = 1; i < len; i++) {
            dp[i][1] = sum(dp[i - 1][0]); //odd
            dp[i][0] = sub(sum((2 * dp[i][1]) % mod), dp[nextVisit[i]][1]);//even
        }
        return dp[len - 1][1] - 1;
    }

    int mod = (int) (1e9 + 7);

    int sum(int a) {
        int sum = a + 1;
        while (sum > mod) sum -= mod;
        return sum;
    }

    int sub(int a, int b) {
        int sub = a - b;
        while (sub < 0) sub += mod;
        return sub;
    }
}
