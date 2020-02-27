package algorthims.LeetCode;

public class CountingBits {

    public static int[] countBits(int num) {
        int[] res = new int[num+1];
        int p = -1;
        res[0] = 0;
        for(int i=1; i<=num; i++){
            if(i==Math.pow(2, p+1)){
                res[i] = 1;      // since num is divisible by power of 2
                p++;
            }
            else{
                res[i] = 1 + res[i%(int)Math.pow(2, p)];       // 1 for the power of 2 and remaining for remainder, can be fetched from previous values
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int [] result = countBits( 2 );

        for( int num: result ){
            System.out.println(num);
        }
    }
}
