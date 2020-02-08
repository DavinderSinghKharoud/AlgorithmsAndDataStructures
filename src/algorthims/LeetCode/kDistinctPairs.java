package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of integers and an integer k, you need to find the number of unique
 * k-diff pairs in the array. Here a k-diff pair is defined as an integer pair (i, j),
 * where i and j are both numbers in the array and their absolute difference is k.
 *
 * Example 1:
 * Input: [3, 1, 4, 1, 5], k = 2
 * Output: 2
 * Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
 * Although we have two 1s in the input, we should only return the number of unique pairs.
 */
public class kDistinctPairs {

    public static void main(String[] args) {
        System.out.println( getPairs(new int[]{
                1,3,1,5,4
        }, 0
        ));
    }

    private static int getPairs(int[] nums, int k) {

        HashMap<Integer, List<Integer> > map = new HashMap<>();
        int count = 0;

        for( int i = 0; i<nums.length; i++){

            List<Integer> temp = new ArrayList<>();
            map.put( nums[i], temp);
        }

        for( int i = 0; i<nums.length; i++){

            for( int j = i+1; j<nums.length; j++){

                if( Math.abs( nums[i] - nums[j]) == k  ){

                    if( !map.get( nums[i] ).contains( nums[j] ) && !map.get( nums[j] ).contains(nums[i]) ){
                        map.get( nums[i] ).add( nums[j] );
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
