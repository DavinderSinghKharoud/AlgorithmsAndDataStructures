package LeetCode.DynamicProgrammingSpecific;

import java.util.Arrays;

public class ReducingDishes {

    //O(n square ) time complexity and O(n) space complexity
    public static int maxSatisfaction1(int[] satisfaction) {
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

        System.out.println(maxSatisfaction2(new int[]{
                -1, -8, 0, 5, -9
        }));
    }

    public static int maxSatisfaction2(int[] satisfaction) {

        int len = satisfaction.length;
        if( len == 0 ) return 0;

        Arrays.sort( satisfaction );
        int res = 0, curr = 0, prefix = 0;

        for( int i = len - 1; i >= 0; i-- ){
            //Since satisfaction level is time[i]*satisfaction[i]
            //with each passing timeunit we need to add 1 to time[i]
            /*
            We consider the most satisfying dish as the end the
            Example - [-8, -7, -1, 0 , 5]
            t = 1 prefix = 5, curr = 5 is the satisfaction level
            t = 2 now satisfaction level should be 0*1 + 2*5  = 10,
            prefix = 5 + 0, curr = curr + prefix as we need to increase curr with prefix
            mathematical - n*x = n + n ..-x times-...+ n
            (n+1)x = nx + x - here x is prefix and nx is curr
            */
            prefix += satisfaction[i];
            curr += prefix;
            res = Math.max(res, curr);
        }

        return Math.max(res, 0);
    }
}
