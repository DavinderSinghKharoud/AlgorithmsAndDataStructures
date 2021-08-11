package CodeForces.Round720;

import java.io.*;
import java.util.*;

/**
 *
 This is an interactive problem!

 Nastia has a hidden permutation 𝑝
 p
 of length 𝑛
 n
 consisting of integers from 1
 1
 to 𝑛
 n
 . You, for some reason, want to figure out the permutation. To do that, you can give her an integer 𝑡
 t
 (1≤𝑡≤2
 1
 ≤
 t
 ≤
 2
 ), two different indices 𝑖
 i
 and 𝑗
 j
 (1≤𝑖,𝑗≤𝑛
 1
 ≤
 i
 ,
 j
 ≤
 n
 , 𝑖≠𝑗
 i
 ≠
 j
 ), and an integer 𝑥
 x
 (1≤𝑥≤𝑛−1
 1
 ≤
 x
 ≤
 n
 −
 1
 ).

 Depending on 𝑡
 t
 , she will answer:

 𝑡=1
 t
 =
 1
 : max(min(𝑥,𝑝𝑖),min(𝑥+1,𝑝𝑗))
 max
 (
 min
 (
 x
 ,
 p
 i
 )
 ,
 min
 (
 x
 +
 1
 ,
 p
 j
 )
 )
 ;
 𝑡=2
 t
 =
 2
 : min(max(𝑥,𝑝𝑖),max(𝑥+1,𝑝𝑗))
 min
 (
 max
 (
 x
 ,
 p
 i
 )
 ,
 max
 (
 x
 +
 1
 ,
 p
 j
 )
 )
 .
 You can ask Nastia at most ⌊3⋅𝑛2⌋+30
 ⌊
 3
 ⋅
 n
 2
 ⌋
 +
 30
 times. It is guaranteed that she will not change her permutation depending on your queries. Can you guess the permutation?

 Input
 The input consists of several test cases. In the beginning, you receive the integer 𝑇
 T
 (1≤𝑇≤10000
 1
 ≤
 T
 ≤
 10
 000
 ) — the number of test cases.

 At the beginning of each test case, you receive an integer 𝑛
 n
 (3≤𝑛≤104
 3
 ≤
 n
 ≤
 10
 4
 ) — the length of the permutation 𝑝
 p
 .

 It's guaranteed that the permutation is fixed beforehand and that the sum of 𝑛
 n
 in one test doesn't exceed 2⋅104
 2
 ⋅
 10
 4
 .

 Interaction
 To ask a question, print "? 𝑡
 t
 𝑖
 i
 𝑗
 j
 𝑥
 x
 " (𝑡=1
 t
 =
 1
 or 𝑡=2
 t
 =
 2
 , 1≤𝑖,𝑗≤𝑛
 1
 ≤
 i
 ,
 j
 ≤
 n
 , 𝑖≠𝑗
 i
 ≠
 j
 , 1≤𝑥≤𝑛−1
 1
 ≤
 x
 ≤
 n
 −
 1
 ) Then, you should read the answer.

 If we answer with −1
 −
 1
 instead of a valid answer, that means you exceeded the number of queries or made an invalid query. Exit immediately after receiving −1
 −
 1
 and you will see the Wrong Answer verdict. Otherwise, you can get an arbitrary verdict because your solution will continue to read from a closed stream.

 To print the answer, print "! 𝑝1
 p
 1
 𝑝2
 p
 2
 …
 …
 𝑝𝑛
 p
 n
 (without quotes). Note that answering doesn't count as one of the ⌊3⋅𝑛2⌋+30
 ⌊
 3
 ⋅
 n
 2
 ⌋
 +
 30
 queries.

 After printing a query or printing the answer, do not forget to output end of line and flush the output. Otherwise, you will get Idleness limit exceeded. To do this, use:

 fflush(stdout) or cout.flush() in C++;
 System.out.flush() in Java;
 flush(output) in Pascal;
 stdout.flush() in Python;
 See the documentation for other languages.
 Hacks

 To hack the solution, use the following test format.

 The first line should contain a single integer 𝑇
 T
 (1≤𝑇≤10000
 1
 ≤
 T
 ≤
 10
 000
 ) — the number of test cases.

 For each test case in the first line print a single integer 𝑛
 n
 (3≤𝑛≤104
 3
 ≤
 n
 ≤
 10
 4
 ) — the length of the hidden permutation 𝑝
 p
 .

 In the second line print 𝑛
 n
 space-separated integers 𝑝1,𝑝2,…,𝑝𝑛
 p
 1
 ,
 p
 2
 ,
 …
 ,
 p
 n
 (1≤𝑝𝑖≤𝑛
 1
 ≤
 p
 i
 ≤
 n
 ), where 𝑝
 p
 is permutation.

 Note that the sum of 𝑛
 n
 over all test cases should not exceed 2⋅104
 2
 ⋅
 10
 4
 .
 */
public class NastiaAndHiddenPermutation implements Runnable {

    void solve() throws IOException {
        int t = ri();
        while (t-- > 0) {
            int n = ri();
            int[] arr = iArr(n);

            // First find position of 1
            List<Integer> onePos = new ArrayList<>();
            int oneIndex = -1;
            for (int i = 0; i + 1 < n; i += 2) {
                int curr = ask(2, i, i + 1, 1);
                if (curr == 1) {
                    arr[i] = 1;
                    oneIndex = i;
                    break;
                } else if (curr == 2) {
                    onePos.add(i + 1);
                    onePos.add(i);
                }
            }
            if ((n & 1) == 1)
                onePos.add(n - 1);
            if (oneIndex == -1) {
                // Find the pos of 1
                if (onePos.size() == 1) {
                    oneIndex = onePos.get(0);
                } else {
                    for (int i = 0; i + 1 < onePos.size(); i += 2) {
                        int curr = ask(2, onePos.get(i), onePos.get(i + 1), 1);
                        if (curr == 1) {
                            oneIndex = onePos.get(i);
                            break;
                        }
                    }
                    if (oneIndex == -1) {
                        oneIndex = onePos.get(onePos.size() - 1);
                    }
                }
                arr[oneIndex] = 1;
            }

            // Now, we can find all other numbers just by query of type 1
            for (int i = 0; i < n; i++) {
                if (i == oneIndex)
                    continue;
                int curr = ask(1, oneIndex, i, n - 1);
                arr[i] = curr;
            }

            System.out.print("! ");
            for (int num : arr) {
                System.out.print(num + " ");
            }
            System.out.flush();
        }
    }

    int ask(int t, int i, int j, int k) throws IOException {
        i++;
        j++;
        System.out.println("? " + t + " " + i + " " + j + " " + k);
        return ri();
    }

    /************************************************************************************************************************************************/
    public static void main(String[] args) throws IOException {
        new Thread(null, new NastiaAndHiddenPermutation(), "1").start();
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
