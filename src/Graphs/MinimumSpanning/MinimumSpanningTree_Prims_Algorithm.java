package Graphs.MinimumSpanning;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 */
public class MinimumSpanningTree_Prims_Algorithm {

    //prims algorithm helps to make sure all the nodes are connected and with the minimum weight
    //while dijikstra just give the shortest path from one node to another

    /**   5     5
     * 1 ----*-----3
     *  \----------/
     *        9
     * over the example above shortest path between 1 and 3 is 9, but it is not connecting all the nodes
     * while if we connect the node in the middle, the path becomes of 10
     */
    //one bonus
    //we must need to connect all the nodes

    public static int networkDelayTime(int[][] times, int N, int K) {
        boolean[] check = new boolean[N];
        int ans = 0;

        ArrayList[] adj = new ArrayList[N];

        for(int i = 0; i < N; i++){
            adj[i] = new ArrayList<>();
        }
        //adding nodes as in adjency list
        for (int t[] : times) {
            adj[t[0] - 1].add(new int[]{
                    t[1] - 1, t[2]
            });
        }

        ans = helper( K - 1, check, adj );

        for( boolean visited: check ){
            if( !visited ){
                return -1;
            }
        }

        return ans;
    }

    private static int helper( int source, boolean []check,  ArrayList<int []>[] adj ){

        int max = 0;
        PriorityQueue<int[]> min_heap = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        min_heap.add( new int[]{ source, 0 });

        while( !min_heap.isEmpty() ){

            int[] node = min_heap.remove();

            //to skip the duplicates

            if( check[ node[0] ] ){
                continue;
            }

            check[ node[0] ] = true;

            max = node[1];

            for( int []neighbor: adj[ node[0] ]){
                min_heap.add(  new int[]{
                        neighbor[0], neighbor[1] + node[1]
                });
            }

        }

        return max;

    }

    public static void main(String[] args) {
        System.out.println(networkDelayTime(new int[][]{
                {2, 1, 1}, {2, 3, 1}, {3, 4, 1}
        }, 4, 2));
    }
}
