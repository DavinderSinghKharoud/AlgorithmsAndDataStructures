package LeetCode.WeeklyContest276;

import java.util.*;

public class SolvingQuestionsBrainPower {
    public static void main(String[] args) {
        SolvingQuestionsBrainPower o = new SolvingQuestionsBrainPower();
        System.out.println(o.mostPoints(new int[][]{
                {3, 2}
        }));
    }

    public long mostPoints(int[][] questions) {
        int len = questions.length;
        long[] dp = new long[len + 1];

        for (int i = len - 1; i >= 0; i--) {
            int points = questions[i][0], brain = questions[i][1];
            dp[i] = Math.max(dp[i + 1], points + ((i + brain + 1 < len) ? dp[i + brain + 1] : 0));
        }
        return dp[0];
    }

}
