package Graphs.MinimumSpanning;

import java.util.HashMap;
import java.util.*;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 *
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 */
public class RedundantConnection_KruskalAlgorithm {

    public static int[] findRedundantConnection(int[][] edges) {
        
        //we do not need to sort, because all the edges have only one weight
        UnionDataStructure obj = new UnionDataStructure();
        
        int nodes = edges.length;
        Set<int[]> set =  new HashSet<>();
        
        for( int i = 1; i<=nodes; i++ ){
			obj.makeSet( i );
		}
		
        for( int[] edge: edges ){
			int edge1 = edge[0];
			int edge2 = edge[1];
			set.add( edge );
			
			if( obj.findSet_representative( edge1 ) != obj.findSet_representative( edge2 ) ){
				set.remove( edge );
				obj.union( edge1, edge2 );
			}
		}

        Iterator<int[]> iterator = set.iterator();

        return iterator.next();
    }
    public static void main(String[] args) {

        int[] res = findRedundantConnection( new int[][]{
                {1, 2}, {1, 3}, {2, 3}
        });

        System.out.println( res[0] +"," + res[1]);
    }
}
 class  UnionDataStructure {

    Map<Long, Node> map = new HashMap<>();

    class Node {
        long data;
        int rank;
        Node parent;
    }

    //create a set with only one element
    public void makeSet( long data ){
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put( data, node );
    }

    public void union( long data1, long data2 ){
        Node node1 = map.get( data1 );
        Node node2 = map.get( data2 );

        Node parent1 = findSet( node1 );
        Node parent2 = findSet( node2 );

        //both have same parent
        if( parent1.data == parent2.data ){
            return;
        }

        //else whoever rank is higher becomes a parent
        if( parent1.rank >= parent2.rank ){
            //increment only when both sets have same rank
            parent1.rank = ( parent1.rank == parent2.rank ) ? parent1.rank + 1: parent1.rank;
            parent2.parent = parent1;

        }else{
            parent1.parent = parent2;
        }
    }

    //Find the representative recursively and does path compression as well
    public Node findSet(Node node ){
        Node parent = node.parent;
        //it means no one is parent
        if( parent == node ){
            return parent;
        }
        //do compression
        node.parent = findSet( parent );
        return node.parent;
    }

    //find the represenetative of this set
    public long findSet_representative( long data ){
        return findSet( map.get(data) ).data;
    }

}
