package algorthims.LeetCode;

public class ClimbingStairs {

    //Brute Force
    public static int climbStairs(int i,int n) {

        if(i > n ){
            return 0;
        }
        if( i == n){
            return 1;
        }

        return climbStairs(i + 1,n) + climbStairs(i + 2, n);
    }

    //Brute Force Memorization
    public int climb( int n){
        int memo[] = new int[n + 1];
        return climeBrute(0, n, memo);
    }

    private int climeBrute(int i, int n, int[] memo) {
        if( i>n ){
            return 0;
        }
        if( i == n){
            return 1;
        }

        if( memo[i]>0){
            return memo[i];
        }

        memo[i] = climeBrute(i + 1, n, memo) + climeBrute(i + 2, n, memo);
        return memo[i];
    }

    //Dynamic Solution O(n)
    public static int ClimbStairs(int n){
        if( n == 1){
            return 1;
        }

        int []dp  = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;

        for(int i = 3; i<=n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];

    }
    public static void main(String[] args) {

        System.out.println( ClimbStairs(5));
    }
}
