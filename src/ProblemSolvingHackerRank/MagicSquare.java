package ProblemSolvingHackerRank;

import java.util.Scanner;

public class MagicSquare {

    static int formingMagicSquare(int[][] s) {

        int [][]maxSquares = new int[][]{
                {8,3,4,1,5,9,6,7,2},
                {4,3,8,9,5,1,2,7,6},
                {8,1,6,3,5,7,4,9,2},
                {6,1,8,7,5,3,2,9,4},
                {2,9,4,7,5,3,6,1,8},
                {4,9,2,3,5,7,8,1,6},
                {2,7,6,9,5,1,4,3,8},
                {6,7,2,1,5,9,8,3,4}

        };

        int minValue = Integer.MAX_VALUE;

        for(int i = 0; i<maxSquares.length; i++){
            int totalCount = 0;
            for(int j = 0;j<maxSquares[i].length; j++){

                totalCount += Math.abs(s[j/3][j%3] - maxSquares[i][j]);
            }
            if( totalCount<minValue){
                minValue = totalCount;
            }


        }
        return minValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            System.out.print("Enter three integers for one row seperated by space: ");
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        System.out.print(result);
    }
}
