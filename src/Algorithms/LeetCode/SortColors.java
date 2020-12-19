package Algorithms.LeetCode;

import java.util.Arrays;

public class SortColors {

    public static void sortColors(int[] nums) {
        int countZeroes = 0;
        int countOne = 0;
        int countTwo = 0;

        for( int num: nums ){
            if( num == 0 ){
                countZeroes++;
            }else if( num == 1 ){
                countOne++;
            }else{
                countTwo++;
            }
        }

        int index = 0;
        while( countZeroes != 0 ){
            nums[index++] = 0;
            countZeroes--;
        }

        while( countOne != 0 ){
            nums[index++] = 1;
            countOne--;
        }

        while( countTwo != 0 ){
            nums[index++] = 2;
            countTwo--;
        }

    }

    public static void main(String[] args) {
        int[] arr = { 2,0,2,1,1,0 };
        sortColors2( arr );
        System.out.println(Arrays.toString(arr));
    }

    /**
     * Here we have 3 indices low, mid and high. If you see we want to break this problem in 4 ranges i.e from 0 to low(consisting of 0s), low to mid(consisting of 1s), mid to high(consisting of unknowns), and finally from high to n - 1(consisting of 2s).
     * Now since the unknowns lie between mid to high we will run our loop from mid to high.
     * If we hit a 0 then we will swap the element at index low to element at index mid and we will increment both low and mid.
     * Why is that ?
     * Reason being low to mid will consist of 1, so on swapping we bring a 0 at low and 1 at mid, and that's why we increment low and mid.
     * If we hit a 1 then we are good to go. We will simple increment mid by 1.
     * If we hit a 2 then we will swap the element at index high to element at index mid and we will decrement high by 1.
     * Reason being mid to high will consist of unknowns, so on swapping we bring a 2 at high and unknown at mid, and that's why we decrement high by 1, while mid remains same.
     * @param nums
     */
    public static void sortColors2(int[] nums) {
        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while( mid <= high ){
            if( nums[mid] == 0 ){
                swap(nums, mid, low );
                low++;
                mid++;
            }else if( nums[mid] == 1 ){
                mid++;
            }else{
                swap( nums, mid, high );
                high--;
            }
        }
    }

    public static void swap( int[] nums, int first, int second ){
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
