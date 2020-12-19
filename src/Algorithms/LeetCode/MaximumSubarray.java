package Algorithms.LeetCode;

public class MaximumSubarray {

    //Brute force
    public static int maxSubArray(int[] nums) {

        int max_sum = nums[0];
        int sum = 0;

        for( int i = 0; i<nums.length; i++ ){


            sum += nums[i];

            if( max_sum < sum ){
                max_sum = sum;
            }

            if( sum < 0 ){
                sum = 0;
            }


        }

        return max_sum;

    }

    public static void main(String[] args) {

        System.out.println( maxSubArray(new int[]{
                1,2
        }));
    }

    public static int maxArr( int[] nums){
        int dp[] = new int[ nums.length];
        int max = nums[0];
        dp[0] = nums[0];

        for( int i = 1; i<dp.length; i++){

            dp[i] = Math.max( nums[i], nums[i] + dp[i-1]);
            max = Math.max( dp[i], max);
        }

        return max;
    }
}
