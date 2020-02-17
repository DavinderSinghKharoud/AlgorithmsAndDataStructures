package algorthims.LeetCode;

import java.util.HashMap;

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist
 * in the array.
 */
public class MajorityElement {

    public static void main(String[] args) {

        System.out.println( majorityElement(new int[]{
                2,2,1,1,1,2,2
        }));

    }


    public static int majorityElement(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int len = nums.length/2;

        for( int i = 0; i<nums.length; i++){


            if( map.containsKey(nums[i])){


                int value = map.get( nums[i]);
                value++;

                map.put( nums[i], value);
                if( value>len){
                    return nums[i];
                }
            }else{
                 map.put( nums[i], 1);
            }
        }

        return nums[0];

    }
}
