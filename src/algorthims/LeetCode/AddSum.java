package algorthims.LeetCode;

public class AddSum {
    public static int[] twoSum(int[] nums, int target) {

        int []result = new int[2];

        for( int i = 0; i<nums.length; i++){

            int remaining = target - nums[i];
            for( int j = i + 1; j<nums.length; j++){

                if( nums[j] == remaining ){

                    result[0] = i;
                    result[1] = j;
                    return result;
                }

            }
        }

        return result;
    }

    public static void main(String[] args) {

        printArr( twoSum( new int[]{
                3,2,4
        },6));
    }

    private static void  printArr( int[] nums){
        for(int i = 0; i<nums.length; i++){
            System.out.println(nums[i]);
        }
    }
}
