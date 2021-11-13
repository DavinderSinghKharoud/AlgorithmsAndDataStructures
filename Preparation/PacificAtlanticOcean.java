package Preparation;

import java.util.*;

/**
 * There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
 *
 * The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).
 *
 * The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.
 *
 * Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * Example 2:
 *
 * Input: heights = [[2,1],[1,2]]
 * Output: [[0,0],[0,1],[1,0],[1,1]]
 */
public class PacificAtlanticOcean {
    int[][] heights;

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        this.heights = heights;
        List<List<Integer>> ans = new ArrayList<>();
        int n = heights.length, m = heights[0].length;
        Node[][] state = new Node[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                state[i][j] = new Node();
            }
        }
        Queue<Node> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            Node curr = new Node(i, 0);
            queue.add(curr);
        }
        for (int col = 0; col < m; col++) {
            Node curr = new Node(0, col);
            queue.add(curr);
        }

        find(queue, true, state);

        //Try for atlantic ocean
        for (int row = 0; row < n; row++) {
            Node curr = new Node(row, m - 1);
            queue.add(curr);
        }
        for (int col = 0; col < m; col++) {
            Node curr = new Node(n - 1, col);
            queue.add(curr);
        }

        find(queue, false, state);

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                Node curr = state[row][col];
                if (curr.isAt && curr.isPc) {
                    List<Integer> lst = new ArrayList<>();
                    lst.add(row);
                    lst.add(col);
                    ans.add(lst);
                }
            }
        }
        return ans;
    }

    int[][] direc = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    void find(Queue<Node> queue, boolean isPacific, Node[][] state) {
        int n = state.length, m = state[0].length;

        boolean[][] isVis = new boolean[n][m];
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            if (isPacific) state[curr.x][curr.y].isPc = true;
            else state[curr.x][curr.y].isAt = true;
            isVis[curr.x][curr.y] = true;

            //System.out.println(curr.x + " " + curr.y);
            for (int[] dir : direc) {
                int modx = dir[0] + curr.x, mody = dir[1] + curr.y;

                if (isValid(modx, mody, n, m) && !isVis[modx][mody] && heights[modx][mody] >= heights[curr.x][curr.y]) {
                    queue.add(new Node(modx, mody));
                }
            }
        }

    }

    boolean isValid(int x, int y, int n, int m) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    static class Node {
        boolean isPc = false, isAt = false;
        int x, y;

        public Node() {
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
