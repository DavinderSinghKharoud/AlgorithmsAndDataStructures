package ProjectEuler;

import java.math.BigInteger;
import java.util.Arrays;

public class LatticePaths {
    public static void main(String[] args) {
//
//        int[][] grid = new int[21][21];
//
//        for( int i = 0; i<grid.length; i++){
//            for(int j = 0; j<grid[0].length; j++){
//               grid[i][j] = 0;
//                System.out.print( grid[i][j]+" ");
//            }
//            System.out.println("");
//        }
//
//        grid[grid.length - 1][ grid.length - 1] = 1;
//        for( int i = 0; i<grid.length; i++){
//            for(int j = 0; j<grid[0].length; j++){
//
//                System.out.print( grid[i][j]+" ");
//            }
//            System.out.println("");
//        }
//
//        System.out.println( getLatticePaths( grid ,0 ,0, 0 ) );

        /**
         * After doing a bit of research on this problem, it seems as though a simple
         * Combinations formula will provide the answer. We know that any path will have
         * 40 moves (20 right + 20 down), so for C(n,r) – n will equal 40 and r
         * (the number of right moves) will equal 20 – C(40,20). Then we just plug the numbers
         * into the formula – the ! means ‘factorial’. I posted 2 versions of the solution:
         * 1 without recursion – uses a for loop, and one with recursion – the factorial
         * function calls itself.
         */

        int n = 40;     // The total number of moves for any one path (right + down)
        int r = 20;     // The total number of right moves for any one path

        //C(40,20) = 40!/20!*(40-20)!
        System.out.println(factorial(n).divide(factorial(r).multiply(factorial(n - r))));

    }
    public static BigInteger factorial(int n1 )
    {
        BigInteger n = BigInteger.ONE;
        for (int i = 1; i <= n1; i ++) {
            n = n.multiply(BigInteger.valueOf(i));
        }
        return n;
    }

//    private static int getLatticePaths(int[][] grid,int row, int column, int count ) {
//
//
//        if( grid[row][column] == 1){
//            count+=1;
//            return count;
//        }
//        //Right
//        if( column != grid.length-1 ){
//             count = getLatticePaths(grid, row , column+1, count);
//        }
//        //Left
//        if( row != grid.length - 1){
//            count = getLatticePaths(grid, row+1, column, count );
//        }
//
//        return count;
//    }
}