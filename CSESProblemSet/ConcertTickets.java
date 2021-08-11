
import java.io.*;
import java.util.*;

/**
 * here are n
 * n
 * concert tickets available, each with a certain price. Then, m
 * m
 * customers arrive, one after another.
 * <p>
 * Each customer announces the maximum price he or she is willing to pay for a ticket, and after this, they will get a ticket with the nearest possible price such that it does not exceed the maximum price.
 * <p>
 * Input
 * <p>
 * The first input line contains integers n
 * n
 * and m
 * m
 * : the number of tickets and the number of customers.
 * <p>
 * The next line contains n
 * n
 * integers h1,h2,…,hn
 * h
 * 1
 * ,
 * h
 * 2
 * ,
 * …
 * ,
 * h
 * n
 * : the price of each ticket.
 * <p>
 * The last line contains m
 * m
 * integers t1,t2,…,tm
 * t
 * 1
 * ,
 * t
 * 2
 * ,
 * …
 * ,
 * t
 * m
 * : the maximum price for each customer.
 * <p>
 * Output
 * <p>
 * Print, for each customer, the price that they will pay for their ticket. After this, the ticket cannot be purchased again.
 * <p>
 * If a customer cannot get any ticket, print −1
 * −
 * 1
 * .
 * <p>
 * Constraints
 * 1≤n,m≤2⋅105
 * 1
 * ≤
 * n
 * ,
 * m
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 * <p>
 * 1≤hi,ti≤109
 * 1
 * ≤
 * h
 * i
 * ,
 * t
 * i
 * ≤
 * 10
 * 9
 * <p>
 * Example
 * <p>
 * Input:
 * 5 3
 * 5 3 7 8 5
 * 4 8 3
 * <p>
 * Output:
 * 3
 * 8
 * -1x
 */
public class ConcertTickets {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {


        int count1 = fastReader.nextInt();
        int count2 = fastReader.nextInt();

//        TreeMap<Integer, Integer> map = new TreeMap<>(); //TLE
//
//        for (int i = 0; i < count1; i++) {
//            int curr = fastReader.nextInt();
//            map.put(curr, map.getOrDefault(curr, 0) + 1);
//        }
//
//        while (count2-- > 0) {
//            int curr = fastReader.nextInt();
//
//            Map.Entry<Integer, Integer> entry = map.floorEntry(curr);
//
//            if (entry == null) {
//                out.println(-1);
//            } else {
//                int key = entry.getKey();
//                int value = entry.getValue();
//                out.println(key);
//
//                if (--value == 0) {
//                    map.remove(key);
//                } else {
//                    map.put(key, value);
//                }
//            }
//        }

        int[] tickets = new int[count1 + 1];


        for (int index = 1; index <= count1; index++) {
            tickets[index] = fastReader.nextInt();
        }

        Arrays.sort(tickets);

        int[] isTaken = new int[count1 + 1];
        for (int index = 0; index <= count1; index++) {
            isTaken[index] = index;
        }
        while (count2-- > 0) {
            int curr = fastReader.nextInt();

            int start = 0;
            int end = tickets.length - 1;
            int ans = -1;

            while (start <= end) {
                int middle = (end - start) / 2 + start;
                boolean isPossible = tickets[middle] <= curr;
                if (isPossible) {
                    ans = middle;
                    start = middle + 1;
                } else {
                    end = middle - 1;
                }
            }

            if( ans == 0 ){
                out.print(-1 + " "); continue;
            }
            int index = find( ans, isTaken);
            if( index == 0 ){
                out.print(-1 + " "); continue;
            }
            out.print(tickets[index] + " ");
            isTaken[index] = index - 1;
        }


        out.close();
    }

    private static int find(int ans, int[] isTaken) {
        return (isTaken[ans] == ans)? ans: ( isTaken[ans] = find(isTaken[ans], isTaken));
    }

    static int bsearch(int cus, int[] tickets, int start, int end) {
        if (start > end) return -1;
        int index = start;
        int mid = (end - start) / 2 + start;

        for (int jump = mid; jump >= 1; jump /= 2) {
            while (jump + index <= end && tickets[jump + index] <= cus) index += jump;
        }

        if (tickets[index] <= cus) return index;
        return -1;
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}
