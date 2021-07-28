package Algorithms.LeetCode;

import java.io.*;
import java.util.*;

public class MeetingSchedules implements Runnable {

    int edge = -1;

    void solve() {
        int m = ri(), k = ri();
        List<Time> times = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int starth = ri(), startm = ri(), endh = ri(), endm = ri();
            times.add(new Time(starth, startm));

            if (starth > 0 && endh == 0) {
                //Edge case
                edge = max(edge, endm);
            } else {
                times.add(new Time(endh, endm, true));
            }
        }
        times.add(new Time(0, 0));
        times.add(new Time(0, 0, true));
        times.sort((o1, o2) -> {
            if (o1.hour == o2.hour)
                return Integer.compare(o1.min, o2.min);
            return Integer.compare(o1.hour, o2.hour);

        });

        int prevh = -1, prevm = -1;
        int started = 0;
        for (Time time : times) {
            // settle
            if (time.isEnd) {
                // End
                started--;
                if (started == 0) {
                    prevh = time.hour;
                    prevm = time.min;
                }
            } else {
                // Starting
                if (started == 0 && prevh != -1) {
                    // Find the diff
                    int diff = find(prevh, prevm, time.hour, time.min);
                    if (diff >= k) {
                        print(prevh, prevm, time.hour, time.min);
                    }
                }

                prevh = -1;
                prevm = -1;
                started++;
            }
        }

        // Last edge case
        if (prevh != -1) {
            int diff = find(prevh, prevm, 24, 00);
            if (diff >= k) {
                print(prevh, prevm, 0, 0);
            }
        }
    }

    void print(int h1, int m1, int h2, int m2) {
        if (h1 == 0 && edge != -1) {
            if (m1 < edge) return;

        }
        String a = h1 + "", b = m1 + "", c = h2 + "", d = m2 + "";
        if (h1 < 10) {
            a = "0" + h1;
        }
        if (h2 < 10) {
            c = "0" + h2;
        }
        if (m1 < 10) {
            b = "0" + m1;
        }
        if (m2 < 10) {
            d = "0" + m2;
        }
        println(a + " " + b + " " + c + " " + d);
    }

    int find(int h1, int m1, int h2, int m2) {
        if (h1 > h2) {
            h1 = h1 ^ h2 ^ (h2 = h1);
            m1 = m1 ^ m2 ^ (m2 = m1);
        }
        int total = (h2 - h1) * 60;
        if (m1 <= m2) {
            total += (m2 - m1);
        } else {
            total -= (m1 - m2);
        }
        return total;
    }

    static class Time {
        int hour, min;
        boolean isEnd = false;

        public Time(int h, int m) {
            hour = h;
            min = m;
        }

        public Time(int h, int m, boolean end) {
            hour = h;
            min = m;
            isEnd = end;
        }
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new MeetingSchedules(), "1", 1 << 21).start();
    }

    static PrintWriter out = new PrintWriter(System.out);
    static Reader read = new Reader();
    static StringBuilder sbr = new StringBuilder();
    static int mod = (int) 1e9 + 7;
    static int dmax = Integer.MAX_VALUE;
    static long lmax = Long.MAX_VALUE;
    static int dmin = Integer.MIN_VALUE;
    static long lmin = Long.MIN_VALUE;
    static int[] dprimes = new int[]{1, 11, 101, 1087, 99991, 100001, 1000003, 15485863, 999999937};

    @Override
    public void run() {
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

    static int ri() {
        try {
            return read.intNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(2);
        return -1;
    }

    static long rl() {
        try {
            return Long.parseLong(read.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(2);
        return -1;
    }

    static String rs() {
        try {
            return read.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(2);
        return "";
    }

    static char rc() {
        return rs().charAt(0);
    }

    static double rd() {
        try {
            return read.doubleNext();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(2);
        return -1;
    }
}
