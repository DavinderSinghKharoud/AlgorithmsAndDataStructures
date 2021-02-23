import java.io.*;
import java.util.*;

/**
 *
 The only difference between the easy and the hard version is the limit to the number of queries.

 This is an interactive problem.

 There is an array 𝑎
 a
 of 𝑛
 n
 different numbers. In one query you can ask the position of the second maximum element in a subsegment 𝑎[𝑙..𝑟]
 a
 [
 l
 .
 .
 r
 ]
 . Find the position of the maximum element in the array in no more than 20 queries.

 A subsegment 𝑎[𝑙..𝑟]
 a
 [
 l
 .
 .
 r
 ]
 is all the elements 𝑎𝑙,𝑎𝑙+1,...,𝑎𝑟
 a
 l
 ,
 a
 l
 +
 1
 ,
 .
 .
 .
 ,
 a
 r
 . After asking this subsegment you will be given the position of the second maximum from this subsegment in the whole array.

 Input
 The first line contains a single integer 𝑛
 n
 (2≤𝑛≤105)
 (
 2
 ≤
 n
 ≤
 10
 5
 )
 — the number of elements in the array.

 Interaction
 You can ask queries by printing "? 𝑙
 l
 𝑟
 r
 " (1≤𝑙<𝑟≤𝑛)
 (
 1
 ≤
 l
 <
 r
 ≤
 n
 )
 . The answer is the index of the second maximum of all elements 𝑎𝑙,𝑎𝑙+1,...,𝑎𝑟
 a
 l
 ,
 a
 l
 +
 1
 ,
 .
 .
 .
 ,
 a
 r
 . Array 𝑎
 a
 is fixed beforehand and can't be changed in time of interaction.

 You can output the answer by printing "! 𝑝
 p
 ", where 𝑝
 p
 is the index of the maximum element in the array.

 You can ask no more than 20 queries. Printing the answer doesn't count as a query.

 After printing a query do not forget to output end of line and flush the output. Otherwise, you will get Idleness limit exceeded. To do this, use:

 fflush(stdout) or cout.flush() in C++;
 System.out.flush() in Java;
 flush(output) in Pascal;
 stdout.flush() in Python;
 see documentation for other languages
 Hacks

 To make a hack, use the following test format.

 In the first line output a single integer 𝑛
 n
 (2≤𝑛≤105)
 (
 2
 ≤
 n
 ≤
 10
 5
 )
 . In the second line output a permutation of 𝑛
 n
 integers 1
 1
 to 𝑛
 n
 . The position of 𝑛
 n
 in the permutation is the position of the maximum
 */
@SuppressWarnings("unchecked")
public class GuessingTheGreatestHard implements Runnable {

    void solve() throws IOException {
        int l = 1, h = read.intNext();

        int dmax = ask(l, h);

        if (ask(l, dmax) == dmax) {
            h = dmax;

            while (h - l > 1) {
                int m = (l + h) / 2;
                if (ask(m, dmax) == dmax) {
                    l = m;
                } else {
                    h = m;
                }

            }
            int q = ask(l, h);
            if (q == h) {
                println("! " + l);
            } else {
                println("! " + h);
            }
        } else {
            l = dmax;
            while (h - l > 1) {
                int m = (l + h) / 2;
                if (ask(dmax, m) == dmax) {
                    h = m;
                } else {
                    l = m;
                }

            }
            int q = ask(l, h);
            if (q == h) {
                println("! " + l);
            } else {
                println("! " + h);
            }
        }
    }

    int ask(int l, int h) throws IOException {
        if (l >= h) return -1;
        System.out.println("? " + l + " " + h);
        return read.intNext();
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new GuessingTheGreatestHard(), "1").start();
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
}


