package algorthims.InterviewBit;


import java.util.Arrays;

/**
 * Given an array A, of N integers A.
 *
 * Return the highest product possible by multiplying 3 numbers from the array.
 *
 * NOTE: Solution will fit in a 32-bit signed integer.
 *
 *
 *
 * Input Format:
 *
 * The first and the only argument is an integer array A.
 *
 * Output Format:
 *
 * Return the highest possible product.
 *
 * Constraints:
 *
 * 1 <= N <= 5e5
 *
 * Example:
 *
 * Input 1:
 * A = [1, 2, 3, 4]
 *
 * Output 1:
 * 24
 *
 * Explanation 1:
 * 2 * 3 * 4 = 24
 *
 * Input 2:
 * A = [0, -1, 3, 100, 70, 50]
 *
 * Output 2:
 * 350000
 *
 * Explanation 2:
 * 70 * 50 * 100 = 350000
 */
public class HighestProduct {

    //O(nLogn) time complexity
    public static int maxp3(int[] arr) {

        Arrays.sort(arr);
        int len = arr.length;
        return Math.max(arr[0] * arr[1] * arr[len - 1], arr[len - 1] * arr[len - 2] * arr[len
                - 3]);
    }

    public static int maxp3_SecondVersion(int[] arr) {

        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 =  Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for(int num: arr ){

            if( num < min1 ){
                min2 = min1;
                min1 = num;
            }else if( num < min2 ){ //If it is smaller than min2
                min2 = num;
            }

            if( num > max1 ){ //If it is greater than max1
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if( num > max2 ){
                max3 = max2;
                max2 = num;
            }else if( num > max3 ){
                max3 = num;
            }
        }

        return Math.max(  min1 * min2 * max1, max1 * max2 * max3 );
    }

    public static void main(String[] args) {

        System.out.println(maxp3_SecondVersion(new int[]{-10000000, 1, 2, 3, 4}));
    }
}
