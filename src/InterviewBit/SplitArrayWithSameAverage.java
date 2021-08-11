package InterviewBit;

/**
 * In a given integer array A, we must move every element of A to either list B or list C. (B and C initially start empty.)
 * <p>
 * Return true if and only if after such a move, it is possible that the average value of B is equal to the average value of C, and B and C are both non-empty.
 * <p>
 * Example :
 * Input:
 * [1,2,3,4,5,6,7,8]
 * Output: true
 * Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
 * Note:
 * <p>
 * The length of A will be in the range [1, 30].
 * A[i] will be in the range of [0, 10000].
 */

import java.util.*;
import java.util.stream.IntStream;

public class SplitArrayWithSameAverage {

    /**
     * S1 + S2 = Sum
     * S1/x = S2/(n - x)
     * S1( n - x ) = S2 (x)
     * S1(n) - S1(x) = S2 (x)
     * S1(n) - ( Sum - S2 )x = S2(x)
     * S1(n) - Sum(x) = 0;
     * S1 = Sum(x)/n
     * <p>
     * So now our aim will be to check that can we get a sum of S1 using x elements.
     * Put diff values of x in eqn above(the one for S1) and check whether that S1 is possible with x no. of elements.
     */
    public static boolean splitArraySameAverage(int[] arr) {

        int len = arr.length;


        int sum = IntStream.of(arr).sum();

        Map<String, Boolean> map = new HashMap<>();

        for( int partition = 1; partition <= len/2; partition++ ){

            if( (sum * partition) % len == 0 ){ //Partition is possible

                if( helper( arr, 0, (sum * partition)/len, partition, map )){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean helper( int[] arr, int index, int possible_sum, int partition, Map<String, Boolean> map ){

        if( partition == 0 ){
            return possible_sum == 0;
        }

        if( index >= arr.length ) return false;

        if( map.containsKey( "" + index + possible_sum + partition ) ){
            return map.get("" + index + possible_sum + partition);
        }

        if( arr[index] <= possible_sum ){ //It means we can include the number

            if( helper(arr, index + 1, possible_sum - arr[index], partition - 1, map ) ){
                map.put( "" + index + possible_sum + partition, true);
                return true;
            }
        }

        if( helper( arr, index + 1, possible_sum, partition, map) ){ //If we do not take the number
            map.put( "" + index + possible_sum + partition, true);
            return true;
        }

        map.put( "" + index + possible_sum + partition, false );
        return false;
    }

    public static void main(String[] args) {

        System.out.println(splitArraySameAverage(new int[]{47, 14, 30, 19, 30, 4, 32, 32, 15, 2, 6, 24}));
    }
}
