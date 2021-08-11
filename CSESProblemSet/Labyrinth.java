
import java.io.*;
import java.util.*;

/**
 * You are given a map of a labyrinth, and your task is to find a path from start to end. You can walk left, right, up and down.
 * <p>
 * Input
 * <p>
 * The first input line has two integers n
 * n
 * and m
 * m
 * : the height and width of the map.
 * <p>
 * Then there are n
 * n
 * lines of m
 * m
 * characters describing the labyrinth. Each character is . (floor), # (wall), A (start), or B (end). There is exactly one A and one B in the input.
 * <p>
 * Output
 * <p>
 * First print "YES", if there is a path, and "NO" otherwise.
 * <p>
 * If there is a path, print the length of the shortest such path and its description as a string consisting of characters L (left), R (right), U (up), and D (down). You can print any valid solution.
 * <p>
 * Constraints
 * 1≤n,m≤1000
 * 1
 * ≤
 * n
 * ,
 * m
 * ≤
 * 1000
 * <p>
 * Example
 * <p>
 * Input:
 * 5 8
 * ########
 * #.A#...#
 * #.##.#B#
 * #......#
 * ########
 * <p>
 * Output:
 * YES
 * 9
 * LDDRRRRRU
 */
public class Labyrinth {
    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;

    static void solve() throws IOException {

        int n = fastReader.intNext();
        int m = fastReader.intNext();

        char[][] arr = new char[n][m];

        for (int i = 0; i < n; i++) {
            arr[i] = fastReader.read().toCharArray();
        }

        boolean isFound = false;
        Pair des = null;
        int[][] direc = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (arr[i][j] == 'A') {

                    Pair a = new Pair(i, j);
                    //Run BFS
                    Queue<Pair> queue = new LinkedList<>();
                    queue.add(new Pair(i, j));

                    while (!queue.isEmpty()) {

                        int len = queue.size();

                        while (len-- > 0) {
                            Pair curr = queue.poll();

                            if (arr[curr.row][curr.col] == 'B') {
                                des = curr;
                                isFound = true;
                                break;
                            }

                            for (int[] dir : direc) {
                                int row = dir[0] + curr.row, col = dir[1] + curr.col;

                                if (isValid(row, col, arr)) {
                                    if (arr[row][col] != 'B') {
                                        arr[row][col] = '#';
                                    }

                                    queue.add(new Pair(row, col, curr, curr.distance + 1));
                                }
                            }

                            if (isFound) break;

                        }

                        if (isFound) break;
                    }
                    break;
                }

            }
            if (isFound) break;
        }

        if (isFound) {
            println("YES");
            println(des.distance);
            List<Character> lst = new ArrayList<>();
            while (des.parent != null) {
                Pair parent = des.parent;
                lst.add(find(des.row, des.col, parent.row, parent.col));
                des = parent;
            }

            for (int i = lst.size() - 1; i >= 0; i--) {
                print(lst.get(i));
            }
        } else {
            print("NO");
        }
    }

    static char find(int row, int col, int pRow, int pCol) {
        if (row == pRow) {
            if (col == pCol - 1) {
                return 'L';
            } else {
                return 'R';
            }
        } else {
            if (row == pRow - 1) {
                return 'U';
            } else {
                return 'D';
            }
        }
    }

    static boolean isValid(int row, int col, char[][] arr) {
        return row >= 0 && row < arr.length && col >= 0 && col < arr[0].length && arr[row][col] != '#' && arr[row][col] != 'A';
    }

    static class Pair {
        int row, col, distance = 0;
        Pair parent;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Pair(int row, int col, Pair parent, int dis) {
            this.row = row;
            this.col = col;
            this.parent = parent;
            distance = dis;
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


