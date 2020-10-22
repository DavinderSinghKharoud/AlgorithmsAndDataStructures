package algorthims.InterviewBit;

import java.util.*;

/**
 * Given an directed graph having A nodes. A matrix B of size M x 2 is given which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
 *
 * Find whether the graph contains a cycle or not, return 1 if cycle is present else return 0.
 *
 * NOTE:
 *
 * The cycle must contain atleast two nodes.
 * There are no self-loops in the graph.
 * There are no multiple edges between two nodes.
 * The graph may or may not be connected.
 * Nodes are numbered from 1 to A.
 * Your solution will run on multiple test cases. If you are using global variables make sure to clear them.
 *
 *
 * Problem Constraints
 * 2 <= A <= 105
 *
 * 1 <= M <= min(200000,A(A-1))
 *
 * 1 <= B[i][0], B[i][1] <= A
 *
 *
 *
 * Input Format
 * The first argument given is an integer A representing the number of nodes in the graph.
 *
 * The second argument given a matrix B of size M x 2 which represents the M edges such that there is a edge directed from node B[i][0] to node B[i][1].
 *
 *
 *
 * Output Format
 * Return 1 if cycle is present else return 0.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = 5
 *  B = [  [1, 2]
 *         [4, 1]
 *         [2, 4]
 *         [3, 4]
 *         [5, 2]
 *         [1, 3] ]
 * Input 2:
 *
 *  A = 5
 *  B = [  [1, 2]
 *         [2, 3]
 *         [3, 4]
 *         [4, 5] ]
 *
 *
 * Example Output
 * Output 1:
 *
 *  1
 * Output 2:
 *
 *  0
 *
 *
 * Example Explanation*
 * Explanation 1:
 *
 *  The given graph contain cycle 1 -> 3 -> 4 -> 1 or the cycle 1 -> 2 -> 4 -> 1
 * Explanation 2:
 *
 *  The given graph doesn't contain any cycle.
 */
public class CycleInDirectedGraph {

    public static int solve(int numOfNodes, ArrayList<ArrayList<Integer>> lst) {
		
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		for(ArrayList<Integer> curr: lst ){
			List<Integer> temp = map.getOrDefault( curr.get(0), new ArrayList<>() );
			temp.add(curr.get(1));
			map.put(curr.get(0), temp);
		}
		
		int[] visited = new int[numOfNodes];
		
		for( int node = 1; node <= numOfNodes; node++ ){
			if( traverse(map, visited, node) ){
				return 1;
			}
		}
		
		return 0;
    }

    // 1 if visited, 0 if not visited and, -1 if it is in processed
    public static boolean traverse( Map<Integer, List<Integer>> map, int[] visited, int node ){
		
		if( visited[node - 1] == 1) return false;
		
		if(visited[node - 1] == -1 ) return true;
		visited[node - 1] = -1;
		
		List<Integer> lst = map.get(node);
		boolean isCycle = false;
		if( lst != null ){
			for(int children: lst){
				isCycle |= traverse(map, visited, children);
			}
		}
		
		visited[node - 1] = 1;
		return isCycle;
		
	}
    public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
		ArrayList<Integer> curr1 = new ArrayList<>();
		curr1.add(1);curr1.add(2);
		ArrayList<Integer> curr2 = new ArrayList<>();
		curr2.add(4);curr2.add(1);
		ArrayList<Integer> curr3 = new ArrayList<>();
		curr3.add(2);curr3.add(4);
		ArrayList<Integer> curr4 = new ArrayList<>();
		curr4.add(3);curr4.add(4);
		ArrayList<Integer> curr5 = new ArrayList<>();
		curr5.add(5);curr5.add(2);
		ArrayList<Integer> curr6 = new ArrayList<>();
		curr6.add(1);curr6.add(3);

		lst.add(curr1);lst.add(curr2);lst.add(curr3);lst.add(curr4);lst.add(curr5);lst.add(curr6);
		System.out.println( solve(5, lst));
    }
}
