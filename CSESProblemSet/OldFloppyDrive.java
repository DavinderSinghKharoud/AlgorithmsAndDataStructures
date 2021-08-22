import java.io.*;
import java.util.*;

/**
 *
 Polycarp was dismantling his attic and found an old floppy drive on it. FindGreatestCommonDivisor round disc was inserted into the drive with 𝑛
 n
 integers written on it.

 Polycarp wrote the numbers from the disk into the 𝑎
 a
 array. It turned out that the drive works according to the following algorithm:

 the drive takes one positive number 𝑥
 x
 as input and puts a pointer to the first element of the 𝑎
 a
 array;
 after that, the drive starts rotating the disk, every second moving the pointer to the next element, counting the sum of all the elements that have been under the pointer. Since the disk is round, in the 𝑎
 a
 array, the last element is again followed by the first one;
 as soon as the sum is at least 𝑥
 x
 , the drive will shut down.
 Polycarp wants to learn more about the operation of the drive, but he has absolutely no free time. So he asked you 𝑚
 m
 questions. To answer the 𝑖
 i
 -th of them, you need to find how many seconds the drive will work if you give it 𝑥𝑖
 x
 i
 as input. Please note that in some cases the drive can work infinitely.

 For example, if 𝑛=3,𝑚=3
 n
 =
 3
 ,
 m
 =
 3
 , 𝑎=[1,−3,4]
 a
 =
 [
 1
 ,
 −
 3
 ,
 4
 ]
 and 𝑥=[1,5,2]
 x
 =
 [
 1
 ,
 5
 ,
 2
 ]
 , then the answers to the questions are as follows:

 the answer to the first query is 0
 0
 because the drive initially points to the first item and the initial sum is 1
 1
 .
 the answer to the second query is 6
 6
 , the drive will spin the disk completely twice and the amount becomes 1+(−3)+4+1+(−3)+4+1=5
 1
 +
 (
 −
 3
 )
 +
 4
 +
 1
 +
 (
 −
 3
 )
 +
 4
 +
 1
 =
 5
 .
 the answer to the third query is 2
 2
 , the amount is 1+(−3)+4=2
 1
 +
 (
 −
 3
 )
 +
 4
 =
 2
 .
 Input
 The first line contains one integer 𝑡
 t
 (1≤𝑡≤104
 1
 ≤
 t
 ≤
 10
 4
 ) — the number of test cases. Then 𝑡
 t
 test cases follow.

 The first line of each test case consists of two positive integers 𝑛
 n
 , 𝑚
 m
 (1≤𝑛,𝑚≤2⋅105
 1
 ≤
 n
 ,
 m
 ≤
 2
 ⋅
 10
 5
 ) — the number of numbers on the disk and the number of asked questions.

 The second line of each test case contains 𝑛
 n
 integers 𝑎1,𝑎2,…,𝑎𝑛
 a
 1
 ,
 a
 2
 ,
 …
 ,
 a
 n
 (−109≤𝑎𝑖≤109
 −
 10
 9
 ≤
 a
 i
 ≤
 10
 9
 ).

 The third line of each test case contains 𝑚
 m
 positive integers 𝑥1,𝑥2,…,𝑥𝑚
 x
 1
 ,
 x
 2
 ,
 …
 ,
 x
 m
 (1≤𝑥≤109
 1
 ≤
 x
 ≤
 10
 9
 ).

 It is guaranteed that the sums of 𝑛
 n
 and 𝑚
 m
 over all test cases do not exceed 2⋅105
 2
 ⋅
 10
 5
 .

 Output
 Print 𝑚
 m
 numbers on a separate line for each test case. The 𝑖
 i
 -th number is:

 −1
 −
 1
 if the drive will run infinitely;
 the number of seconds the drive will run, otherwise.

 */
@SuppressWarnings("unchecked")
public class OldFloppyDrive implements Runnable {

    void solve() throws IOException {

        int t = read.intNext();

        while (t-- > 0) {
            int n = read.intNext(), m = read.intNext();

            long sum = 0, maxPrefix = Long.MIN_VALUE;
            TreeMap<Long, Integer> map = new TreeMap<>();

            for (int i = 0; i < n; i++) {
                sum += read.intNext();
                if (sum > maxPrefix) { //Add the new Max
                    maxPrefix = sum;
                    map.put(maxPrefix, i);
                }
            }

            while (m-- > 0) {
                long curr = read.intNext();
                long res = 0;
                if (curr <= map.lastKey()) {
                    res += map.ceilingEntry(curr).getValue();
                } else if (sum <= 0) {
                    res = -1;
                } else {

                    long tmp = curr - map.lastKey();
                    long cycles = tmp/sum;
                    curr -= cycles * sum;
                    res += cycles * n;

                    if( curr > map.lastKey()){
                        curr -= sum;
                        res += n;
                    }

                    res += map.ceilingEntry(curr).getValue();

                }

                print(res + " ");

            }
            println(" ");

        }
    }


    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new OldFloppyDrive(), "1").start();
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


