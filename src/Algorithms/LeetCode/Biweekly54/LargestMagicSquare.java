package Algorithms.LeetCode.Biweekly54;

public class LargestMagicSquare {
    public static void main(String[] args) {
        LargestMagicSquare o = new LargestMagicSquare();
        System.out.println(o.largestMagicSquare(new int[][]{
                {1,9,3,5,5,8,1,6,9}, {4,1,1,6,8,3,5,7,6}, {9,8,4,7,2,4,9,2,7}, {1,9,8,10,5,10,1,6,3}
        }));
       // [[8,1,6],[8,1,6],[4,9,2],[7,10,9]]
    }

    int n, m;

    public int largestMagicSquare(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int res = 1;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                // We check every single possible square
                int adjR = row + 1, adjC = col + 1;
                while (isValid(adjR, adjC)) {
                    if (isPossible(grid, row, col, adjR, adjC)) {
                        res = Math.max(res, adjR - row + 1);
                    }
                    adjR++;
                    adjC++;
                }
            }
        }
        return res;
    }

    boolean isPossible(int[][] grid, int r, int c, int adjr, int adjc) {
        long target = 0;
        // Sum of first row
        for (int i = c; i <= adjc; i++) {
            target += grid[r][i];
        }

        // Check all other rows
        for (int row = r + 1; row <= adjr; row++) {
            long sum = 0;
            for (int col = c; col <= adjc; col++)
                sum += grid[row][col];
            if (sum != target)
                return false;
        }

        // Check all cols
        for (int col = c; col <= adjc; col++) {
            long sum = 0;
            for (int row = r; row <= adjr; row++) {
                sum += grid[row][col];
            }
            if (sum != target)
                return false;
        }

        // Check diagonal from left
        long sum = 0;
        int diar = r, diac = c;
        while (diar != adjr && diac != adjc) {
            sum += grid[diar][diac];
            diar++;
            diac++;
        }
        sum += grid[adjr][adjc];
        if (sum != target)
            return false;

        // Check diagonal from right
        sum = 0;
        diar = r;
        diac = adjc;
        while (diar != adjr && diac != c) {
            sum += grid[diar][diac];
            diar++;
            diac--;
        }
        sum += grid[adjr][c];
        if (sum != target)
            return false;
        return true;
    }

    boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < n && c < m;
    }
}
