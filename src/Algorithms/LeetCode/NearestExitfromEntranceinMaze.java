package Algorithms.LeetCode;

import java.util.*;

public class NearestExitfromEntranceinMaze {
    public static void main(String[] args) {
        NearestExitfromEntranceinMaze o = new NearestExitfromEntranceinMaze();
        System.out.println(o.nearestExit(new char[][]{{'+', '+', '+'}, {'.', '.', '.'},
                        {'+', '+', '+'}},
                new int[]{1, 0}));
    }

    int[][] direc = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public int nearestExit(char[][] maze, int[] entrance) {
        Set<Integer> vis = new HashSet<>();
        vis.add(entrance[0] * 200 + entrance[1]);
        // We can use bfs
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(entrance[0], entrance[1], 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (int[] dir : direc) {
                int modx = curr.x + dir[0], mody = curr.y + dir[1];
                if (!isValid(modx, mody, maze.length, maze[0].length)) {
                    if(curr.x != entrance[0] || curr.y != entrance[1]) return curr.dis;
                } else {
                    if (maze[modx][mody] == '.' && !vis.contains(modx * 200 + mody)) {
                        queue.add(new Node(modx, mody, curr.dis + 1));
                        vis.add(modx * 200 + mody);
                    }
                }
            }
        }
        return -1;
    }

    boolean isValid(int a, int b, int n, int m) {
        return a >= 0 && b >= 0 && a < n && b < m;
    }

    class Node {
        int x;
        int y;
        int dis = 0;

        public Node(int xx, int yy, int dis) {
            x = xx;
            y = yy;
            this.dis = dis;
        }
    }
}
