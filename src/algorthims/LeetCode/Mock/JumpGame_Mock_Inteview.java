package algorthims.LeetCode.Mock;

public class JumpGame_Mock_Inteview {

    public static boolean canJump(int[] nums) {
        int len = nums.length;
        if( len == 0 ){
            return false;
        }
        int[] dp = new int[len];
        dp[len - 1] = len - 1;

        for(int i = len - 2; i >= 0; i-- ){
            int reach = Math.min( len - 1, nums[i] + i );
            for( int j = i + 1; j <= reach; j++ ){
                reach = Math.max( dp[j], reach );
            }
            dp[i] = reach;
        }

        return dp[0] == len - 1;
    }
    public static void main(String[] args) {

        System.out.println( canJump( new int[]{
                3,2,1,0,4
        }));

        System.out.println( canJump( new int[]{
                2,3,1,1,4
        }));
    }
}
