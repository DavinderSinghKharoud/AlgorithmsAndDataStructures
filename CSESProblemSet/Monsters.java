import java.io.*;
import java.util.*;

/**
 * You and some monsters are in a labyrinth. When taking a step to some direction in the labyrinth, each monster may simultaneoulsy take one as well. Your goal is to reach one of the boundary squares without ever sharing a square with a monster.
 *
 * Your task is to find out if your goal is possible, and if it is, print a path that you can follow. Your plan has to work in any situation; even if the monsters know your path beforehand.
 *
 * Input
 *
 * The first input line has two integers n
 * n
 *  and m
 * m
 * : the height and width of the map.
 *
 * After this there are n
 * n
 *  lines of m
 * m
 *  characters describing the map. Each character is . (floor), # (wall), A (start), or M (monster). There is exactly one A in the input.
 *
 * Output
 *
 * First print "YES" if your goal is possible, and "NO" otherwise.
 *
 * If your goal is possible, also print an example of a valid path (the length of the path and its description using characters D, U, L, and R). You can print any path, as long as its length is at most n⋅m
 * n
 * ⋅
 * m
 *  steps.
 *
 * Constraints
 * 1≤n,m≤1000
 * 1
 * ≤
 * n
 * ,
 * m
 * ≤
 * 1000
 *
 * Example
 *
 * Input:
 * 5 8
 * ########
 * #M..A..#
 * #.#.M#.#
 * #M#..#..
 * #.######
 *
 * Output:
 * YES
 * 5
 * RRDDR
 */
