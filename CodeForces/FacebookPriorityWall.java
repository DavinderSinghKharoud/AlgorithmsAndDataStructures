package CodeForces;

import java.io.*;
import java.nio.channels.InterruptedByTimeoutException;
import java.util.*;

/**
 *
 Facetook is a well known social network website, and it will launch a new feature called Facetook Priority Wall. This feature will sort all posts from your friends according to the priority factor (it will be described).

 This priority factor will be affected by three types of actions:

 1. "X posted on Y's wall" (15 points),
 2. "X commented on Y's post" (10 points),
 3. "X likes Y's post" (5 points).
 X and Y will be two distinct names. And each action will increase the priority factor between X and Y (and vice versa) by the above value of points (the priority factor between X and Y is the same as the priority factor between Y and X).

 You will be given n actions with the above format (without the action number and the number of points), and you have to print all the distinct names in these actions sorted according to the priority factor with you.
 */
public class FacebookPriorityWall implements Runnable {
    Map<String, Integer> map = new HashMap<>();

    void solve() throws IOException {
        String me = rs();
        int n = ri();

        int posted = 15, commented = 10, likes = 5;

        while (n-- > 0) {
            String s = getString();
            String[] split = s.split(" ");
            if (split[1].equals("posted")) {

                String a = split[0], b = getB(split[3]);
                if (a.equals(me)) {
                    map.put(b, map.getOrDefault(b, 0) + posted);
                } else if (b.equals(me)) {
                    map.put(a, map.getOrDefault(a, 0) + posted);
                } else {
                    addDefault(a, b);
                }
            } else if (split[1].equals("commented")) {

                String a = split[0], b = getB(split[3]);
                if (a.equals(me)) {
                    map.put(b, map.getOrDefault(b, 0) + commented);
                } else if (b.equals(me)) {
                    map.put(a, map.getOrDefault(a, 0) + commented);
                } else {
                    addDefault(a, b);
                }
            } else {
                String a = split[0], b = getB(split[2]);
                if (a.equals(me)) {
                    map.put(b, map.getOrDefault(b, 0) + likes);
                } else if (b.equals(me)) {
                    map.put(a, map.getOrDefault(a, 0) + likes);
                } else {
                    addDefault(a, b);
                }
            }

        }

        List<Node> lst = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            lst.add(new Node(entry.getKey(), entry.getValue()));
        }

        lst.sort((o1, o2) -> {
            int diff = Integer.compare(o2.val, o1.val);
            if (diff != 0) return diff;
            return o1.s.compareTo(o2.s);
        });

        for (Node node : lst) {
            println(node.s);
        }
    }

    static class Node {
        String s;
        int val;

        public Node(String a, int v) {
            val = v;
            s = a;
        }
    }

    void addDefault(String a, String b) {
        if (!map.containsKey(a))
            map.put(a, 0);
        if (!map.containsKey(b))
            map.put(b, 0);
    }

    String getB(String s) {
        return s.substring(0, s.length() - 2);
    }

    String getString() throws IOException {
        StringBuilder sbr = new StringBuilder();
        int count = 0;
        while (true) {
            String s = rs();
            count++;
            sbr.append(s).append(" ");
            if ( count > 3 && (s.equals("wall") || s.equals("post"))) break;
        }
        return sbr.toString();
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new FacebookPriorityWall(), "1").start();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;

    @Override
    public void run() {
        try {
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
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
                } else
                    throw new InputMismatchException();
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
                } else
                    throw new InputMismatchException();
            }
            if (n == '.') {
                n = scan();
                double temp = 1;
                while (!isWhiteSpace(n)) {
                    if (n >= '0' && n <= '9') {
                        temp /= 10;
                        doub += (n - '0') * temp;
                        n = scan();
                    } else
                        throw new InputMismatchException();
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

    static void shuffle(int[] aa) {
        int n = aa.length;
        Random rand = new Random();
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);
            int tmp = aa[i];
            aa[i] = aa[j];
            aa[j] = tmp;
        }
    }

    static void shuffle(int[][] aa) {
        int n = aa.length;
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

    static final class Comparators {
        public static final Comparator<int[]> pairIntArr = (x, y) -> x[0] == y[0] ? compare(x[1], y[1])
                : compare(x[0], y[0]);

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

    static int[] iArr(int len) {
        return new int[len];
    }

    static long[] lArr(int len) {
        return new long[len];
    }

    static long min(long a, long b) {
        return Math.min(a, b);
    }

    static int min(int a, int b) {
        return Math.min(a, b);
    }

    static long max(Long a, Long b) {
        return Math.max(a, b);
    }

    static int max(int a, int b) {
        return Math.max(a, b);
    }

    static int ri() throws IOException {
        return read.intNext();
    }

    static long rl() throws IOException {
        return Long.parseLong(read.read());
    }

    static String rs() throws IOException {
        return read.read();
    }

    static double rd() throws IOException {
        return read.doubleNext();
    }
}
