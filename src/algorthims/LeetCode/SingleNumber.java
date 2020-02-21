package algorthims.LeetCode;

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

        return nums[0];
    }
    public static void main(String[] args) {

        System.out.println( singleNumber(new int[]{
                4,1,2,1,2
        }));
    }
}
