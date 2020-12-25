
import java.io.*;
import java.util.*;

/**
 * There are n
 * n
 *  applicants and m
 * m
 *  free apartments. Your task is to distribute the apartments so that as many applicants as possible will get an apartment.
 *
 * Each applicant has a desired apartment size, and they will accept any apartment whose size is close enough to the desired size.
 *
 * Input
 *
 * The first input line has three integers n
 * n
 * , m
 * m
 * , and k
 * k
 * : the number of applicants, the number of apartments, and the maximum allowed difference.
 *
 * The next line contains n
 * n
 *  integers a1,a2,…,an
 * a
 * 1
 * ,
 * a
 * 2
 * ,
 * …
 * ,
 * a
 * n
 * : the desired apartment size of each applicant. If the desired size of an applicant is x
 * x
 * , he or she will accept any apartment whose size is between x−k
 * x
 * −
 * k
 *  and x+k
 * x
 * +
 * k
 * .
 *
 * The last line contains m
 * m
 *  integers b1,b2,…,bm
 * b
 * 1
 * ,
 * b
 * 2
 * ,
 * …
 * ,
 * b
 * m
 * : the size of each apartment.
 *
 * Output
 *
 * Print one integer: the number of applicants who will get an apartment.
 *
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
 *
 * 0≤k≤109
 * 0
 * ≤
 * k
 * ≤
 * 10
 * 9
 *
 * 1≤ai,bi≤109
 * 1
 * ≤
 * a
 * i
 * ,
 * b
 * i
 * ≤
 * 10
 * 9
 *
 * Example
 *
 * Input:
 * 4 3 5
 * 60 45 80 60
 * 30 60 75
 *
 * Output:
 * 2
 */
public class Apartments {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();

    public static void main(String[] args) throws InterruptedException {

		int countApp = fastReader.nextInt();
		int countAppartments = fastReader.nextInt();
		int k = fastReader.nextInt();
		
		List<Integer> applicants = new ArrayList<>(), appartments = new ArrayList<>();
		
		while( countApp -- > 0 ){
			applicants.add( fastReader.nextInt() );
		}
		
		while( countAppartments -- > 0 ){
			appartments.add(fastReader.nextInt() );
		}

		Collections.sort(applicants);
		Collections.sort( appartments );
		
		int count = 0;
		for(int i = 0, j = 0; i < applicants.size(); i++ ){
		    while( j < appartments.size() && appartments.get(j) < applicants.get(i) - k ){
		        j++;
            }
		    if( j < appartments.size() && appartments.get(j) <= applicants.get(i) + k ){
		        count++; j++;
            }
        }

		out.print( count );
        out.close();
    }

    private static boolean canChoose(List<Integer> applicants, List<Integer> appartments, int applicant, int appartment, int k) {
        return  appartments.get(appartment) <= applicants.get(applicant) + k && appartments.get(appartment) >= applicants.get(applicant) - k;
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
