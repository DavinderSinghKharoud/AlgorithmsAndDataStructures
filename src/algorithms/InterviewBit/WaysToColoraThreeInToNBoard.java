/*
 * WaysToColoraThreeInToNBoard.java
 *
 * Copyright 2020 Davinder singh kharoud <davindersinghkharoud@Davinders-MacBook-Pro.local>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 *
 */


import java.sql.PreparedStatement;

public class WaysToColoraThreeInToNBoard {

    static int[][] direc = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static int solve(int a) {

        int[][] arr = new int[3][a];

        arr[0][0] = 4;
        helper(arr, 0, 0, -1, 4);
        return arr[2 - 1][a - 1];
    }

    public static int helper(int[][] arr, int row, int col, int banned, int total) {

        int count = 0;
        for (int num = 0; num < 4; num++) {
            if (num == banned) {
                continue;
            }
            count++;

            for (int[] dir : direc) {
                if (row + dir[0] >= 0 && row + dir[0] < arr.length && col + dir[1] >= 0 && col + dir[1] < arr[0].length && arr[row + dir[0]][col + dir[1]] == 0)
                    count += helper(arr, row + dir[0], col + dir[1], num, total);

            }

        }
        arr[row][col] = total + count;
        return total + count;
    }


    public static void main(String[] args) {
        System.out.println(solve(1));
    }
}

