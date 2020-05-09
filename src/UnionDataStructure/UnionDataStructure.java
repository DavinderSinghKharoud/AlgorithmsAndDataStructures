import java.util.*;

public class UnionDataStructure {
	
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
	public Node findSet( Node node ){
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
	
	public static void main (String[] args) {
		UnionDataStructure obj = new UnionDataStructure();
		obj.makeSet( 1 );
		obj.makeSet( 2 );
		obj.makeSet( 3 );
		obj.makeSet( 4 );
		obj.makeSet( 5 );
		
		obj.union( 1, 2 );
		obj.union( 3, 4 );
		obj.union( 4, 5 );
		
		System.out.println( obj.findSet_representative( 2 ));
		System.out.print( obj.findSet_representative( 5 ));
	}
}

