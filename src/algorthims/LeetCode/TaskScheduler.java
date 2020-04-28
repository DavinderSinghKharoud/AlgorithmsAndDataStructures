import java.util.*;
/*
 Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks. Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

 

Example:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 */


public class TaskScheduler {
	
	public static int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int cycles = 0;
        
        for( char c: tasks ){
			map.put( c, map.getOrDefault( c, 0 ) + 1);
		}
		
		//setting up max heap
		PriorityQueue<Integer> max_heap = new PriorityQueue<>( (a, b) -> b - a );
		
		max_heap.addAll( map.values() );
		
		while( !max_heap.isEmpty() ) {
			
			List<Integer> lst = new ArrayList<>();
			
			for( int i = 0; i<n + 1; i++ ){
				if( !max_heap.isEmpty() ){	
					lst.add( max_heap.remove() );
				}
			}
			
			for( int num: lst ){
				if( --num > 0 ){
					max_heap.add( num );
				}
			}
			
		
			cycles += max_heap.isEmpty() ? lst.size(): n + 1;
			 
		 
		}
		
		return cycles;
        
    }
    
	public static void main (String[] args) {
		System.out.println( leastInterval( new char[]{
			'A', 'A', 'A', 'B', 'B', 'B'
		}, 2));
	}
}

