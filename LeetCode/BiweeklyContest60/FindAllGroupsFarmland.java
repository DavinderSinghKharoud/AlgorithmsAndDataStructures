package LeetCode.BiweeklyContest60;

import java.util.*;

public class FindAllGroupsFarmland {
   public static void main(String[] args) {
      FindAllGroupsFarmland o = new FindAllGroupsFarmland();
   }

   int n, m;
   int[][] arr;

   public int[][] findFarmland(int[][] land) {
      n = land.length;
      m = land[0].length;
      arr = land;
      List<int[]> res = new ArrayList<>();
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < m; j++) {
            if (arr[i][j] == 1) {
               int[] right = findRight(i, j);
               submerge(i, j);
               res.add(new int[] { i, j, right[0], right[1] });
            }
         }
      }

      int[][] ans = new int[res.size()][4];
      for(int i = 0; i < res.size(); i++) ans[i] = res.get(i);
      return ans;
   }

   int[] findRight(int i, int j) {
      int x = i, y = j;

      int row = i, col = j;
      // Move left
      while (isValid(row, col + 1) && arr[row][col + 1] == 1) {
         col++;
      }
      // Move right
      while (isValid(row + 1, col) && arr[row + 1][col] == 1) {
         row++;
      }
      return new int[] { row, col };
   }

   int[][] direc = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

   void submerge(int i, int j) {
      if (arr[i][j] == 0)
         return;
      arr[i][j] = 0;

      for (int[] dir : direc) {
         int modx = dir[0] + i, mody = dir[1] + j;
         if (isValid(modx, mody))
            submerge(modx, mody);
      }
   }

   boolean isValid(int i, int j) {
      return i >= 0 && j >= 0 && i < n && j < m;
   }
}
