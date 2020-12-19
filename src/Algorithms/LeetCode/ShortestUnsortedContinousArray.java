package Algorithms.LeetCode;

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


    //Most Efficient
    public static int findUnsortedSubarrayMostEfficient(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        boolean flag = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1])
                flag = true;
            if (flag)
                min = Math.min(min, nums[i]);
        }
        flag = false;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1])
                flag = true;
            if (flag)
                max = Math.max(max, nums[i]);
        }
        int start, end;

        for( start = 0; start<nums.length; start++){
            if( min < nums[start]){
                break;
            }
        }

        for( end = nums.length - 1; end>=0 ; end--){
            if( max > nums[end]){
                break;
            }
        }

        return end - start < 0 ? 0:end-start+1;
    }
    public static void main(String[] args) {

        System.out.println(findUnsortedSubarrayMostEfficient(new int[]{
                1,2,3,4
        }));
    }
}
