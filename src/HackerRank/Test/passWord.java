package HackerRank.Test;

import java.io.*;
import java.util.*;

public class passWord implements Runnable {

    void solve() throws IOException {
        List<List<String>> lst = new ArrayList<>();
        lst = Arrays.asList(Arrays.asList("setPassword", "JZiOhCap"), Arrays.asList("authorize", "15273"),
                Arrays.asList("setPassword", "DBf2QuP"),
                Arrays.asList("setPassword", "m"));
        System.out.println(lst);
        System.out.println(authEvents(lst));
    }

    int p = 131;
    long[] ps = new long[9];
    HashSet<Long> set = new HashSet<>();

    public List<Integer> authEvents(List<List<String>> events) {
        List<Integer> ans = new ArrayList<>();
        preCompute();
        for (List<String> query : events) {
            String key = query.get(0);
            if (key.equals("setPassword")) {
                setPassword(query.get(1));
            } else {
                ans.add((isAuthorized(query.get(1)) ? 1 : 0));
            }
        }
        return ans;
    }

    boolean isAuthorized(String s) {
        return set.contains(Long.parseLong(s));
    }

    void setPassword(String s) {
        set = new HashSet<>();
        long curr = s.charAt(s.length() - 1);
        int start = 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            curr = add(curr, mul(s.charAt(i), ps[start++]));
        }

        // Find all other possible values
        set.add(curr);

        //PreCompute with one next power
        curr = mul(s.charAt(s.length() - 1), ps[0]);
        start = 1;
        for (int i = s.length() - 2; i >= 0; i--) {
            curr = add(curr, mul(s.charAt(i), ps[start++]));
        }

        for (int i = 'a'; i <= 'z'; i++) {
            set.add(add(curr, i));
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            set.add(add(curr, i));
        }
        for (int i = '1'; i <= '9'; i++) {
            set.add(add(curr, i));
        }
    }

    long add(long a, long b) {
        return (a + b) % mod;
    }

    long mul(long a, long b) {
        return (a * b) % mod;
    }

    void preCompute() {
        ps[0] = p;
        for (int i = 1; i < 9; i++) {
            ps[i] = mul(ps[i - 1], p);
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new passWord(), "1").start();
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

    // Gives strict lowerBound that previous number would be smaller than the target
    int lowerBound(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l) >> 1;
            if (arr[mid] >= val) {
                r = mid;
            } else
                l = mid + 1;
        }
        return l;
    }

    // Gives strict upperBound that next number would be greater than the target
    int upperBound(int[] arr, int val) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int mid = (r + l + 1) >> 1;
            if (arr[mid] <= val) {
                l = mid;
            } else
                r = mid - 1;
        }
        return l;
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

    static char rc() throws IOException {
        return rs().charAt(0);
    }

    static double rd() throws IOException {
        return read.doubleNext();
    }
}
