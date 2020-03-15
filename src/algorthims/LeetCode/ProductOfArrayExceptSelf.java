package algorthims.LeetCode;

public class ProductOfArrayExceptSelf {

    public static int[] productExceptSelf(int[] nums) {

        if (nums.length == 0) {
            return new int[0];
        }
        int[] res = new int[nums.length];

        int[] left = new int[nums.length];
        int[] right = new int[nums.length];


        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                left[i] = nums[i];
                continue;
            }
            left[i] = nums[i] * left[i - 1];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                right[i] = (nums[i]);
                continue;
            }
            right[i] = (nums[i] * right[i + 1]);
        }

        res[0] = right[1];
        res[nums.length - 1] = left[nums.length - 2];
        for (int i = 1; i < nums.length - 1; i++) {
            res[i] = left[i - 1] * right[i + 1];
        }
        return res;

    }

    public static void main(String[] args) {

        int[] res = productExceptSelf(new int[]{

                1, 2, 3, 4
        });

        for (int num : res) {
            System.out.println(num);
        }
    }
}
