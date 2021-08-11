package LeetCode;

/**
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
 * calculate the number of 1's in their binary representation and return them as an array.
 */
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

    /**
     * In decimal, when you multiply by 10 a zero is added.
     * Similarly if you multiply by 2 to a binary number, a zero is added and and
     * odd number ends with 1.
     * So when a even number 'n' is encountered, it will have same number of 1's as n/2
     * And an odd number will have bits in n/2 +1
     * @param num
     * @return
     */
    public static int[] countBits1(int num) {
        int arr[] = new int[num+1];
        arr[0]=0;
        for(int i=1;i<=num;i++)
            arr[i] = i%2==0 ? arr[i/2] : arr[i/2]+1;
        return arr;
    }

    public static void main(String[] args) {
        int [] result = countBits( 11 );

        for( int num: result ){
            System.out.println(num);
        }
    }
}
