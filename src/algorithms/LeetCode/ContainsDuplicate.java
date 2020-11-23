package algorithms.LeetCode;

import java.util.Arrays;

public class ContainsDuplicate {

    public static boolean containsDuplicate(int[] nums) {

        if( nums.length == 0 || nums == null ){
            return false;
        }
        Arrays.sort( nums );
        int value = nums[0];
        for( int i = 1; i<nums.length; i++ ){

            if( value == nums[i]){
                return true;
            }

            value = nums[i];
        }

        return false;
    }

    public static void main(String[] args) {

        System.out.println( containsDuplicate(new int[]{
                1,2,3,4,1
        }));
    }
}
