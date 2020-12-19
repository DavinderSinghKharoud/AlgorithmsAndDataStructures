package Algorithms.HackerRank;

import java.util.*;

public class ComponentsInAGraph {

    //O(E + V) time and space complexity ( May throw runtime exception with large number of vertices )
    static int[] componentsInGraph(int[][] gb) {

        if (gb == null) return new int[]{0, 0};
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] edge : gb) {
            List<Integer> lst1 = map.getOrDefault(edge[0], new ArrayList<>());
            lst1.add(edge[1]);
            map.put(edge[0], lst1);

            List<Integer> lst2 = map.getOrDefault(edge[1], new ArrayList<>());
            lst2.add(edge[0]);
            map.put(edge[1], lst2);
        }

        int[] res = new int[2];
        res[0] = 30001;

        Set<Integer> visited = new HashSet<>();

        for (int vertex : map.keySet()) {
            if (!visited.contains(vertex)) {
                int total_vertices = traverse(map, vertex, visited);

                res[0] = Math.min(res[0], total_vertices);
                res[1] = Math.max(res[1], total_vertices);
            }
        }

        return res;

    }

    public static int traverse(Map<Integer, List<Integer>> map, int vertex, Set<Integer> visited) {

        visited.add(vertex);

        int total = 1;

        for (int children : map.get(vertex)) {
            if (!visited.contains(children)) {
                total += traverse(map, children, visited);
            }
        }

        return total;

    }


    public static void main(String[] args) {

        int[] res = componentsInGraph2(new int[][]{{1, 6}, {2, 7}, {3, 8}, {4, 9}, {2, 6},});

        System.out.println(res[0] + ", " + res[1]);
    }

    //Union Find
    static int[] componentsInGraph2(int[][] gb) {
        
        Map<Integer, Node> map = new HashMap<>();
        
        for(int[] edge: gb ){
			if( !map.containsKey(edge[0] ) ){
				Node curr = new Node( edge[0], 1);
				curr.parent = curr;
				map.put(edge[0], curr);
			}
			if( !map.containsKey(edge[1] ) ){
				Node curr = new Node( edge[1], 1);
				curr.parent = curr;
				map.put(edge[1], curr);
			}
			
			Node node1 = map.get( edge[0] );
			Node node2 = map.get( edge[1] );
			
			Node parent1 = findParent( node1 );
			Node parent2 = findParent( node2 );
			
			if( parent1 == parent2 ) continue;
			
			if( parent1.size >= parent2.size ){
				parent1.size = parent1.size + parent2.size;
				parent2.size = 0;
				parent2.parent = parent1;
			}else{
				parent2.size = parent1.size + parent2.size;
				parent1.size = 0;
				parent1.parent = parent2;
			}
			
		}
		
		int[] res = new int[2];
		res[0] = Integer.MAX_VALUE;
		
		for( int key: map.keySet() ){
			Node node = map.get(key);

			if( node.size > 0 ){
                res[0] = Math.min( res[0], node.size );
            }
			res[1] = Math.max( res[1], node.size );
		}

		return res;
    }
    
    public static Node findParent( Node node ){
		Node parent = node.parent;
		if( parent == node ) return node;
		
		node.parent = findParent(parent);
		return node.parent;
	}

    static class Node{
        Node parent;
        int val;
        int size;
        
        public Node( int val, int size ){
			this.val = val;
			this.size = size;
		}
    }


}
