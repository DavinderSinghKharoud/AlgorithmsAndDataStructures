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

    //O(n)
    public static int[] countBits1(int num) {
        int arr[] = new int[num+1];
        arr[0]=0;
        for(int i=1;i<=num;i++)
            arr[i] = i%2==0 ? arr[i/2] : arr[i/2]+1;
        return arr;
    }
    public static void main(String[] args) {
        int [] result = countBits1( 2 );

        for( int num: result ){
            System.out.println(num);
        }
    }
}
