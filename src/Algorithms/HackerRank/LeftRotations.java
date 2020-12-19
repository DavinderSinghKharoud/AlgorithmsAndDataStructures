package Algorithms.HackerRank;

public class LeftRotations {

    //O(n) time and space complexity
    public static int[] rotateLeft(int d, int[] arr) {

        int len = arr.length;

        int[] res = new int[len];

        for (int index = 0; index < len; index++) {
            int finalIndex = index - d;
            if (finalIndex < 0) { //Mod the value
                finalIndex = -1 * (Math.abs(finalIndex) % len);
            }
            if (finalIndex < 0) { //If still less than zero
                finalIndex += len;
            }

            res[finalIndex] = arr[index];

        }

        return res;
    }

    public static void main(String[] args) {

        //System.out.println(Arrays.toString(rotateLeft(7, new int[]{1, 2, 3})));
        rotateLeft2(7, new int[]{1, 2, 3});
    }

    //O(n) time and O(1) space complexity
    public static void rotateLeft2(int d, int[] arr) {
        int len = arr.length;
        int mod = d % len;

        for (int index = 0; index < len; index++) {
            int value = arr[(index + mod) % len];
            System.out.print(value + " ");
        }


    }
}
