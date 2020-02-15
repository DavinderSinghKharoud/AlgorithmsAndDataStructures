package algorthims.LeetCode;

/**
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 *
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 */
public class ArrangingCoins {

    public static int arrangeCoins(int n) {

        int count = 1;

        while ( true ){
            n -= count;

            if( n < 0 ){
                return count - 1;
            }

            count++;
        }
    }

    public static void main(String[] args) {
        System.out.println( arrangeCoins(8));
    }
}
