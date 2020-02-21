package algorthims.LeetCode;

/**
 * Given a non-empty array of integers, every element appears twice except for one.
 * Find that single one.
 *
 * Note:
 *
 * Your algorithm should have a linear runtime complexity. Could you implement
 * it without using extra memory?
 *
 * Example 1:
 *
 * Input: [2,2,1]
 * Output: 1
 */
public class SingleNumber {

    //Brute Force
    public static int singleNumber(int[] nums) {

        for( int i = 0; i<nums.length; i++){

            int index = Math.abs( nums[i] );
            int value = nums[index];
            nums[index] = -1 * value;

        }

        for( int i = 0; i<nums.length; i++ ){

            if( nums[i] < 0 ){
                return i;
            }
        }

        return -1;
    }

    //O(n)
    public int singleNumber1(int[] nums) {

        int res = 0;
        for (int num : nums) {

            // XOR operation to cancel duplicates
            res = res ^ num;
        }

        return res;
    }
    public static void main(String[] args) {

        System.out.println( singleNumber(new int[]{
                4,1,2,1,2
        }));
    }
}
