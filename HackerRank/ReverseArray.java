package HackerRank;

import java.util.Arrays;

/**
 * An array is a type of data structure that stores elements of the same type in a contiguous block of memory. In an array, , of size , each memory location has some unique index,  (where ), that can be referenced as  or .
 * Reverse an array of integers.
 * Note: If you've already solved our MinimizeDifference++ domain's Arrays Introduction challenge, you may want to skip this.
 * Example [ 1, 2 ,3 ] -> [ 3, 2, 1 ]
 *
 * Return .
 */
public class ReverseArray {

    // Complete the reverseArray function below.
    static int[] reverseArray(int[] a) {

        int limit = (a.length % 2 == 0 )? a.length/2 - 1: a.length/2;
        for (int index = 0; index <= limit; index++) {
           int temp = a[index];
           a[index] = a[a.length - 1 - index];
           a[a.length - 1 - index] = temp;
        }
        return a;
    }
    public static void main(String[] args) {

        System.out.println(Arrays.toString(reverseArray(new int[]{1, 2, 3, 4, 5})));
    }
}
