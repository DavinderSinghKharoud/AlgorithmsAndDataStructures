
import java.io.*;
import java.util.*;

/**
 * There is a street of length x
 * x
 *  whose positions are numbered 0,1,…,x
 * 0
 * ,
 * 1
 * ,
 * …
 * ,
 * x
 * . Initially there are no traffic lights, but n
 * n
 *  sets of traffic lights are added to the street one after another.
 *
 * Your task is to calculate the length of the longest passage without traffic lights after each addition.
 *
 * Input
 *
 * The first input line contains two integers x
 * x
 *  and n
 * n
 * : the length of the street and the number of sets of traffic lights.
 *
 * Then, the next line contains n
 * n
 *  integers p1,p2,…,pn
 * p
 * 1
 * ,
 * p
 * 2
 * ,
 * …
 * ,
 * p
 * n
 * : the position of each set of traffic lights. Each position is distinct.
 *
 * Output
 *
 * Print the length of the longest passage without traffic lights after each addition.
 *
 * Constraints
 * 1≤x≤109
 * 1
 * ≤
 * x
 * ≤
 * 10
 * 9
 *
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
 * 0<pi<x
 * 0
 * <
 * p
 * i
 * <
 * x
 *
 * Example
 *
 * Input:
 * 8 3
 * 3 6 2
 *
 * Output:
 * 5 3 3
 */
public class TrafficLights {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) {

          int bound = fastReader.nextInt();
          int count = fastReader.nextInt();

          int[] input = new int[count];
          int[] output = new int[count];

          TreeSet<Integer> set = new TreeSet<>();

          set.add(0);set.add(bound);

        for (int index = 0; index < count; index++) {
            input[index] = fastReader.nextInt();
            set.add(input[index]);
        }

        int last = 0, best = 0;
        for(int element: set){
            best = Math.max( best, element - last);
            last = element;
        }

        output[count - 1] = best;

        for (int index = count - 2; index >= 0; index--) {
            best = Math.max( best, set.higher(input[index + 1]) - set.lower(input[index + 1]));
            set.remove( input[index + 1]);
            output[index] = best;
        }


        StringBuilder res = new StringBuilder();
        for(int num: output){
            res.append( num).append(" ");

        }
        out.print(res);
//        TreeMap<Integer, Integer> map1 = new TreeMap<>(); //TLE
//        TreeSet<Integer> map2 = new TreeSet<>();
//
//        int len = fastReader.nextInt();
//
//        map1.put(len, 1);
//
//        int count = fastReader.nextInt();
//
//        map2.add(0);
//        map2.add(len);
//
//        StringBuilder sbr = new StringBuilder();
//        while (count-- > 0) {
//            int curr = fastReader.nextInt();
//
//            Integer bound1 = map2.floor(curr);
//            Integer bound2 = map2.ceiling(curr);
//
//            int diff = bound2 - bound1;
//
//            map2.add(curr);
//
//            int value = map1.get(diff);
//            if (value == 1) map1.remove(diff);
//            else map1.put(diff, map1.get(diff) - 1);
//
//            map1.put(curr - bound1, map1.getOrDefault(curr - bound1, 0) + 1);
//            map1.put(bound2 - curr, map1.getOrDefault(bound2 - curr, 0) + 1);
//
//            out.print(map1.lastKey() + " ");
//
//        }

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
