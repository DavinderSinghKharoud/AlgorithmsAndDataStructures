package algorthims.LeetCode;

import java.util.*;

/**
 * We have a collection of stones, each stone has a positive integer weight.
 *
 * Each turn, we choose the two heaviest stones and smash them together.  Suppose the stones have weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed;
 * If x != y, the stone of weight x is totally destroyed, and the stone of weight y has new weight y-x.
 * At the end, there is at most 1 stone left.  Return the weight of this stone (or 0 if there are no stones left.)
 */
public class LastStoneWeight {
	
	public static int lastStoneWeight(int[] stones) {
		
		PriorityQueue<Integer> pq = new PriorityQueue<>( (x,y) -> y - x );
		
		for(int stone: stones ){
		    pq.add(stone);
		}
		
		while( pq.size() > 1 ){
		    int x = pq.poll();
		    int y = pq.poll();
		    
		    if( x != y ){
			int remaining = x - y;
			
			
			pq.add( remaining );
		    }
		}
		
		
		return ( pq.size() == 0) ? 0: pq.poll();
        
	}
	
	public static void main (String[] args) {
		    
		    System.out.println( lastStoneWeight(new int[]{
			1,3
		    }));


	}
}

