package Algorithms.InterviewBit;

import java.util.*;


public class LargestDistanceBetweenNodesOfATree {

	static int ans = 1;

	/**
	 * If we start BFS from any node x and find a node with the longest distance from x, it must be an end point of the longest path. It can be proved using contradiction. So our algorithm reduces to simple two BFSs. First BFS to find an end point of the longest path and second BFS from this end point to find the actual longest path.
	 * @param lst
	 * @return
	 */
	//O(n) time and space complexity
    public static int solve(List<Integer> lst) {

		int len = lst.size();
		if( len == 0 ) return 0;
		Node root = null;
		
		Map<Integer, Node > map = new HashMap<>();
		
		for( int index = 0; index < len; index++ ){
			map.put(index, new Node(index));
		}

        for (int index = 0; index < len; index++) {
            int parent = lst.get(index);
            if( parent == -1 ) {
				root = map.get(index);
				continue;
			}
            map.get(parent).neighbours.add(map.get(index));
            map.get(index).neighbours.add(map.get(parent));
        }

        return findLongestDistance( root, lst.size() );
    }

	private static int findLongestDistance(Node root, int size) {

    	Pair longestFromRoot = bfs(root, size);

    	Pair longestFromNode = bfs( longestFromRoot.node, size);

    	return longestFromNode.distance;
	}

	private static Pair bfs(Node root, int size) {
		Map<Node, Integer> visited = new HashMap<>();

    	Queue<Node> queue = new LinkedList<>();
		queue.add(root);
    	visited.put(root, 0);

    	while ( !queue.isEmpty() ){
    		Node curr = queue.poll();

    		for(Node neighbor: curr.neighbours ){
    			if( !visited.containsKey(neighbor) ){
    				queue.add(neighbor);
    				visited.put(neighbor, visited.get(curr) + 1);
				}
			}
		}

    	int maxDis = 0;
    	Node longestNode = root;

    	for(Node key: visited.keySet() ){
    		if( visited.get(key) > maxDis ){
    			maxDis = visited.get(key);
    			longestNode = key;
			}
		}

    	return new Pair( longestNode, maxDis );
	}

	static class Pair{
    	Node node;
    	int distance;
    	public Pair( Node node, int distance ){
    		this.node = node;
    		this.distance = distance;
		}
	}


	//O(n) time and space complexity
	public static int findDiameter( List<Integer> lst ){

		List<int[]> map = new ArrayList<>();
		for (int index = 0; index < lst.size(); index++) {
			map.add(new int[2]);
		}

		int res = 0;
		for (int index = lst.size() - 1; index > 0; index--) {

			int element = lst.get(index);
			int[] parent = map.get(element);
			int currentLength = map.get(index)[0] + 1;

			parent[1] = Math.max( parent[1], parent[0] + currentLength);
			parent[0] = Math.max(parent[0], currentLength);

			res = Math.max( res, parent[1]);
		}
		return res;
	}

    public static void main(String[] args) {

		System.out.println( findDiameter(Arrays.asList(-1, 0, 0, 0, 3)));

    }
    
    static class Node{
		int val;
		List<Node> neighbours;
		
		public Node(int val){
			this.val = val;
			neighbours = new ArrayList<>();
		}
	}
}
