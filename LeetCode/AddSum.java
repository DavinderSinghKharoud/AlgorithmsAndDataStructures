package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class AddSum {

    //( N ) Square
    public static int[] twoSum(int[] nums, int target) {

        int []result = new int[2];

        for( int i = 0; i<nums.length; i++){

            int remaining = target - nums[i];
            for( int j = i + 1; j<nums.length; j++){

                if( nums[j] == remaining ){

                    result[0] = i;
                    result[1] = j;
                    return result;
                }

            }
        }

        return result;
    }

    //Hash table
    public static int[] twoSumHash(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();

        for(int i =0; i<nums.length; i++){
            int complement = target - nums[i];

            if( map.containsKey(complement)){
                return new int[] {map.get(complement), i};
            }

            map.put( nums[i], i);
        }

        return new int[] {};
    }
    public static void main(String[] args) {

//        printArr( twoSum( new int[]{
//                3,2,4
//        },6));

        printArr( twoSumHash( new int[]{
                3,2,4
        }, 6));
    }

    private static void  printArr( int[] nums){
        for(int i = 0; i<nums.length; i++){
            System.out.println(nums[i]);
        }
    }
}
