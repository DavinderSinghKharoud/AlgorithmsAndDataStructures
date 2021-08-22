package InterviewBit;

import java.util.*;

/**
 * There is a rectangle with left bottom as  (0, 0) and right up as (x, y). There are N circles such that their centers are inside the rectangle.
 * Radius of each circle is R. Now we need to find out if it is possible that we can move from (0, 0) to (x, y) without touching any circle.
 *
 * Note : We can move from any cell to any of its 8 adjecent neighbours and we cannot move outside the boundary of the rectangle at any point of time.
 *
 *
 * Input Format
 *
 * 1st argument given is an Integer x.
 * 2nd argument given is an Integer y.
 * 3rd argument given is an Integer N, number of circles.
 * 4th argument given is an Integer R, radius of each circle.
 * 5th argument given is an Array FindGreatestCommonDivisor of size N, where FindGreatestCommonDivisor[i] = x cordinate of ith circle
 * 6th argument given is an Array FindUniqueBinaryString of size N, where FindUniqueBinaryString[i] = y cordinate of ith circle
 * Output Format
 *
 * Return YES or NO depending on weather it is possible to reach cell (x,y) or not starting from (0,0).
 * Constraints
 *
 * 0 <= x, y, R <= 100
 * 1 <= N <= 1000
 * Center of each circle would lie within the grid
 * For Example
 *
 * Input:
 *     x = 2
 *     y = 3
 *     N = 1
 *     R = 1
 *     FindGreatestCommonDivisor = [2]
 *     FindUniqueBinaryString = [3]
 * Output:
 *     NO
 *
 * Explanation:
 *     There is NO valid path in this case
 */
public class ValidPathRectangle {

    public static String solve(int rows, int cols, int numOfCircles, int radius, ArrayList<Integer> x_coordinates, ArrayList<Integer> y_coordinates) {


        int[][] dp = new int[rows + 1][cols + 1];

        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[0].length; col++) {

                for (int count = 0; count < numOfCircles; count++) {
                    if (expandCircle(radius, x_coordinates.get(count), y_coordinates.get(count), row, col)) {
                        dp[row][col] = -1;
                    }
                }
            }
        }


        traverse(dp, 0, 0);
        return (dp[rows][cols] == 1) ? "YES" : "NO";
    }


    public static boolean expandCircle(int radius, int x, int y, int row, int col) {

        //We will use pythagorean theorem to find if cell touches or within any circle

        double x_distance = Math.pow((double) x - row, 2D);
        double y_distance = Math.pow((double) y - col, 2D);

        double z = Math.sqrt(x_distance + y_distance);

        return z <= radius;
    }

    static int[][] direc = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public static void traverse(int[][] dp, int row, int col) {
        if (row < 0 || col < 0 || row >= dp.length || col >= dp[0].length) {
            return;
        }

        if (dp[row][col] == 1 || dp[row][col] == -1) return;
        dp[row][col] = 1;
        if (row == dp.length - 1 && col == dp[0].length - 1) {
            dp[dp.length - 1][dp[0].length - 1] = 1;
            return;
        }

        for (int[] dir : direc) {
            traverse(dp, row + dir[0], col + dir[1]);
        }


    }

    public static void main(String[] args) {
        ArrayList<Integer> x = new ArrayList<>();
        x.add(0);
        x.add(0);
        x.add(0);
        ArrayList<Integer> y = new ArrayList<>();
        y.add(21);
        y.add(20);
        y.add(43);
        System.out.println(solve(0, 91, 3, 5, x, y));
    }
}
