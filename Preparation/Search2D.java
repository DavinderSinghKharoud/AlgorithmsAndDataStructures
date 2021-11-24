package Preparation;

public class Search2D {
    public static void main(String[] args) {
        System.out.println(new Search2D().searchMatrix2(
                new int[][]{
                        {1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}
                }, 3
        ));
    }

    int n, m;
    int target;

    /**
     * Log(n)
     */
    public boolean searchMatrix3(int[][] matrix, int target) {
        n = matrix.length;
        m = matrix[0].length;
        int row = 0, col = m - 1;
        while (isValid(row, col)) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else return true;
        }
        return false;
    }


    /**
     * Use binary search.
     * <p>
     * n * m matrix convert to an array => matrix[x][y] => a[x * m + y]
     * <p>
     * an array convert to n * m matrix => a[x] =>matrix[x / m][x % m];
     * Increae in m will increase both x & y
     */

    public boolean searchMatrix2(int[][] matrix, int target) {
        n = matrix.length;
        m = matrix[0].length;
        int start = 0, end = n * m - 1;

        while (start < end) {
            int mid = (end + start + 1) / 2;
            System.out.println(mid);
            int row = mid / m, col = mid % n;
            System.out.println(row + " " + col);
            if (matrix[row][col] <= target) {
                start = mid;
            } else end = mid - 1;
        }
        return matrix[end / m][end % n] == target;
    }


    /**
     * Log(m*n)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        n = matrix.length;
        m = matrix[0].length;
        this.target = target;
        boolean isColSearch = true;
        int row = 0, col = 0;
        int prevRow = -1, prevCol = -1;
        while (true) {
            if (isColSearch) {
                int rowFound = findInCol(matrix, row, col);
                if (rowFound == -1) return false;
                row = rowFound;
            } else {
                int colFound = findInRow(matrix, row, col);
                if (colFound == -1) return false;
                col = colFound;
            }
            if (isValid(row, col) && matrix[row][col] == target) return true;
            if (prevRow == row && prevCol == col) return false;
            prevRow = row;
            prevCol = col;
            isColSearch = !isColSearch;
        }
    }

    int findInRow(int[][] matrix, int row, int col) {
        int start = col, end = m - 1;
        while (start < end) {
            int mid = (end + start + 1) / 2;
            if (matrix[row][mid] <= target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }

        return (matrix[row][start] <= target) ? start : -1;
    }

    int findInCol(int[][] matrix, int row, int col) {
        int start = row, end = n - 1;
        while (start < end) {
            int mid = (end + start + 1) / 2;
            if (matrix[mid][col] <= target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        System.out.println(start + " " + col);
        return (matrix[start][col] <= target) ? start : -1;
    }

    boolean isValid(int row, int col) {
        return row >= 0 && col >= 0 && row < n && col < m;
    }

}
