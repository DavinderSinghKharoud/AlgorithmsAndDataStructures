package LeetCode.BiweeklyContest59;

public class MaxMatrixSum {
    public static void main(String[] args) {
        MaxMatrixSum o = new MaxMatrixSum();
        System.out.println(o.maxMatrixSum(new int[][]{
                {1, 2, 3}, {-1, -2, -3}, {1, 2, 3}
        }));
    }

    public long maxMatrixSum(int[][] matrix) {
        long sum = 0;
        int count = 0;
        int min = Math.abs(matrix[0][0]);
        boolean isZero = false;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                int curr = matrix[i][j];
                if (curr == 0) isZero = true;
                sum += Math.abs(curr);
                min = Math.min(min, Math.abs(curr));
                if (curr < 0) {
                    count++;
                }
            }
        }
        if (count % 2 == 0 || isZero)
            return sum;
        return sum - 2L * min;
    }
}
