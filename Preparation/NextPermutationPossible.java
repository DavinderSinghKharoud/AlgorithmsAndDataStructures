package Preparation;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 *
 * If such an arrangement is not possible, it must rearrange it as the lowest possible order (i.e., sorted in ascending order).
 *
 * The replacement must be in place and use only constant extra memory.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [1,3,2]
 * Example 2:
 *
 * Input: nums = [3,2,1]
 * Output: [1,2,3]
 * Example 3:
 *
 * Input: nums = [1,1,5]
 * Output: [1,5,1]
 * Example 4:
 *
 * Input: nums = [1]
 * Output: [1]
 */
public class NextPermutationPossible {
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        if(len <= 1) return;
        int find = -1;
        for(int i = len - 2; i >= 0; i--){
            if(nums[i] < nums[i + 1]){
                find = i;
                break;
            }
        }
        if(find == -1) {
            Arrays.sort(nums);
            return;
        }

        //find next greater
        int next = find + 1;
        for(int i = find + 1; i < len; i++){
            if(nums[i] > nums[find] && nums[next] >= nums[i] ){
                next = i;
            }
        }
        //swap
        swap(find, next, nums);
        //Sort the remaining to find next minimum

        reverse(find + 1, nums);
    }

    void reverse(int start, int[] nums){
        int end = nums.length - 1;
        while(start <= end){
            swap(start++, end--, nums);
        }
    }

    void swap(int a, int b, int[] nums){
        nums[a] = nums[a] ^ nums[b] ^ (nums[b] = nums[a]);
    }
}
