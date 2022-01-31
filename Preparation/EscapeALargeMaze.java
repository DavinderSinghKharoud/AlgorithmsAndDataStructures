package Preparation;

import java.util.Objects;

/**
 * There is a 1 million by 1 million grid on an XY-plane, and the coordinates of each grid square are (x, y).
 * <p>
 * We start at the source = [sx, sy] square and want to reach the target = [tx, ty] square. There is also an array of blocked squares, where each blocked[i] = [xi, yi] represents a blocked square with coordinates (xi, yi).
 * <p>
 * Each move, we can walk one square north, east, south, or west if the square is not in the array of blocked squares. We are also not allowed to walk outside of the grid.
 * <p>
 * Return true if and only if it is possible to reach the target square from the source square through a sequence of valid moves.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
 * Output: false
 * Explanation: The target square is inaccessible starting from the source square because we cannot move.
 * We cannot move north or east because those squares are blocked.
 * We cannot move south or west because we cannot go outside of the grid.
 * Example 2:
 * <p>
 * Input: blocked = [], source = [0,0], target = [999999,999999]
 * Output: true
 * Explanation: Because there are no blocked cells, it is possible to reach the target square.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= blocked.length <= 200
 * blocked[i].length == 2
 * 0 <= xi, yi < 106
 * source.length == target.length == 2
 * 0 <= sx, sy, tx, ty < 106
 * source != target
 * It is guaranteed that source and target are not blocked.
 */
import java.util.*;

public class EscapeALargeMaze {

    Set<Node> block = new HashSet<>();
    int[][] direc = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    int limit = 200_100;
    int n = 1000_000;

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        //We can only return true if source and target both can
        //go more than 200,100 because blocked.length is only 200
        //Max it can block alongside straight diagonal
        //Which would be far like ((200)(200 + 1))/2

        for (int[] curr : blocked) {
            block.add(new Node(curr[0], curr[1]));
        }

        return dfs(new Node(source[0], source[1]), target, new HashSet<>()) && dfs(new Node(target[0], target[1]), source, new HashSet<>());
    }

    boolean dfs(Node curr, int[] target, Set<Node> vis) {
        vis.add(curr);
        if (curr.x == target[0] && curr.y == target[1]) return true;
        if (curr.distance >= limit) return true;

        for (int[] dir : direc) {
            int modx = dir[0] + curr.x, mody = dir[1] + curr.y;
            Node neighbour = new Node(modx, mody, curr.distance + 1);
            if (isValid(modx, mody) && !vis.contains(neighbour) && !block.contains(neighbour)) {
                if (dfs(neighbour, target, vis)) return true;
            }
        }
        return false;
    }

    boolean isValid(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < n;
    }

    static class Node {
        int x, y, distance;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
