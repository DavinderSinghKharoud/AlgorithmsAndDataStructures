package algorthims.LeetCode;

public class GameOfLife {

    public static void gameOfLife1(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        int[][] copy = new int[row][col];
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                copy[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (board[i][j] == 0) {
                    int lives = countLives(copy, dir, i, j);
                    if (lives == 3) {
                        board[i][j] = 1;
                    }
                }

                if (board[i][j] == 1) {
                    int lives = countLives(copy, dir, i, j);

                    if (lives < 2 || lives > 3) {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }

    private static int countLives(int[][] matrix, int[][] dirs, int i, int j) {
        int lives = 0;

        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];

            if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length && matrix[row][col] == 1) {
                lives += 1;
            }
        }
        return lives;
    }

    private static int countLives2(int[][] matrix, int[][] dirs, int i, int j) {
        int lives = 0;

        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];

            if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length &&
                    (matrix[row][col] == 1 || matrix[row][col] == 2)) {
                lives += 1;
            }
        }
        return lives;
    }


    public static void gameOfLife2(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (board[i][j] == 0) {
                    int lives = countLives2(board, dir, i, j);
                    if (lives == 3) {
                        board[i][j] = -1;
                    }
                }

                if (board[i][j] == 1) {
                    int lives = countLives2(board, dir, i, j);

                    if (lives < 2 || lives > 3) {
                        board[i][j] = 2;
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if( board[i][j] == -1){
                    board[i][j] = 1;
                }
                if( board[i][j] == 2){
                    board[i][j] = 0;
                }
            }
        }
    }


    public static void main(String[] args) {

        int[][] matrix = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        gameOfLife2(matrix);

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }
}
