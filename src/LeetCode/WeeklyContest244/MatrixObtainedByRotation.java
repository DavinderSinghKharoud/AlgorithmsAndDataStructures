package LeetCode.WeeklyContest244;

public class MatrixObtainedByRotation {
   public static void main(String[] args) {

   }

   public boolean findRotation(int[][] mat, int[][] target) {

      for (int i = 0; i < 5; i++) {
         if (isEqual(mat, target))
            return true;
         rotate(mat);
      }
      return false;
   }

   boolean isEqual(int[][] a, int[][] b) {
      for (int i = 0; i < a.length; i++) {
         for (int j = 0; j < b.length; j++) {
            if (a[i][j] != b[i][j])
               return false;
         }
      }
      return true;
   }

   public void rotate(int[][] matrix) {
      int M = matrix.length;
      for (int i = 0; i < M; i++) {
         for (int j = i; j < matrix[i].length; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[j][i];
            matrix[j][i] = temp;
         }
      }

      for (int i = 0; i < M; i++) {

         for (int j = 0; j < matrix[i].length / 2; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[i][M - 1 - j];
            matrix[i][M - 1 - j] = temp;
         }
      }
   }
}
