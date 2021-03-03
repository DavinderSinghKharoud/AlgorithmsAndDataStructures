
public class SparseTableMin {

    int[][] dp;

    public SparseTableMin(int[] arr){
        int log = log2(arr.length);

        dp = new int[arr.length][log + 1];

        for( int i = 0; i < arr.length; i++ ){
            dp[i][0] = arr[i];
        }

        //construct a sparse table

        for( int i = 1; i <= log ; i++ ){
            for(int j = 0; j + (1 << i) <= arr.length; j++){
                dp[j][i] = Math.min(dp[j][i - 1], dp[j + (1 << (i - 1) )][i - 1]);
            }
        }

    }

    public int rangeMinQuery(int low, int high ){
        int len = high - low + 1;
        int k = log2(len);

        if( dp[low][k] <= dp[high - (1 << k) + 1][k] ){
            return dp[low][k];
        }

        return dp[high - ( 1<< k) + 1][k];
    }

    private int log2(int len) {
         return 31 - Integer.numberOfLeadingZeros(len);
    }
}
