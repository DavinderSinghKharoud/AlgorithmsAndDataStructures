import java.io.*;
import java.util.*;

/**
 *
 Polycarp was gifted an array 𝑎
 a
 of length 𝑛
 n
 . Polycarp considers an array beautiful if there exists a number 𝐶
 C
 , such that each number in the array occurs either zero or 𝐶
 C
 times. Polycarp wants to remove some elements from the array 𝑎
 a
 to make it beautiful.

 For example, if 𝑛=6
 n
 =
 6
 and 𝑎=[1,3,2,1,4,2]
 a
 =
 [
 1
 ,
 3
 ,
 2
 ,
 1
 ,
 4
 ,
 2
 ]
 , then the following options are possible to make the array 𝑎
 a
 array beautiful:

 Polycarp removes elements at positions 2
 2
 and 5
 5
 , array 𝑎
 a
 becomes equal to [1,2,1,2]
 [
 1
 ,
 2
 ,
 1
 ,
 2
 ]
 ;
 Polycarp removes elements at positions 1
 1
 and 6
 6
 , array 𝑎
 a
 becomes equal to [3,2,1,4]
 [
 3
 ,
 2
 ,
 1
 ,
 4
 ]
 ;
 Polycarp removes elements at positions 1,2
 1
 ,
 2
 and 6
 6
 , array 𝑎
 a
 becomes equal to [2,1,4]
 [
 2
 ,
 1
 ,
 4
 ]
 ;
 Help Polycarp determine the minimum number of elements to remove from the array 𝑎
 a
 to make it beautiful.

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

 The first line of each test case consists of one integer 𝑛
 n
 (1≤𝑛≤2⋅105
 1
 ≤
 n
 ≤
 2
 ⋅
 10
 5
 ) — the length of the array 𝑎
 a
 .

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
 (1≤𝑎𝑖≤109
 1
 ≤
 a
 i
 ≤
 10
 9
 ) — array 𝑎
 a
 .

 It is guaranteed that the sum of 𝑛
 n
 over all test cases does not exceed 2⋅105
 2
 ⋅
 10
 5
 .

 Output
 For each test case, output one integer — the minimum number of elements that Polycarp has to remove from the array 𝑎
 a
 to make it beautiful.
 */
@SuppressWarnings("unchecked")
public class EqualizeTheArray implements Runnable {

    void solve() throws IOException {
        int t = read.intNext();
        while (t-- > 0) {
            int n = read.intNext();
            Map<Integer, Integer> map = new HashMap<>();
            List<Integer> arr = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int curr = read.intNext();
                map.put(curr, map.getOrDefault(curr, 0) + 1);
            }

            arr.addAll(map.values());
            Collections.sort(arr);
            int res = n;
            for (int i = 0; i < arr.size(); i++) {
                int rightCount = arr.get(i) * (arr.size() - i);

                res = min(res, n - rightCount);
            }
            println(res);

        }
    }

    public void solve2() throws IOException {
        int t = read.intNext();
        while (t-- > 0) {
            int n = read.intNext();

            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                int curr = read.intNext();
                map.put(curr, map.getOrDefault(curr, 0) + 1);
            }

            List<Integer> arr = new ArrayList<>();
            for(int value: map.values()){
                arr.add(value);
            }

            Collections.sort(arr);

            int[] prefix = new int[arr.size()], suffix = new int[arr.size()];
            prefix[0] = arr.get(0);
            suffix[arr.size() - 1] = arr.get(arr.size() - 1);
            for (int i = 1; i < arr.size(); i++) {
                prefix[i] = prefix[i - 1] + arr.get(i) ;
            }
            for(int i = arr.size() - 2; i >= 0; i--){
                suffix[i] = suffix[i + 1] + arr.get(i);
            }
            int res = n;
            for (int i = 0; i < arr.size(); i++) {
                int ope = 0;
                if( i - 1 >= 0){ //Operations from left
                    ope += prefix[i - 1];
                }

                if( i + 1 < arr.size()){
                    int shouldBe = (arr.size() - i - 1) * arr.get(i);
                    int present = suffix[i + 1];
                    ope += present - shouldBe;
                }
               res = min(res, ope);

            }
            println(res);
        }


    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new EqualizeTheArray(), "1").start();
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
            //solve();
            solve2();
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


