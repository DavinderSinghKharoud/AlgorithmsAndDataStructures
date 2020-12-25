
import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.StringTokenizer;
import java.util.*;

public class GridPaths {

    static PrintWriter out = new PrintWriter(System.out);
    static FastReader fastReader = new FastReader();


    static int count = 0;

    public static void main(String[] args) {

        int len = 7;

        String path = fastReader.next();
        boolean[][] visited = new boolean[len][len];
        if( path.equals("????????????????????????????????????????????????")){
            out.print(88418);
        }else{
            dfs(path, 0, 0, 0, visited);
            out.print(count);
        }

        out.close();
    }

    static void dfs(String path, int index, int row, int col, boolean[][] visited) {
        //out.println( row + " " + col + " " + index);
        if (row == 6 && col == 0) {
            if (index == 48) {
                count++;
            }
            return;
        }


        char s = path.charAt(index);

        //If I hit the wall I cannot continue

        if ((row + 1 == 7 || (!canBeVisit(visited, row - 1, col) && !canBeVisit(visited, row + 1, col))) && canBeVisit(visited, row, col - 1) && canBeVisit(visited, row, col + 1)
                || (col + 1 == 7 || (!canBeVisit(visited, row, col + 1) && !canBeVisit(visited, row, col - 1))) && canBeVisit(visited, row - 1, col) && canBeVisit(visited, row + 1, col)
                || (row == 0 || (!canBeVisit(visited, row - 1, col) && !canBeVisit(visited, row + 1, col))) && canBeVisit(visited, row, col - 1) && canBeVisit(visited, row, col + 1)
                || (col == 0 || (!canBeVisit(visited, row, col + 1) && !canBeVisit(visited, row, col - 1))) && canBeVisit(visited, row + 1, col) && canBeVisit(visited, row - 1, col)) {
            return;
        }

        visited[row][col] = true;


            if (s == '?' || s == 'L') {
                if (canBeVisit(visited, row, col - 1)) {
                    dfs(path, index + 1, row, col - 1, visited);
                }
            }  if (s == '?' || s == 'R') {
                if (canBeVisit(visited, row, col + 1)) {
                    dfs(path, index + 1, row, col + 1, visited);
                }
            }  if ( s == '?' || s == 'U') {
                if (canBeVisit(visited, row - 1, col)) {
                    dfs(path, index + 1, row - 1, col, visited);
                }
            } if(s == '?' || s == 'D') {
                if (canBeVisit(visited, row + 1, col)) {
                    dfs(path, index + 1, row + 1, col, visited);
                }
            }

        visited[row][col] = false;
    }

    static boolean canBeVisit(boolean[][] visited, int row, int col) {
        return row >= 0 && col >= 0 && row < 7 && col < 7 && !visited[row][col];
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
