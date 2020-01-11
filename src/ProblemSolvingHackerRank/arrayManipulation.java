package ProblemSolvingHackerRank;

import java.util.Scanner;

/**
 * Starting with a 1-indexed array of zeros and a list of operations,
 * for each operation add a value to each of the array element between two given indices,
 * inclusive. Once all operations have been performed, return the maximum value in your array.
 */
public class arrayManipulation {


    // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {

        long[] arr = new long[n+2];

        for(int i = 0; i<queries.length; i++){

            int starting = queries[i][0];
            int ending = queries[i][1];
            int numAdd = queries[i][2];

            arr[starting] += numAdd;
            arr[ending+1] -= numAdd;


        }

        long maxValue = getMax(arr);
        return maxValue;

    }

    private static long getMax(long[] arr) {
        long max = Integer.MIN_VALUE;
        long sum = 0;

        for(int i = 0; i<arr.length; i++){

            sum+=arr[i];
            max = Math.max(max,sum);
        }
        return max;
    }



    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);
        System.out.println(result);
    }
}
