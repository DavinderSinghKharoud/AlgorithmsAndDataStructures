package Algorithms.LeetCode;

import java.util.*;

public class CyclicallyRotatingAGrid {

   public static void main(String[] args) {
//      CyclicallyRotatingAGrid o = new CyclicallyRotatingAGrid();
//      System.out.println(Arrays.deepToString(o.rotateGrid(new int[][] { { 1, 2 }, { 3, 4 } }, 1)));
   }
//
//   int[][] ans;
//
//   public int[][] rotateGrid(int[][] grid, int k) {
//      int n = grid.length, m = grid[0].length;
//      ans = new int[n][m];
//
//      for (int i = 0; i < n; i++) {
//         for (int j = 0; j < m; j++) {
//            rotate(grid, i, j, k);
//         }
//      }
//      return ans;
//   }
//
//   void rotate(int[][] grid int i, int j, int k) {
//
//        int rows = (mMax - mMin) * 2;
//        rows += rows * (nMax - nMin - 2);
//        int cols = (nMax - nMin - 2) * 2;
//        cols += cols * (mMax - mMin - 2);
//        int total = rows + cols;
//
//        int move = k % total;
//        if (move == 0)
//            return;
//
//        move(grid[i][j], i, j, move, nMin, nMax, mMin, mMax);
//    }
//
//   void move(int curr, int i, int j, int move, int nMin, int nMax, int mMin, int mMax) {
//      while (move > 0) {
//         if (i == 0) {
//            // Move left
//            if (j == 0) {
//               i += 1;
//            } else {
//               while (j > 0 && move > 0) {
//                  move--;
//                  j--;
//               }
//            }
//         } else if (j == 0) {
//            // Move down
//            if (i == nMax - 1) {
//               j += 1;
//            } else {
//               while (i < nMax - 1 && move > 0) {
//                  i++;
//                  move--;
//               }
//            }
//         } else if (i == nMax - 1) {
//            // Move right
//            if (j == nMax - 1) {
//               i -= 1;
//            } else {
//               while (j < mMax - 1 && move > 0) {
//                  move--;
//                  j++;
//               }
//            }
//         } else {
//            // Move up
//            if (i == 0) {
//               j -= 1;
//            } else {
//               while (i > 0 && move > 0) {
//                  i--;
//                  move--;
//               }
//            }
//         }
//      }
//
//      ans[i][j] = curr;
//   }
}
