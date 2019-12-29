package algorthims.AbsoluteDifference;

import java.util.Scanner;

/**
 * Complete the Difference class by writing the following:
 *
 * A class constructor that takes an array of integers as a parameter and
 * saves it to the elements instance variable.
 * A computeDifference method that finds the maximum absolute difference
 * between any  numbers in  and stores it in the  instance variable.
 */
public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        sc.close();

        Difference difference = new Difference(a);

        difference.computeDifference();

        System.out.print(difference.maximumDifference);
    }
}
