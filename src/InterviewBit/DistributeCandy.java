package InterviewBit;

public class DistributeCandy {

    //O(n) time and space complexity
    public static int candy(int[] arr) {
        int[] candies = new int[arr.length];

        //Traverse from left to right
        for (int index = 1; index < arr.length; index++) {
            if (arr[index] > arr[index - 1]) {
                candies[index] = candies[index - 1] + 1;
            }
        }

        int res = candies[arr.length - 1];
        //Traverse from right to left
        for (int index = arr.length - 2; index >= 0; index--) {
            if (arr[index] > arr[index + 1]) {
                candies[index] = Math.max(candies[index], candies[index + 1] + 1);
            }
            res += candies[index];
        }
        return res + arr.length;
    }

    public static void main(String[] args) {

        System.out.println(candy(new int[]{1, 5, 2, 1}));
    }
}
