package Algorithms.InterviewBit;

import java.util.*;

public class CaptureRegionsOnBoard {

    public static void solve(List<List<Character>> lst) {

        int len1 = lst.size();
        int len2 = lst.get(0).size();
        //going right
        for (int col = 0; col < len2; col++) {
            if ( lst.get(0).get(col) == 'O' ){
                traverse( lst, 0, col );
            }
        }

        //going bottom
        for (int row = 0; row < len1; row++) {
            if ( lst.get(row).get(len2 - 1) == 'O' ){
                traverse( lst, row, len2 - 1 );
            }
        }

        //backward bottom
        for (int col = len2 - 1; col >= 0; col--) {
            if ( lst.get(len1 - 1).get(col) == 'O' ){
                traverse( lst, len1 - 1, col );
            }
        }

        for (int row = len1 - 1; row >= 0; row--) {
            if ( lst.get(row).get(0) == 'O' ){
                traverse( lst, row, 0 );
            }
        }


        for (int row = 0; row < len1; row++) {
            for (int col = 0; col < len2; col++) {
                if( lst.get(row).get(col) == 'O' ){
                    lst.get(row).set(col, 'X');
                }
                if(lst.get(row).get(col) == '1'){
                    lst.get(row).set(col,'O');
                }
            }
        }

    }

    private static void traverse(List<List<Character>> lst, int row, int col) {
        lst.get(row).set(col, '1');
        int[][] direc = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for(int[] dir: direc){
            int modifiedRow = row + dir[0];
            int modifiedCol = col + dir[1];

            if ( modifiedRow >= 0 && modifiedRow < lst.size() && modifiedCol >= 0 && modifiedCol < lst.get(0).size() ) {
                if( lst.get(modifiedRow).get(modifiedCol) == 'O' ){
                    traverse(lst, modifiedRow, modifiedCol);
                }
            }
        }
    }

    public static boolean submerge(List<List<Character>> lst, int row, int col, boolean[][] dp) {
        int[][] direc = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean isSubmerge = true;
        dp[row][col] = true;

        for (int[] dir : direc) {
            int modifiedRow = row + dir[0];
            int modifiedCol = col + dir[1];
            if ((modifiedRow < 0 || modifiedRow >= lst.size()) || (modifiedCol < 0 || modifiedCol >= lst.get(0).size())) {
                isSubmerge = false;
                break;
            } else {
                if (dp[modifiedRow][modifiedCol] && lst.get(row + dir[0]).get(col + dir[1]) == 'O') {
                    isSubmerge = false;
                    continue;
                }
                if (!dp[modifiedRow][modifiedCol] && lst.get(row + dir[0]).get(col + dir[1]) == 'O') {
                    if (!submerge(lst, row + dir[0], col + dir[1], dp)) {
                        isSubmerge = false;
                    }
                }
            }
        }
        if (isSubmerge) {
            lst.get(row).set(col, 'X');
        }

        return isSubmerge;
    }

    public static void main(String[] args) {

        List<List<Character>> lst = new ArrayList<>();

        lst.add(Arrays.asList('X', 'X', 'X'));
        lst.add(Arrays.asList('X', 'O', 'X'));
        lst.add(Arrays.asList('X', 'X', 'X'));
        lst.add(Arrays.asList('X', 'O', 'X'));

        solve(lst);

        for (int row = 0; row < lst.size(); row++) {
            for (int col = 0; col < lst.get(0).size(); col++) {

                System.out.print(lst.get(row).get(col));
            }
            System.out.println();
        }
    }
}
