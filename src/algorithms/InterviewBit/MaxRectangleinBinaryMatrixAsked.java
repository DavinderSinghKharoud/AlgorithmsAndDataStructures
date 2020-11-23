package algorithms.InterviewBit;

import java.util.*;

public class MaxRectangleinBinaryMatrixAsked {

    public static int maximalRectangle(ArrayList<ArrayList<Integer>> arr) {

        int n = arr.size();
        if (n == 0) {
            return 0;
        }
        int m = arr.get(0).size();

        int[] series = new int[m];

        int max = 0;

        for (int row = n - 1; row >= 0; row--) {

            for (int col = 0; col < m; col++) {
                int num = arr.get(row).get(col);

                if (num == 1) {
                    series[col] += 1;
                } else {
                    series[col] = 0;
                }

            }

            max = Math.max(max, findRectangle(series));
        }

        return max;
    }


    public static int findRectangle(int[] heights) {


        Stack<Integer> stack = new Stack<>();
        int max = 0;


        for (int index = 0; index <= heights.length; index++) {

            while (!stack.isEmpty() && heights[stack.peek()] > ((index == heights.length) ? -1 : heights[index])) {
                int val = stack.pop();
                int range = (stack.isEmpty()) ? index : index - 1 - stack.peek();
                max = Math.max(max, range * heights[val]);
            }

            stack.push(index);
        }


        return max;
    }

    public static void main(String[] args) {

    }
}
