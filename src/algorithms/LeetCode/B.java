package algorithms.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class B {

    static int shortPath = Integer.MAX_VALUE;
    static int removeObstacle(int numRows, int numColumns, List<List<Integer>> lot)
    {
        //Setting up directions, in which robot can move
        int[][] dir = new int[][]{ {-1, 0},{0, 1}, {0, -1},{1, 0}};

        Boolean [][]visited = new Boolean[numRows][numColumns];

        for( int i = 0; i<numRows; i++ ){
            for( int j = 0; j<numColumns; j++ ){
                visited[i][j] = false;
            }
        }
        //calling recursive function for finding the shortest path
         move( lot, numRows, numColumns, dir, 0 , 0, visited);
        return shortPath;
    }

    private static int move(List<List<Integer>> lot, int numRows, int numColumns, int[][] dir, int row,
                            int col, Boolean[][] visited) {
        if( lot.get(row).get(col) == 0 ){
            return 0;
        }
        if( lot.get(row).get(col) == 9 ){
            return 1;
        }

        int count = 0;
        visited[row][col] = true;

        for( int i = 0; i<dir.length; i++ ){

            if( valid( row + dir[i][0], col + dir[i][1],numRows, numColumns) && !visited[row + dir[i][0]][col + dir[i][1]]  ){
            int temp = move( lot, numRows, numColumns, dir, row + dir[i][0], col + dir[i][1], visited);
                if( temp >= 1){
                    count++;
                }
            }
        }
        if( shortPath > count ){ shortPath = count;}
        return count;
    }

    private static boolean valid(int row, int col, int numRows, int numColumns) {
        if( row < 0 || row >= numRows || col < 0 || col >= numColumns  ){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {

        List<Integer> lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(0);
        lst1.add(0);
        List<Integer> lst2 = new ArrayList<>();
        lst2.add(1);
        lst2.add(0);
        lst2.add(0);
        List<Integer> lst3 = new ArrayList<>();
        lst3.add(1);
        lst3.add(9);
        lst3.add(1);
        List<List<Integer>> lst = new ArrayList<>();
        lst.add(lst1);
        lst.add(lst2);
        lst.add(lst3);

        System.out.println( removeObstacle(3,3,lst));

    }
}
