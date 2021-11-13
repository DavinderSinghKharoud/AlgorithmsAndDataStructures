package Preparation;

import java.util.*;

public class MinimumMovesToMoveBox {

    public static void main(String[] args) {
        System.out.println(new MinimumMovesToMoveBox().minPushBox(
                new char[][]{{'#', '#', '#', '#', '#', '#'},
                        {'#', 'T', '.', '.', '#', '#'},
                        {'#', '.', '#', 'B', '.', '#'},
                        {'#', '.', '.', '.', '.', '#'},
                        {'#', '.', '.', '.', 'S', '#'},
                        {'#', '#', '#', '#', '#', '#'}}
        ));
    }

    int n, m;
    int tarx, tary;
    char[][] grid;
    int[][] direc = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    Map<Node, Set<Node>> map = new HashMap<>();

    public int minPushBox(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        this.grid = grid;
        int srcx = 0, srcy = 0, boxx = 0, boxy = 0;
        //Get all the possible reach from every point
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'T') {
                    tarx = i;
                    tary = j;
                }
                if (grid[i][j] == 'S') {
                    srcx = i;
                    srcy = j;
                }
                if (grid[i][j] == 'B') {
                    boxx = i;
                    boxy = j;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        //Check if src can reach box
        for (int[] dir : direc) {
            int modx = boxx + dir[0], mody = boxy + dir[1];
            if (isValid(modx, mody) && grid[modx][mody] != '#' && canReach(srcx, srcy, modx, mody, boxx, boxy)) {
                ans = Math.min(ans, find(boxx, boxy, modx, mody));
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    int find(int boxx, int boxy, int currx, int curry) {
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(currx, curry, boxx, boxy, 0));
        Set<Position> vis = new HashSet<>();
        while (!queue.isEmpty()) {
            Position curr = queue.poll();
            vis.add(curr);
            if (curr.boxx == tarx && curr.boxy == tary) return curr.steps;

            for (int[] dir : direc) {
                int modx = dir[0] + curr.boxx;
                int mody = dir[1] + curr.boxy;
                if (isValid(modx, mody) && grid[modx][mody] != '#') {
                    if (canReach(curr.perx, curr.pery, modx, mody, curr.boxx, curr.boxy)) {
                        int oppositex = curr.boxx - dir[0];
                        int oppositey = curr.boxy - dir[1];
                        if (isValid(oppositex, oppositey) && grid[oppositex][oppositey] != '#') {
                            Position newPosition = new Position(curr.boxx, curr.boxy, oppositex, oppositey, curr.steps + 1);
                            if (!vis.contains(newPosition))
                                queue.add(newPosition);
                        }
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static class Position {
        int perx, pery, boxx, boxy, steps;

        public Position(int perx, int pery, int boxx, int boxy, int steps) {
            this.perx = perx;
            this.pery = pery;
            this.boxx = boxx;
            this.boxy = boxy;
            this.steps = steps;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return perx == position.perx && pery == position.pery && boxx == position.boxx && boxy == position.boxy;
        }

        @Override
        public int hashCode() {
            return Objects.hash(perx, pery, boxx, boxy);
        }
    }

    boolean canReach(int i, int j, int ii, int jj, int boxx, int boxy) {
        char temp = grid[boxx][boxy];
        grid[boxx][boxy] = '#';

        Set<Node> vis = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(i, j));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            vis.add(curr);
            if (curr.a == ii && curr.b == jj) return true;
            for (int[] dir : direc) {
                int modx = dir[0] + curr.a, mody = dir[1] + curr.b;
                if (isValid(modx, mody) && grid[modx][mody] != '#' && !vis.contains(new Node(modx, mody))) {
                    queue.add(new Node(modx, mody));
                }
            }
        }

        grid[boxx][boxy] = temp;
        return false;
    }


    boolean isValid(int a, int b) {
        return a >= 0 && b >= 0 && a < n && b < m;
    }

    static class Node {
        int a, b;

        public Node(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return a == node.a && b == node.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
}
