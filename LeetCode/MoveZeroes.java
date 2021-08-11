package LeetCode;

/**
 * Given an array nums, write a function to move all 0's to the end of it while
 * maintaining the relative order of the non-zero elements.
 *
 * Example:
 *
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Note:
 *
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {

    public static void moveZeroes(int[] nums) {

        int replaceIndex = 0;
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != 0) {
                swap(i, replaceIndex, nums);
                replaceIndex++;
            }

        }
    }

    public static void swap(int index1, int index2, int[] nums) {

        int temp = nums[index2];
        nums[index2] = nums[index1];
        nums[index1] = temp;
    }

    public static void main(String[] args) {


        int[] arr = {
                0, 1, 0, 3, 12
        };
        moveZeroes(arr);

        for(int i = 0; i<arr.length; i++){
            System.out.println( arr[i]);
        }
    }
}
