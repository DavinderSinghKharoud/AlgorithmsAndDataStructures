import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int n = scn.nextInt();
        int m = scn.nextInt();

        List<Integer>[] graph = new ArrayList[2 * m + 2];
        List<Integer>[] revGraph = new ArrayList[2 * m + 2];

        for (int i = 0; i < n; ++i) {
            char c = scn.next().charAt(0);
            int p = 2 * scn.nextInt(); // multiplying by 2 because topping x is denoted by 2x and -x is denoted by 2x+1
            char d = scn.next().charAt(0);
            int q = 2 * scn.nextInt();

            // a V b === -a->b ^ -b->a

            // add first edge
            // -a -> b

            int pt = p;
            int qt = q;

            if (c == '+') {
                pt += 1;
            }

            if (d == '-') {
                qt += 1;
            }

            if (graph[pt] == null) {
                graph[pt] = new ArrayList<>();
            }
            if (revGraph[qt] == null) {
                revGraph[qt] = new ArrayList<>();
            }

            graph[pt].add(qt);
            revGraph[qt].add(pt);
            // add second edge
            // -b -> a

            pt = p;
            qt = q;

            if (d == '+') {
                qt += 1;
            }

            if (c == '-') {
                pt += 1;
            }

            if (graph[qt] == null) {
                graph[qt] = new ArrayList<>();
            }
            if (revGraph[pt] == null) {
                revGraph[pt] = new ArrayList<>();
            }
            revGraph[pt].add(qt);
            graph[qt].add(pt);
        }

        SCC(n, m, graph, revGraph);

    }

    public static void SCC(int n, int m, List<Integer>[] graph, List<Integer>[] rGraph) {
        boolean[] visited = new boolean[2 * m + 2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2; i <= 2 * m + 1; ++i) {
            if (!visited[i]) {
                dfs1(i, graph, visited, stack);
            }
        }

        int count = 0;
        int[] scc = new int[2 * m + 2];
        visited = new boolean[2 * m + 2];
        while (stack.size() > 0) {
            int v = stack.pop();
            if (!visited[v]) {
                count++;
                dfs2(v, rGraph, visited, scc, count);
            }
        }

        boolean noSol=false;
        boolean[] ans = new boolean[m+1];
        for(int i=2;i<=2*m+1;i+=2) {
            if(scc[i]==scc[i+1]) {
                noSol=true;
                break;
            }

            if(scc[i]<scc[i+1]) {
                ans[i/2]=false;
            }
            else {
                ans[i/2]=true;
            }

        }

        if(noSol) {
            out.println("IMPOSSIBLE");
        }
        else {
            for(int i=1;i<=m;++i) {
                if(ans[i]) {
                    out.println('+');
                }
                else {
                    out.println('-');
                }
            }
        }
        out.close();
    }

    public static void dfs2(int root, List<Integer>[] graph, boolean[] visited, int[] scc, int sccVal) {

        visited[root] = true;
        scc[root] = sccVal;

        if (graph[root] != null) {
            for (int nbr : graph[root]) {
                if (!visited[nbr]) {
                    dfs2(nbr, graph, visited, scc, sccVal);
                }
            }
        }
    }

    public static void dfs1(int root, List<Integer>[] graph, boolean[] visited, Stack<Integer> stack) {
        visited[root] = true;
        if (graph[root] != null) {
            for (int nbr : graph[root]) {
                if (!visited[nbr]) {
                    dfs1(nbr, graph, visited, stack);
                }
            }
        }
        stack.push(root);
    }

    static FastScanner scn = new FastScanner();
    static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }

}