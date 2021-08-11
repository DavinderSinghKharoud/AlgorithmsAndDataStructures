package LeetCode;

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

        public static int[] productExceptSelf2(int[] nums) {

            // The length of the input array
            int length = nums.length;

            // Final answer array to be returned
            int[] answer = new int[length];

            // answer[i] contains the product of all the elements to the left
            // Note: for the element at index '0', there are no elements to the left,
            // so the answer[0] would be 1
            answer[0] = 1;
            for (int i = 1; i < length; i++) {

                // answer[i - 1] already contains the product of elements to the left of 'i - 1'
                // Simply multiplying it with nums[i - 1] would give the product of all
                // elements to the left of index 'i'
                answer[i] = nums[i - 1] * answer[i - 1];
            }

            // R contains the product of all the elements to the right
            // Note: for the element at index 'length - 1', there are no elements to the right,
            // so the R would be 1
            int R = 1;
            for (int i = length - 1; i >= 0; i--) {

                // For the index 'i', R would contain the
                // product of all elements to the right. We update R accordingly
                answer[i] = answer[i] * R;
                R *= nums[i];
            }

            return answer;
        }

    public static void main(String[] args) {

        int[] res = productExceptSelf2(new int[]{

                1, 2, 3, 4
        });

        for (int num : res) {
            System.out.println(num);
        }
    }
}
