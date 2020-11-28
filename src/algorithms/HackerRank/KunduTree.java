package algorithms.HackerRank;

import java.util.*;
public class KunduTree {


	//Time complexity O( n Log(n) ) and Space complexity O(n)
    public static void main(String[] args) {
        
        Scanner sc = new Scanner( System.in );
        int limit = sc.nextInt();
        Map<Integer, Node> map = new HashMap<>();
        
        for(int count = 0; count < limit - 1; count++ ){
			
			int first = sc.nextInt();
			int second = sc.nextInt();
			char color = sc.next().charAt(0);
			if( color == 'r' ) continue;
			
			Node firstNum = map.getOrDefault( first, new Node() );
			map.put(first, firstNum);
			Node secondNum = map.getOrDefault( second, new Node() );
			map.put(second, secondNum);
			
			Node parent1 = findParent(firstNum);
			Node parent2 = findParent(secondNum);
			
			if( parent1 == parent2 ) continue;
			
			if( parent1.size >= parent2.size ){
				parent1.size += parent2.size;
				parent2.parent = parent1;
			}else{
				parent2.size += parent1.size;
				parent1.parent = parent2;
			}
			
		}
		
		Set<Node> uniqueComponents = new HashSet<>();
		
		for(int num = 1; num < limit; num++ ){
			if( !map.containsKey(num) ) continue;
			
			uniqueComponents.add( findParent( map.get(num) ));
		}
		
		long validTriplets = binomial(limit, 3);
		
		for( Node component: uniqueComponents ){
			//For the black pairs
			validTriplets -= binomial( component.size, 3); //pair of 3
			
			validTriplets -= binomial( component.size, 2) * ( limit - component.size); //pair of 2 and 1 any other node
		}

		System.out.println( validTriplets % 1_000_000_007);
		
    }
    
    static long binomial( int num, int k ){
		
		/*
		 *  n!/ k!(n - k)!
		 */
		long res = 1;
		
		for( int x = ( num - k + 1); x <= num; x++ ){ 
			res *= x;
		}
		
		long denom = 1;
		
		for( int x = k; x >= 1; x-- ){
			denom *= x;
		}
		
		return res/denom;
	}
    
    static Node findParent( Node node ){
		if( node == null ) return null;
		Node parent = node.parent;
		if( parent == node ) return node;
		
		node.parent = findParent( node.parent );
		return node.parent;
	}
    
    static class Node{
		Node parent = this;
		int size = 1;
		
	}
}
