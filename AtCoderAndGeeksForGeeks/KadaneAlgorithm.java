package AtCoderAndGeeksForGeeks;

import java.util.Scanner;

/**
 * Given an array arr of N integers. Find the contiguous sub-array with maximum sum.
 *
 * Input:
 * The first line of input contains an integer T denoting the number of test cases. The description of T test cases follows. The first line of each test case contains a single integer N denoting the size of array. The second line contains N space-separated integers A1, A2, ..., AN denoting the elements of the array.
 *
 * Output:
 * Print the maximum sum of the contiguous sub-array in a separate line for each test case.
 *
 * Constraints:
 * 1 ≤ T ≤ 110
 * 1 ≤ N ≤ 106
 * -107 ≤ FindGreatestCommonDivisor[i] <= 107
 *
 * Example:
 * Input
 * 2
 * 5
 * 1 2 3 -2 5
 * 4
 * -1 -2 -3 -4
 * Output
 * 9
 * -1
 */
public class KadaneAlgorithm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        
        for(int test = 0; test < testCases; test++ ){
			int len = scanner.nextInt();
			int[] arr = new int[len];
			for(int count = 0; count < len; count++ ){
				arr[count] = scanner.nextInt();
			}
			
			System.out.println( findSum(arr) );
		}
    }
    
    public static int findSum( int[] arr ){
		int len = arr.length;
		if( len == 0 ) return 0;
		int curr_max = arr[0];
		int max = arr[0];
		
		for( int index = 1; index < len; index++ ){
			curr_max = Math.max( arr[index], curr_max + arr[index] );
			max = Math.max(max, curr_max);
		}
		
		return max;
	}
}
