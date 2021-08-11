
import java.io.*;
import java.util.*;

/**
 * n
 * n
 * customers will arrive soon. Each customer wants to have a single room.
 * <p>
 * You know each customer's arrival and departure day. Two customers can stay in the same room if the departure day of the first customer is earlier than the arrival day of the second customer.
 * <p>
 * What is the minimum number of rooms that are needed to accommodate all customers? And how can the rooms be allocated?
 * <p>
 * Input
 * <p>
 * The first input line contains an integer n
 * n
 * : the number of customers.
 * <p>
 * Then there are n
 * n
 * lines, each of which describes one customer. Each line has two integers a
 * a
 * and b
 * b
 * : the arrival and departure day.
 * <p>
 * Output
 * <p>
 * Print first an integer k
 * k
 * : the minimum number of rooms required.
 * <p>
 * After that, print a line that contains the room number of each customer in the same order as in the input. The rooms are numbered 1,2,…,k
 * 1
 * ,
 * 2
 * ,
 * …
 * ,
 * k
 * . You can print any valid solution.
 * <p>
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
 * <p>
 * 1≤a≤b≤109
 * 1
 * ≤
 * a
 * ≤
 * b
 * ≤
 * 10
 * 9
 * <p>
 * Example
 * <p>
 * Input:
 * 3
 * 1 2
 * 2 4
 * 4 4
 * <p>
 * Output:
 * 2
 * 1 2 1
 */
public class RoomAllocation {

    static PrintWriter out = new PrintWriter(System.out);
    static Reader fastReader = new Reader();

    public static void main(String[] args) throws IOException {

        //solve();
        solve2();
        out.close();
    }

    private static void solve2() throws IOException {

        int tests = fastReader.nextInt();
        int[] ans = new int[tests];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int maxRooms = 0;

        List<Event> events = new ArrayList<>();

        for (int index = 0; index < tests; index++) {
            events.add(new Event(fastReader.nextInt(), index, 0));
            events.add(new Event(fastReader.nextInt(), index, 1));
        }

        Collections.sort(events);

        for(Event event: events){
            int type = event.type;
            if(type == 1){//out
                pq.add(ans[event.id]);
            }else {
                //In
                int chosen;
                if( pq.isEmpty() ){
                    chosen = ++maxRooms;
                }else {
                    chosen = pq.poll();
                }
                ans[event.id] = chosen;
            }
        }

        out.println(maxRooms);
        StringBuilder sbr = new StringBuilder();
        for (int val : ans) {
            sbr.append(val).append(" ");
        }
        out.print(sbr);
    }


    private static void solve() throws IOException {
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
    }

    static class Event implements Comparable<Event> {
        int time, id, type;

        Event(int time, int id, int type) {
            this.id = id;
            this.time = time;
            this.type = type;
        }

        public int compareTo(Event other) {
            if (this.time < other.time)
                return -1;
            if (this.time > other.time)
                return 1;
            return Integer.compare(this.type, other.type);
        }

        public String toString() {
            return time + ", " + id + ", " + type;
        }
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {

            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;

        }

        public String next() throws IOException {

            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);

        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    static void shuffle(int[][] aa, int n) {
        Random rand = new Random();
        int len = aa[0].length;
        int[] temp = new int[len];
        for (int i = 1; i < n; i++) {
            int j = rand.nextInt(i + 1);

            for (int index = 0; index < len; index++) {
                temp[index] = aa[i][index];
            }

            for (int index = 0; index < len; index++) {
                aa[i][index] = aa[j][index];
            }

            for (int index = 0; index < len; index++) {
                aa[j][index] = temp[index];
            }

        }
    }
}
