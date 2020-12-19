package Algorithms.LeetCode;

import java.util.*;

/**
 * In a given 2D binary array A, there are two islands.  (An island is a 4-directionally connected group of 1s not connected to any other 1s.)
 *
 * Now, we may change 0s to 1s so as to connect the two islands together to form 1 island.
 *
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed that the answer is at least 1.)
 *
 *
 *
 * Example 1:
 *
 * Input: A = [[0,1],[1,0]]
 * Output: 1
 * Example 2:
 *
 * Input: A = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * Example 3:
 *
 * Input: A = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * 2 <= A.length == A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1
 */
public class ShortestBridge {

    //Time complexity and Space complexity O(n)
    static int shortestBridge(int[][] arr) {

        Deque<int[]> queue = new ArrayDeque<>();

        boolean found = false;
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[0].length; col++) {
                if (arr[row][col] == 1) {
                    submerge(arr, row, col, queue);
                    found = true;
                    break;
                }
            }
            if (found) break;
        }


        //So one island is already in the queue

        int[][] direc = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int res = 0;

        while (!queue.isEmpty()) {
            int len = queue.size();

            for (int size = len; size > 0; size--) {
                int[] curr = queue.pop();

                for (int[] d : direc) {

                    int row = curr[0] + d[0];
                    int col = curr[1] + d[1];

                    if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || arr[row][col] == -1) {
                        continue;
                    }

                    if (arr[row][col] == 1) return res;
                    arr[row][col] = -1;
                    queue.add(new int[]{row, col});


                }

            }
            res++;


        }


        return res;
    }


    static void submerge(int[][] arr, int row, int col, Deque<int[]> queue) {
        if (row < 0 || col < 0 || row >= arr.length || col >= arr[0].length || arr[row][col] != 1) {
            return;
        }

        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        queue.add(new int[]{row, col});
        arr[row][col] = -1;
        for (int[] dir : dirs) {
            submerge(arr, row + dir[0], col + dir[1], queue);
        }
    }

    public static void main(String[] args) {

        System.out.println(shortestBridge(new int[][]{{1, 1, 1, 1, 1}, {1, 0, 0, 0, 1}, {1, 0, 1, 0, 1}, {1, 0, 0, 0, 1}, {1, 1, 1, 1, 1}}));

        }
    }
