package HackerRank;

/**
 * Given a  2D Array, :
 *
 * 1 1 1 0 0 0
 * 0 1 0 0 0 0
 * 1 1 1 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * 0 0 0 0 0 0
 * We define an hourglass in  to be a subset of values with indices falling in this pattern in 's graphical representation:
 *
 * a b c
 *   d
 * e f g
 * There are  hourglasses in , and an hourglass sum is the sum of an hourglass' values.
 *Task
 * Calculate the hourglass sum for every hourglass in , then print the maximum hourglass sum.
 */

public class TwoDArray {


    public static void main(String[] args) {

        int count = 0;
        int[][] arr = new int[6][6];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++ ,count++) {
                arr[i][j] = count;
            }
        }

        printArray(arr,6,6);

        System.out.print(calculateHourGlassesSum(arr, arr[0].length,arr.length));

    }

    private static int calculateHourGlassesSum(int[][] myArray,int rowSize, int colSize) {

        int sum= -63;

        for(int row=1; row<rowSize-1; row++){
            for(int col=1; col<colSize-1; col++){
                int temp_sum = calculateSum(myArray, row, col);

                if( temp_sum>sum ){
                    sum = temp_sum;
                }
            }
        }
        return sum;
    }

    private static int calculateSum(int[][] myArray, int row, int col) {
        int sum = 0;
        sum+= myArray[row-1][col-1];
        sum+= myArray[row-1][col];
        sum+= myArray[row-1][col+1];
        sum+= myArray[row][col];
        sum+= myArray[row+1][col-1];
        sum+= myArray[row+1][col];
        sum+= myArray[row+1][col+1];

        return sum;
    }

    private static void printArray(int[][] myArray,int rowSize, int colSize){
        for(int i = 0; i < rowSize; i++) {

            // print the row of space-separated values
            for(int j = 0; j < colSize; j++) {
                System.out.print(myArray[i][j] + " ");
            }
            // end of row is reached, print newline
            System.out.println();
        }
    }
}
