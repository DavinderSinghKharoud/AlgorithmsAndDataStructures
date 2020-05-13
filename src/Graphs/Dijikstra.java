
import java.util.*;

/**
 * 743. Network Delay Time
 * Medium
 *
 * 1281
 *
 * 211
 *
 * Add to List
 *
 * Share
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 */
public class Dijikstra {

    public static int networkDelayTime(int[][] times, int N, int K) {

    	//to check if node is processed or not
        boolean[] check = new boolean[N];
        //to get the minimum distance
        int[] distance = new int[N];
        Arrays.fill( distance, Integer.MAX_VALUE );
        //setting the distance of starting node
        distance[K-1] = 0;
		int ans = 0;

        ArrayList[] adj = new ArrayList[N];

		for(int i = 0; i < N; i++){
			adj[i] = new ArrayList<>();
		}
        //adding nodes as in adjacency list
        for (int t[] : times) {
            adj[t[0] - 1].add(new int[]{
                    t[1] - 1, t[2]
            });
        }

		ans = helper( K - 1, check, adj, distance );
		
		for( boolean visited: check ){
			if( !visited ){
				return -1;
			}
		}

		return ans;
    }
    
    private static int helper(int source, boolean[] check, ArrayList<int[]>[] adj, int[] distance){
		
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

				if( neighbor[1] + node[1] < distance[ neighbor[0] ] ){
					distance[ neighbor[0] ] = neighbor[1] + node[1];
					min_heap.add(  new int[]{
							neighbor[0], neighbor[1] + node[1]
					});
				}

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

