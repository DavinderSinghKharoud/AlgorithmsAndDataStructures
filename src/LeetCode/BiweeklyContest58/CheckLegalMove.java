package LeetCode.BiweeklyContest58;

public class CheckLegalMove {
   public static void main(String[] args) {
      CheckLegalMove o = new CheckLegalMove();
   }

   int rlen, colLen;
   int[][] direc = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, -1 }, { -1, 1 } };

   public boolean checkMove(char[][] board, int rMove, int cMove, char color) {
      rlen = board.length;
      colLen = board[0].length;

      for (int[] dir : direc) {
         boolean is = isPoss(board, rMove, cMove, color, dir[0], dir[1]);
         if (is)
            return true;
      }
      return false;
   }

   boolean isPoss(char[][] board, int rMove, int cMove, char color, int x, int y) {
      // Find the same color on the path
      int count = 0;
      boolean isEndPoint = false;
      rMove += x;
      cMove += y;
      while (isValid(rMove, cMove)) {
         if (board[rMove][cMove] == color) {
            isEndPoint = true;
            break;
         } else if (board[rMove][cMove] == '.') {
            return false;
         } else
            count++;
         rMove += x;
         cMove += y;
      }
      return count >= 1 && isEndPoint;
   }

   boolean isValid(int x, int y) {
      return x >= 0 && y >= 0 && x < rlen && y < colLen;
   }
}
