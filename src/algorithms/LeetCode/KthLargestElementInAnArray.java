package algorithms.LeetCode;

import java.util.*;
/*
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
 */
public class KthLargestElementInAnArray {
    public static int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
    public static int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> minH =
                new PriorityQueue<>();

        for(int num: nums){
            minH.add( num);
            if( minH.size() > k){
                minH.remove();
            }

        }

        return minH.remove();
    }

    public static void main(String[] args) {

        System.out.println(findKthLargest2(new int[]{
                3,2,3,1,2,4,5,5,6
        }, 4));
    }
}
