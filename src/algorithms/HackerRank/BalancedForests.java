package algorithms.HackerRank;

import java.util.*;

/**
 * Greg has a tree of nodes containing integer data. He wants to insert a node with some non-zero integer value somewhere into the tree. His goal is to be able to cut two edges and have the values of each of the three new trees sum to the same amount. This is called a balanced forest. Being frugal, the data value he inserts should be minimal. Determine the minimal amount that a new node can have to allow creation of a balanced forest. If it's not possible to create a balanced forest, return -1.
 * For example, you are given node values  and . It is the following tree:
 * image
 * The blue node is root, the first number in a node is node number and the second is its value. Cuts can be made between nodes  and  and nodes  and  to have three trees with sums ,  and . Adding a new node  of  to the third tree completes the solution.
 * Function Description
 * Complete the balancedForest function in the editor below. It must return an integer representing the minimum value of that can be added to allow creation of a balanced forest, or  if it is not possible.
 * balancedForest has the following parameter(s):
 * c: an array of integers, the data values for each node
 * edges: an array of 2 element arrays, the node pairs per edge
 * Input Format
 * The first line contains a single integer, , the number of queries.
 * Each of the following  sets of lines is as follows:
 * The first line contains an integer, , the number of nodes in the tree.
 * The second line contains  space-separated integers describing the respective values of , where each denotes the value at node .
 * Each of the following  lines contains two space-separated integers,  and , describing edge  connecting nodes  and .
 * Constraints
 *
 *
 *
 * Each query forms a valid undirected tree.
 * Subtasks
 * For  of the maximum score:
 *
 *
 * For  of the maximum score:
 *
 *
 * Output Format
 * For each query, return the minimum value of the integer . If no such value exists, return  instead.
 * Sample Input
 * 2
 * 5
 * 1 2 2 1 1
 * 1 2
 * 1 3
 * 3 5
 * 1 4
 * 3
 * 1 3 5
 * 1 3
 * 1 2
 * Sample Output
 * 2
 * -1
 * Explanation
 * We perform the following two queries:
 * The tree initially looks like this:
 * image
 * Greg can add a new node  with  and create a new edge connecting nodes  and . Then he cuts the edge connecting nodes  and  and the edge connecting nodes  and . We now have a three-tree balanced forest where each tree has a sum of .
 * image
 * In the second query, it's impossible to add a node in such a way that we can split the tree into a three-tree balanced forest so we return .
 */
public class BalancedForests {
    public static void main(String[] args) {
        long t1 = System.nanoTime();
        Scanner scanner = new Scanner(System.in);
        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int[] c = new int[n];
            String[] cItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            for (int i = 0; i < n; i++) {
                int cItem = Integer.parseInt(cItems[i]);
                c[i] = cItem;
            }
            int[][] edges = new int[n - 1][2];
            for (int i = 0; i < n - 1; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }
            long result = balancedForest(c, edges);

            System.out.println("ANSWER: " + result);
        }
        scanner.close();
        long t2 = System.nanoTime();
    }


    static class Node {
        long cost;
        boolean visited = false, visited2 = false;
        ArrayList<Integer> adjacent = new ArrayList<>();

        @Override
        public String toString() {
            return "Node{" +
                    "cost=" + cost +
                    ", v1=" + visited +
                    ", visited2=" + visited2 +
                    ", adjacent=" + adjacent +
                    '}';
        }

        public Node(long cost) {
            this.cost = cost;
        }
    }

    
    static long min, sum;
    static Set<Long> encounteredSum;
    static Set<Long> sumUpToRoot;
    
    private static long balancedForest(int[] node_values, int[][] edges) {
		encounteredSum = new HashSet<>();
		sumUpToRoot =  new HashSet<>();
		
		
		List<Node> nodes = new ArrayList<>();
        for (int node_value : node_values) {
            nodes.add(new Node(node_value));
        }

        for (int[] edge : edges) {
            int a = edge[0] - 1;
            int b = edge[1] - 1;

            nodes.get(a).adjacent.add(b);
            nodes.get(b).adjacent.add(a);
        }
		
		min = sum = dfs(0, nodes);
		solve(0, nodes );
		
		return ( min == sum )? -1: min;

    }

    private static void solve(int index, List<Node> nodes) {
		
		Node curr = nodes.get(index);
		if( curr.visited2 ) return;
		curr.visited2 = true;
		
		//We will use these after
		long firstCase = 3 * curr.cost - sum;
		long secondCase = ( sum - curr.cost )/2 - curr.cost;
		
		//1] if current tree sum is equal to total/2 then the answer would be the same cost
		if( sum % 2 == 0 && curr.cost == (sum/2) ) min = Math.min( min, curr.cost);
		
		//2] if encountered sums contain same cost as current node then the answer would be the remaining
		if( encounteredSum.contains( curr.cost ) ){
			if( firstCase >= 0 ) min = Math.min( min, firstCase );
		}
		
		//3] 
		if( encounteredSum.contains( sum - 2 * curr.cost ) || sumUpToRoot.contains( sum - curr.cost ) ){
			if( firstCase >= 0 ) min = Math.min( min, firstCase );
		}
		
		//4] if remaining sum is even, encountered sum contains remaining/2, and sumUpToRoot contains ( sum + cost )/2
		if( (sum - curr.cost ) % 2 == 0 ){
			
			if( encounteredSum.contains( (sum - curr.cost )/2 ) ||
			 sumUpToRoot.contains( (sum + curr.cost)/2 ) ){
				 if( secondCase >= 0 ) min = Math.min( min, secondCase );
			 }
		}
		
		sumUpToRoot.add(curr.cost);
		
		for(int adjacent: curr.adjacent ){
			solve( adjacent, nodes );
		}
		//After traversing all the childrens
		sumUpToRoot.remove(curr.cost);
		encounteredSum.add( curr.cost );
		
    }

    private static long dfs(int index, List<Node> nodes) {
		Node node = nodes.get(index);
		if( node.visited ) return 0;
		node.visited = true;
		
		for( int children: node.adjacent ){
			node.cost += dfs( children, nodes );
		}
		
		return node.cost;
		
    }


}
