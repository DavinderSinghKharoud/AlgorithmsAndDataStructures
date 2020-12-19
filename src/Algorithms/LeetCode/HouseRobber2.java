package Algorithms.LeetCode;


public class HouseRobber2 {

    public static int rob(int[] nums) {

        int n = nums.length;

        if( n == 0 ){
            return 0;
        }else if( n == 1){
            return nums[0];
        }

        int []dp1 = new int[n];
        int []dp2 = new int[n + 1];
        dp1[1] = nums[0];
        dp2[2] = nums[1];

        for( int index = 2; index<=nums.length; index++){
            dp1[index] = Math.max( dp1[index - 1], dp1[index - 2] + nums[index - 1]);
        }

        for( int index = 3; index<= nums.length; index++){
            dp2[index] = Math.max( dp2[ index - 1], dp2[index - 2] + nums[index - 1]);
        }

        return Math.max( dp1[n - 1], dp2[n]);
    }


    public static void main(String[] args) {

        System.out.println( rob( new int[]{
                2,3,2
        }));
    }
}
