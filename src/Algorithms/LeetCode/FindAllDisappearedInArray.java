package Algorithms.LeetCode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array),
 * some elements appear twice and others appear once.
 *
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 *
 * Could you do it without extra space and in O(n) runtime? You may assume
 * the returned list does not count as extra space.
 *
 * Example:
 *
 * Input:
 * [4,3,2,7,8,2,3,1]
 *
 * Output:
 * [5,6]
 */
public  class FindAllDisappearedInArray {

    public static List<Integer> findDisappearedNumbers(int[] nums) {

        List<Integer> lst = new ArrayList<>();

        for( int i = 0; i<nums.length; i++){

            int tem = Math.abs( nums[i] ) - 1;
            if( nums[tem] > 0){
                nums[tem] = -nums[tem];
            }
        }

        for( int i = 0; i<nums.length; i++){
            if(nums[i]>0){
                lst.add(i+1);
            }
        }

        return lst;
    }

    public static void main(String[] args) {
        System.out.println( findDisappearedNumbers( new int[]{
                4,3,2,7,8,2,3,1
        }));
    }
}
