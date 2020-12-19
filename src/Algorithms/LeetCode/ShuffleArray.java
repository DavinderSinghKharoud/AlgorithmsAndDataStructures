package Algorithms.LeetCode;


import java.util.Random;

public class ShuffleArray {

    private int[] nums;
    private int[] copy;
    private Random rand = new Random();



    public ShuffleArray(int[] nums) {
        this.nums = nums;
        copy = nums.clone();
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        nums = copy;
        copy = copy.clone();
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {

        for (int i = 0; i < nums.length; i++) {
          swap(i, randRange(i, nums.length));

        }
        return nums;
    }

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swap(int i, int change) {
        int temp = nums[i];
        nums[i] = change;
        nums[change] = temp;
    }


    public static void main(String[] args) {
        ShuffleArray obj = new ShuffleArray(new int[]{
                1, 2, 3
        });

        int[] x = obj.shuffle();
        for (int num : x) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] y = obj.reset();
        for (int num : y) {
            System.out.print(num + " ");
        }
    }
}