public class Monsters {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static int[][] direc = new int[][]{{1, 0, 'D'}, {0, 1, 'R'}, {-1, 0, 'U'}, {0, -1, 'L'}};

    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        char[][] arr = new char[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = fastReader.read().toCharArray();
        }

        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], dmax);
        }

        List<int[]> monsters = new ArrayList<>();
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (arr[row][col] == 'M') {
                    monsters.add(new int[]{row, col});
                }
            }
        }

        bfs(arr, dp, monsters);

        boolean isFound = false;
        Pair des = null;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (arr[row][col] == 'A') {
                    Queue<Pair> queue = new LinkedList<>();
                    queue.add(new Pair(row, col, null, 0, ' '));

                    while (!queue.isEmpty()) {
                        int len = queue.size();
                        while (len-- > 0) {
                            Pair curr = queue.poll();

                            for (int[] dir : direc) {
                                int mRow = curr.row + dir[0];
                                int mCol = curr.col + dir[1];

                                if (mRow < 0 || mCol < 0 || mRow >= n || mCol >= m) {
                                    isFound = true;
                                    des = curr;
                                    break;
                                }

                                if (arr[mRow][mCol] != '#' && dp[mRow][mCol] > curr.distance + 1) {
                                    queue.add(new Pair(mRow, mCol, curr, curr.distance + 1, (char) dir[2]));
                                }
                            }
                            if (isFound) break;
                        }
                        if (isFound) break;
                    }
                    break;
                }

            }
        }

        if (isFound) {
            println("YES");
            println(des.distance);

            List<Character> res = new ArrayList<>();
            while (des.parent != null) {
                res.add(des.c);
                des = des.parent;
            }

            for (int i = res.size() - 1; i >= 0; i--) {
                print(res.get(i));
            }

        } else {
            print("NO");
        }

    }


    static void bfs(char[][] arr, int[][] dp, List<int[]> lst) {

        boolean[][] visited = new boolean[arr.length][arr[0].length];
        Queue<int[]> queue = new LinkedList<>();
        for (int[] c : lst) {
            queue.add(new int[]{c[0], c[1]});
            visited[c[0]][c[1]] = true;
            dp[c[0]][c[1]] = 0;
        }

        int distance = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            while (len-- > 0) {
                int[] curr = queue.poll();

                for (int[] dir : direc) {
                    int mRow = curr[0] + dir[0];
                    int mCol = curr[1] + dir[1];
                    if (isValid(arr, mRow, mCol, visited)) {
                        dp[mRow][mCol] = 1 + distance;
                        visited[mRow][mCol] = true;
                        queue.add(new int[]{mRow, mCol});
                    }
                }

            }
            distance++;
        }
    }

    static boolean isValid(char[][] arr, int row, int col, boolean[][] visited) {
        return row >= 0 && col >= 0 && row < arr.length && col < arr[0].length && arr[row][col] != '#' && !visited[row][col];
    }

    static class Pair {
        int row;
        int col;
        int distance;
        Pair parent;
        char c;

        public Pair(int row, int col, Pair parent, int distance, char c) {
            this.row = row;
            this.col = col;
            this.parent = parent;
            this.distance = distance;
            this.c = c;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {

        solve();
        out.close();
    }

    static class Reader {
        private byte[] buf = new byte[1024];
        private int index;
        private InputStream in;
        private int total;

        public Reader() {
            in = System.in;
        }

        public int scan() throws IOException {
            if (total < 0)
                throw new InputMismatchException();
            if (index >= total) {
                index = 0;
                total = in.read(buf);
                if (total <= 0)
                    return -1;
            }
            return buf[index++];
        }

        public int intNext() throws IOException {
            int integer = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n)) {
                if (n >= '0' && n <= '9') {
                    integer *= 10;
                    integer += n - '0';
                    n = scan();
                } else throw new InputMismatchException();
            }
            return neg * integer;
        }

        public double doubleNext() throws IOException {
            double doub = 0;
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            int neg = 1;
            if (n == '-') {
                neg = -1;
                n = scan();
            }
            while (!isWhiteSpace(n) && n != '.') {
                if (n >= '0' && n <= '9') {
                    doub *= 10;
                    doub += n - '0';
                    n = scan();
                } else throw new InputMismatchException();
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else throw new InputMismatchException();
                }
            }
            return doub * neg;
        }

        public String read() throws IOException {
            StringBuilder sb = new StringBuilder();
            int n = scan();
            while (isWhiteSpace(n))
                n = scan();
            while (!isWhiteSpace(n)) {
                sb.append((char) n);
                n = scan();
            }
            return sb.toString();
        }

        private boolean isWhiteSpace(int n) {
            if (n == ' ' || n == '\n' || n == '\r' || n == '\t' || n == -1)
                return true;
            return false;
        }
    }

    static void shuffle(int[] aa, int n) {
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    static void shuffle(int[][] aa, int n) {
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int first = aa[i][0];
            int second = aa[i][1];
            aa[i][0] = aa[j][0];
            aa[i][1] = aa[j][1];
            aa[j][0] = first;
            aa[j][1] = second;
        }
    }

    /**
     * Tree Multiset utility class *
     */
    static class TMultiset<T extends Number> extends TreeMap<T, Integer> {
        private int size = 0;

        void add(T value) {
            Integer count = get(value);
            size++;
            if (count == null) {
                put(value, 1);
            } else {
                put(value, count + 1);
            }
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public Integer remove(Object key) {
            if (!containsKey(key)) {
                return null;
            }

            size--;

            Integer value = get(key);
            if (value > 1) {
                return put((T) key, value - 1);
            }

            return super.remove(key);
        }

        @java.lang.Override
        public int size() {
            return size;
        }
    }

    /**
     * It is a HashMap
     */
    static class HMap<T> extends HashMap<T, Integer> {
        void add(T key) {
            Integer count = get(key);
            put(key, count == null ? 1 : count + 1);
        }

        @SuppressWarnings(value = "unchecked")
        @Override
        public Integer remove(Object key) {
            if (!containsKey(key)) return null;

            int count = get(key);
            if (count > 1) return put((T) key, count - 1);

            return super.remove(key);
        }
    }

    static final class Comparators {
        public static final Comparator<int[]> pairIntArr =
                (x, y) -> x[0] == y[0] ? compare(x[1], y[1]) : compare(x[0], y[0]);

        private static final int compare(final int x, final int y) {
            return Integer.compare(x, y);
        }
    }


    static void print(Object object) {
        out.print(object);
    }

    static void println(Object object) {
        out.println(object);
    }


    static int min(Object... objects) {
        int min = Integer.MAX_VALUE;

        for (Object num : objects) {
            min = Math.min(min, (Integer) num);
        }
        return min;
    }

    static int max(Object... objects) {
        int max = Integer.MIN_VALUE;

        for (Object num : objects) {
            max = Math.max(max, (Integer) num);
        }
        return max;
    }
}