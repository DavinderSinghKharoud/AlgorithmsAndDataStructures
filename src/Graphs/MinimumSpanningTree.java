package Graphs;

public class MinimumSpanningTree {

    private final int V;

    public MinimumSpanningTree(int vertices) {
        this.V = vertices;
    }

    void printMST(int parent[], int graph[][]) {
        System.out.println("Edge \tWeight");

        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }


    void PrimMST(int graph[][]) {

        int parent[] = new int[V];

        int key[] = new int[V];

        Boolean mstSet[] = new Boolean[V];

        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        //Including first vertex
        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {

            int u = minKey(key, mstSet);

            mstSet[u] = true;

            for (int v = 0; v < V; v++) {

                if (graph[u][v] < key[v] && mstSet[v] == false && graph[u][v] != 0) {

                    parent[v] = u;
                    key[v] = graph[u][v];

                }
            }

        }


        // print the constructed MST
        printMST(parent, graph);
    }

    //Extrack the minimum weight adjacent edge, that not included in the mstSet
    private int minKey(int[] key, Boolean[] mstSet) {

        int min = Integer.MAX_VALUE;
        int min_index = -1;

        for (int v = 0; v < V; v++) {

            if (mstSet[v] == false && key[v] < min) {

                min = key[v];
                min_index = v;
            }
        }
        return min_index;
    }


    public static void main(String[] args) {
        MinimumSpanningTree t = new MinimumSpanningTree(5);
        int graph[][] = new int[][]{
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}};

        // Print the solution
        t.PrimMST(graph);
    }
}
