package algorthims.LeetCode;

import java.util.Arrays;
import java.util.Stack;


public class ShortestUnsortedContinousArray {

    //Sort the Array
    public static int findUnsortedSubarray(int[] nums) {

        int []numsCopy = nums.clone();
        Arrays.sort( numsCopy );
        int start = numsCopy.length, end = 0;

        for(int i = 0; i<numsCopy.length; i++){

            if( numsCopy[i] != nums[i]){
                start = Math.min( start, i );
                end = Math.max( end, i);
            }

        }

        return (end - start >= 0 ? end-start+1 : 0);
    }

    //Stack
    public int findUnsortedSubarrayStack(int[] nums) {

        Stack<Integer> stack = new Stack<Integer>();
        int start = nums.length, end = 0;

        for( int i = 0; i<nums.length; i++){

            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]){
                start = Math.min( start, stack.pop());
            }
            stack.push( i );

        }
        stack.clear();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i])
                end = Math.max(end, stack.pop());
            stack.push(i);
        }

        return end-start > 0? end-start+1:0;
    }

    public static void main(String[] args) {

        System.out.println(findUnsortedSubarray(new int[]{
                4,3,2,1
        }));
    }
}
