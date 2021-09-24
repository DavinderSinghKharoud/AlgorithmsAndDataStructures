package LeetCode;

import java.util.*;

public class DetectSquares {

    public static void main(String[] args) {
        DetectSquares detectSquares = new DetectSquares();
        detectSquares.add(new int[]{3, 10});
        detectSquares.add(new int[]{11, 2});
        detectSquares.add(new int[]{3, 2});
        System.out.println(detectSquares.count(new int[]{11, 10})); // return 1. You can choose:
        // - The first, second, and third points
        System.out.println(detectSquares.count(new int[]{14, 8})); // return 0. The query point cannot form a square
        // with any points in the data structure.
        detectSquares.add(new int[]{11, 2}); // Adding duplicate points is allowed.
        System.out.println(detectSquares.count(new int[]{11, 10}));
    }

    Map<Coordinate, Integer> map = new HashMap<>();

    public void add(int[] point) {
        Coordinate coordinate = new Coordinate(point[0], point[1]);
        map.put(coordinate, map.getOrDefault(coordinate, 0) + 1);
    }

    public int count(int[] point) {
        // Check the diagonals
        int ans = 0;
        int x = point[0], y = point[1];
        while (x - 1 >= 0 && y - 1 >= 0) {
            x--;
            y--;

            if (map.containsKey(new Coordinate(x, y))) {
                int brx = min(point[0], x), bry = max(point[1], y);
                if (map.containsKey(new Coordinate(brx, bry))) {
                    int ulx = max(point[0], x), uly = min(point[1], y);
                    if (map.containsKey(new Coordinate(ulx, uly))) {

                        int count1 = get(x, y), count2 = get(brx, bry), count3 = get(ulx, uly);

                        ans += (count1 * count2 * count3);
                    }
                }
            }
        }

        // Move up
        x = point[0];
        y = point[1];
        while (x + 1 <= 1000 && y + 1 <= 1000) {
            x++;
            y++;

            if (map.containsKey(new Coordinate(x, y))) {
                int brx = min(point[0], x), bry = max(point[1], y);
                if (map.containsKey(new Coordinate(brx, bry))) {
                    int ulx = max(point[0], x), uly = min(point[1], y);
                    if (map.containsKey(new Coordinate(ulx, uly))) {

                        int count1 = get(x, y), count2 = get(brx, bry), count3 = get(ulx, uly);

                        ans += (count1 * count2 * count3);
                    }
                }
            }
        }
        // Move Down right
        x = point[0];
        y = point[1];
        while (x - 1 >= 0 && y + 1 <= 1000) {
            x--;
            y++;

            if (map.containsKey(new Coordinate(x, y))) {
                int blx = min(point[0], x), bly = min(point[1], y);
                if (map.containsKey(new Coordinate(blx, bly))) {
                    int urx = max(point[0], x), ury = max(point[1], y);
                    if (map.containsKey(new Coordinate(urx, ury))) {

                        int count1 = get(x, y), count2 = get(blx, bly), count3 = get(urx, ury);

                        ans += (count1 * count2 * count3);
                    }
                }
            }
        }

        // Move Up Left
        x = point[0];
        y = point[1];
        while (x + 1 <= 1000 && y - 1 >= 0) {
            x++;
            y--;

            if (map.containsKey(new Coordinate(x, y))) {
                int blx = min(point[0], x), bly = min(point[1], y);
                if (map.containsKey(new Coordinate(blx, bly))) {
                    int urx = max(point[0], x), ury = max(point[1], y);
                    if (map.containsKey(new Coordinate(urx, ury))) {

                        int count1 = get(x, y), count2 = get(blx, bly), count3 = get(urx, ury);

                        ans += (count1 * count2 * count3);
                    }
                }
            }
        }

        return ans;
    }

    int get(int x, int y) {
        return map.getOrDefault(new Coordinate(x, y), 0);
    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    int min(int a, int b) {
        return Math.min(a, b);
    }

    int max(int a, int b) {
        return Math.max(a, b);
    }
}
