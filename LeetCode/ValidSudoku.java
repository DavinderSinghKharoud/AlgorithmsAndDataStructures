package LeetCode;

public class ValidSudoku {
    public static boolean isValidSudoku(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] != '.' && !isSafe(board, i, j, rows, cols, board[i][j])) {
                    return false;
                }

            }
        }
        return true;
    }

    private static boolean isSafe(char[][] board, int rowIndex, int colIndex
            , int rows, int cols, char num) {

        //checking rows
        for (int i = 0; i < cols; i++) {
            if (i != colIndex && board[rowIndex][i] == num) {
                return false;
            }
        }

        //Checking cols
        for (int j = 0; j < rows; j++) {
            if (j != rowIndex && board[j][colIndex] == num) {
                return false;
            }
        }

        //Checking Grid
        int grid_size = (int) Math.sqrt(rows);
        int row_start = rowIndex - rowIndex % grid_size;
        int col_start = colIndex - colIndex % grid_size;
        int row_end = row_start + grid_size;
        int col_end = col_start + grid_size;
        for (int i = row_start; i < row_end; i++) {
            for (int j = col_start; j < col_end; j++) {
                if( i != rowIndex && j != colIndex && board[i][j] == num ){
                    return false;
                }
            }
        }
        return true;

    }

    public static void main(String[] args) {

        System.out.println( isValidSudoku2(new char[][]{

                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'}
        }));
    }

    public static boolean isValidSudoku2(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(board[i][j] == '.') continue;
                if(!isSafe2(board,i,j,rows,cols,board[i][j])) return false;
            }
        }
        return true;
    }

    static boolean isSafe2(char[][] board, int row, int col, int rows, int cols, char num) {
        //same row checking
        for (int i = 0; i < cols; i++) {
            if (i!=col && board[row][i] == num) return false;
        }

        //same col checking
        for (int i = 0; i < rows; i++) {
            if (i!=row && board[i][col] == num) return false;
        }

        //same grid checking
        int grid_size = (int) Math.sqrt(rows);
        int row_start = row - row % grid_size;
        int col_start = col - col % grid_size;
        int row_end = row_start + grid_size;
        int col_end = col_start + grid_size;
        for (int i = row_start; i < row_end; i++) {
            for (int j = col_start; j < col_end; j++) {
                if (i!=row && j!=col && board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
