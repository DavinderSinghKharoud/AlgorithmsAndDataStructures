package LeetCode;

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

    //Reversing
    public static void rotate1(int[] nums, int k) {

        k %= nums.length;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private static void reverse(int[] nums, int start, int end) {

        while (start < end){
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {

        int[] arr = new int[]{
                1, 2, 3, 4, 5, 6, 7
        };
        rotate1(arr, 3);

        for (int num : arr) {
            System.out.println(num);
        }
    }

}
