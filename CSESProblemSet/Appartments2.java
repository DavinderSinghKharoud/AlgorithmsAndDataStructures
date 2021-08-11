import java.io.*;
import java.util.*;

public class Appartments2 {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) throws InterruptedException {

        int countApp = fastReader.nextInt();
        int countAppartments = fastReader.nextInt();
        int k = fastReader.nextInt();

        List<Integer> applicants = new ArrayList<>(), appartments = new ArrayList<>();

        while (countApp-- > 0) {
            applicants.add(fastReader.nextInt());
        }

        while (countAppartments-- > 0) {
            appartments.add(fastReader.nextInt());
        }

        Collections.sort(applicants);
        Collections.sort(appartments);

        int count = 0;

        int start = 0;
//        for(int applicant = 0; applicant < applicants.size(); applicant++){
//            for( int appartment = start; appartment < appartments.size(); appartment++ ){
//                if( canChoose( applicants, appartments, applicant, appartment, k)){
//                    count++;
//                    start = appartment + 1;
//                    break;
//                }else{
//                    if( appartments.get(appartment) > applicants.get(applicant) + k) break;
//
//                }
//            }
//        }

        for (int a : applicants) {

            int res = isValid(start, appartments.size(), appartments, a, k);
            if (res == -1) continue;
            if (res == -2) break;
            start = res;
            count++;
        }
        out.print(count);
        out.close();
    }

    private static boolean canChoose(List<Integer> applicants, List<Integer> appartments, int applicant, int appartment, int k) {
        return appartments.get(appartment) <= applicants.get(applicant) + k && appartments.get(appartment) >= applicants.get(applicant) - k;
    }


    static int isValid(int start, int end, List<Integer> appartments, int applicant, int k) {
        if (start >= end) return -2;

        int lowest = Math.max(0, applicant - k);
        int highest = applicant + k;

        int mid = (end - start) / 2 + start;
        int index = start;
        for (int jump = mid; jump >= 1; jump /= 2) {
            while (jump + index < end && appartments.get(jump + index) <= lowest) {
                index += jump;
            }
        }


        if (appartments.get(index) >= lowest && appartments.get(index) <= highest) {
            return index + 1;
        } else if (index + 1 < end && appartments.get(index + 1) >= lowest && appartments.get(index + 1) <= highest) {
            return index + 2;
        }

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