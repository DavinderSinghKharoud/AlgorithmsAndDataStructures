package algorthims.LeetCode.DynamicProgrammingSpecific;

import java.util.Arrays;

public class ReducingDishes {

    //O(n square ) time complexity and O(n) space complexity
    public static int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int len = satisfaction.length;
        if (len == 0) return 0;
        int[] dp = new int[len];

        for (int i = 0; i < len; i++) {
            dp[i] = getSum(satisfaction, i);
            if (i != 0 && dp[i - 1] > dp[i]) {
                return dp[i - 1];
            }
        }

        return 0;
    }

    public static int getSum(int[] satisfaction, int start) {

        int sum = 0;
        int time = 1;
        for (int i = start; i < satisfaction.length; i++) {
            sum += satisfaction[i] * time++;
        }

        return sum;
    }

    public static void main(String[] args) {

        System.out.println(maxSatisfaction(new int[]{
                -1, -8, 0, 5, -9
        }));
    }
}
