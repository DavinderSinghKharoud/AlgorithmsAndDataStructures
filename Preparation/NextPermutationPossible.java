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
 *
 *  us start from example and see how our algorithm should work.
 * Imaigne n = 234157641. Our goal is to find next number with the same digits, which is greater than given one and which is the smallest one. It makes sense to try to take our number as close to original one as possible. Let us try to do it: can it start from 2......, yes, for example 24.... Can it start with 2341...? Yes, it can be 23417.... Can it start with 23415...? No, it can not, and the reason, that the rest what we have 7641 already biggest number given digits 7, 6, 4, 1.
 * So, we can see now, how our algorithm should work:
 *
 * Start from the end and look for increasing pattern, it our case 7641.
 * If it happen, that all number has increasing pattern, there is no bigger number with the same digits, so we can return -1.
 * Now, we need to find the first digit in our ending, which is less or equal to digits[i-1]: we have ending 5 7641 and we are looking for the next number with the same digits. What can go instead of 5: it is 6! Let us change these two digits, so we have 6 7541 now. Finally, we need to reverse last ditits to get 6 1457 as our ending.
 * Complexity: time complexity is O(m), where m is number of digits in our number, space complexity O(m) as well.
 *
 * PS see also problem 31. Next Permutation, which uses exactly the same idea.
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
