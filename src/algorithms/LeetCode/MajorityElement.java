package algorithms.LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Given an array of size n, find the majority element.
 * The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always exist
 * in the array.
 */
public class MajorityElement {

    public static void main(String[] args) {

        /*We can solve in many ways
        1.] brute force (  O(n square) time complexity )
        2.] Using space ( O(n) space and time complexity )
        3.] Using sorting ( O(nLog(n)) time complexity
        4.] Boyer-Moore Voting Algorithm ( O(n) time and O(1) space complexity )
        */
        System.out.println( majorityElement2(Arrays.asList( 2,2,1,1,1,2,2)) );

    }

    //O(n) time and O(1) space complexity
    public static int majorityElement2(List<Integer> lst) {

        int total = 0;
        Integer candidate = null;
        
        for(int num: lst ){
			
			if( total == 0 ){
				candidate = num;
			}
			
			if( num == candidate ){
				total++;
			}else{
				total--;
			}
			
		}
		
		return candidate;
    }

    //O(N) time and space complexity
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
