
import java.io.*;
import java.util.*;

/**
 * n
 * n
 *  customers will arrive soon. Each customer wants to have a single room.
 *
 * You know each customer's arrival and departure day. Two customers can stay in the same room if the departure day of the first customer is earlier than the arrival day of the second customer.
 *
 * What is the minimum number of rooms that are needed to accommodate all customers? And how can the rooms be allocated?
 *
 * Input
 *
 * The first input line contains an integer n
 * n
 * : the number of customers.
 *
 * Then there are n
 * n
 *  lines, each of which describes one customer. Each line has two integers a
 * a
 *  and b
 * b
 * : the arrival and departure day.
 *
 * Output
 *
 * Print first an integer k
 * k
 * : the minimum number of rooms required.
 *
 * After that, print a line that contains the room number of each customer in the same order as in the input. The rooms are numbered 1,2,…,k
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * k
 * . You can print any valid solution.
 *
 * Constraints
 * 1≤n≤2⋅105
 * 1
 * ≤
 * n
 * ≤
 * 2
 * ⋅
 * 10
 * 5
 *
 * 1≤a≤b≤109
 * 1
 * ≤
 * a
 * ≤
 * b
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 3
 * 1 2
 * 2 4
 * 4 4
 *
 * Output:
 * 2
 * 1 2 1
 */
public class RoomAllocation {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {


        int tests = fastReader.nextInt();
        int[] ans = new int[tests];
        int[][] arr = new int[tests][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1], o2[1]);
            }
            return Integer.compare(o1[0], o2[0]);
        }));
        int maxRooms = 0;

        for (int index = 0; index < tests; index++) {
            arr[index] = new int[]{fastReader.nextInt(), fastReader.nextInt(), index};
        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        int room = 1;
        for (int[] curr : arr) {
            if (pq.isEmpty()) {
                pq.add(new int[]{curr[1], room});
                ans[curr[2]] = room++;
            } else {
                int[] min = pq.peek();
                if (min[0] < curr[0]) {
                    pq.remove(min);
                    pq.add(new int[]{curr[1], min[1]});
                    ans[curr[2]] = min[1];
                } else {
                    pq.add(new int[]{curr[1], room++});
                    ans[curr[2]] = room - 1;
                }
            }

            maxRooms = Math.max(maxRooms, pq.size());
        }
        out.println(maxRooms);
        for (int ind = 0; ind < tests; ind++) {
            out.print(ans[ind] + " ");
        }
        out.close();
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
