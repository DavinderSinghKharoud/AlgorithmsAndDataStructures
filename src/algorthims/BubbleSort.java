package algorthims;

import java.util.Scanner;

/**
 * Given an array, , of size  distinct elements, sort the array in ascending order
 * using the Bubble Sort algorithm above. Once sorted, print the following  lines:
 *
 * Array is sorted in numSwaps swaps.
 * where  numSwaps is the number of swaps that took place.
 * First Element: firstElement
 * where firstElement is the first element in the sorted array.
 * Last Element: lastElement
 * where lastElement is the last element in the sorted array.
 *
 * Constraints:
 * 2<=n<=600
 */

public class BubbleSort {

    static int numSwaps = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the size: ");
        int n = in.nextInt();
        int[] a = new int[n];

        for(int a_i=0; a_i < n; a_i++){
            System.out.print("Enter the number: ");
            a[a_i] = in.nextInt();
        }


        for (int i = 0; i < n; i++) {
            // Track number of elements swapped during a single array traversal
            int numberOfSwaps = 0;

            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {

                    //swap elements
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;

                    numSwaps++;
                    numberOfSwaps++;
                }
            }

            // If no elements were swapped during a traversal, array is sorted
            if (numberOfSwaps == 0) {
                break;
            }

        }


        System.out.println("Array is sorted in "+numSwaps+" swaps.\n"+
                "First Element: "+a[0]+"\nLast Element: "+a[a.length-1]);
        
        
    }


}
