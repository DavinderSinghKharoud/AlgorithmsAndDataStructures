package algorthims.LeetCode;

public class RotateArray {

    public static void rotate(int[] nums, int k) {

        for (int steps = 0; steps < k; steps++) {

            int previous = nums[0];
            for (int index = 1; index < nums.length; index++) {

                int current = nums[index];
                nums[index] = previous;
                previous = current;
            }
            nums[0] = previous;
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{
                1, 2, 3, 4, 5, 6, 7
        };
        rotate( arr, 3);

        for( int num: arr){
            System.out.println(num);
        }
    }

}
