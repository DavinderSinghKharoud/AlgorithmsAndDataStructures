import java.io.*;
import java.util.*;

/**
 *
 FindGreatestCommonDivisor championship is held in Berland, in which 𝑛
 n
 players participate. The player with the number 𝑖
 i
 has 𝑎𝑖
 a
 i
 (𝑎𝑖≥1
 a
 i
 ≥
 1
 ) tokens.

 The championship consists of 𝑛−1
 n
 −
 1
 games, which are played according to the following rules:

 in each game, two random players with non-zero tokens are selected;
 the player with more tokens is considered the winner of the game (in case of a tie, the winner is chosen randomly);
 the winning player takes all of the loser's tokens;
 The last player with non-zero tokens is the winner of the championship.

 All random decisions that are made during the championship are made equally probable and independently.

 For example, if 𝑛=4
 n
 =
 4
 , 𝑎=[1,2,4,3]
 a
 =
 [
 1
 ,
 2
 ,
 4
 ,
 3
 ]
 , then one of the options for the game (there could be other options) is:

 during the first game, the first and fourth players were selected. The fourth player has more tokens, so he takes the first player's tokens. Now 𝑎=[0,2,4,4]
 a
 =
 [
 0
 ,
 2
 ,
 4
 ,
 4
 ]
 ;
 during the second game, the fourth and third players were selected. They have the same number of tokens, but in a random way, the third player is the winner. Now 𝑎=[0,2,8,0]
 a
 =
 [
 0
 ,
 2
 ,
 8
 ,
 0
 ]
 ;
 during the third game, the second and third players were selected. The third player has more tokens, so he takes the second player's tokens. Now 𝑎=[0,0,10,0]
 a
 =
 [
 0
 ,
 0
 ,
 10
 ,
 0
 ]
 ;
 the third player is declared the winner of the championship.
 Championship winners will receive personalized prizes. Therefore, the judges want to know in advance which players have a chance of winning, i.e have a non-zero probability of winning the championship. You have been asked to find all such players.

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

 The first line of each test case consists of one positive integer 𝑛
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
 ) — the number of players in the championship.

 The second line of each test case contains 𝑛
 n
 positive integers 𝑎1,𝑎2,…,𝑎𝑛
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
 ) — the number of tokens the players have.

 It is guaranteed that the sum of 𝑛
 n
 over all test cases does not exceed 2⋅105
 2
 ⋅
 10
 5
 .

 Output
 For each test case, print the number of players who have a nonzero probability of winning the championship. On the next line print the numbers of these players in increasing order. Players are numbered starting from one in the order in which they appear in the input.

 Example
 inputCopy
 2
 4
 1 2 4 3
 5
 1 1 1 1 1
 outputCopy
 3
 2 3 4
 5
 1 2 3 4 5
 */
@SuppressWarnings("unchecked")
public class AccidentalVictory implements Runnable {
    int[] arr;

    void solve() throws IOException {
        int t = read.intNext();

        while (t-- > 0) {
            int n = read.intNext();

            arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = read.intNext();
            }

            int[] b = Arrays.copyOf(arr, n);

            shuffle(b, n);
            Arrays.sort(b);

            int l = -1, h = n - 1;

            while (h - l > 1) {
                int mid = (l + h) / 2;
                if (win(mid, b)) {
                    h = mid;
                } else {
                    l = mid;
                }
            }

            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (arr[i] >= b[h]) {
                    res.add(i + 1);
                }
            }

            sbr.append(res.size()).append('\n');

            for (Integer i : res) {
                sbr.append(i).append(' ');
            }

            sbr.append('\n');

        }

        print(sbr);
    }

    boolean win(int index, int[] arr) {
        long power = arr[index];

        for (int i = 0; i < arr.length; i++) {
            if (i == index) continue;
            if (power < arr[i]) return false;
            power += arr[i];
        }
        return true;
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new AccidentalVictory(), "1").start();
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


