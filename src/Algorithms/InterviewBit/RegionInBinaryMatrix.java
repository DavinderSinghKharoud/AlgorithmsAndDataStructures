package Algorithms.InterviewBit;

/**
 * Given a binary matrix A of size N x M.
 *
 * Cells which contain 1 are called filled cell and cell that contain 0 are called empty cell.
 *
 * Two cells are said to be connected if they are adjacent to each other horizontally, vertically, or diagonally.
 *
 * If one or more filled cells are also connected, they form a region. Find the length of the largest region.
 *
 * .
 *
 *
 *
 * Problem Constraints
 * 1 <= N, M <= 102
 *
 * A[i][j]=0 or A[i][j]=1
 *
 *
 *
 * Input Format
 * First argument is a 2D binary matrix Aof size  N x M.
 *
 *
 *
 * Output Format
 * Return a single interger denoting the length of largest region.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = [  [0, 0, 1, 1, 0]
 *         [1, 0, 1, 1, 0]
 *         [0, 1, 0, 0, 0]
 *         [0, 0, 0, 0, 1]
 *     ]
 * Input 2:
 *
 *  A = [  [1, 1, 1]
 *         [0, 0, 1]
 *     ]
 *
 *
 * Example Output
 * Output 1:
 *
 *  6
 * Output 2:
 *
 *  4
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The largest region is denoted by red color in the matrix:
 *     00110
 *     10110
 *     01000
 *     00001
 * Explanation 2:
 *
 *  The largest region is:
 *     111
 *     001
 */
public class RegionInBinaryMatrix {

    public static int solve(int[][] arr) {

        int res = -1;

        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {

                if (arr[row][col] == 1) {
                    res = Math.max(res, DFS(arr, row, col));
                }
            }
        }

        return res;
    }

    static int[][] direc = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, -1}, {-1, 1}, {1, 1}, {-1, -1}};

    public static int DFS(int[][] arr, int row, int col) {
        if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || arr[row][col] == 0) {
            return 0;
        }
        arr[row][col] = 0;
        int max = 1;
        for (int[] dir : direc) {
            max += DFS(arr, row + dir[0], col + dir[1]);
        }

        return max;
    }

    public static void main(String[] args) {

        System.out.println(solve(new int[][]{{0, 0, 1, 1, 0}, {1, 0, 1, 1, 0}, {0, 1, 0, 0, 0}, {0, 0, 0, 0, 1}}));
    }
}
