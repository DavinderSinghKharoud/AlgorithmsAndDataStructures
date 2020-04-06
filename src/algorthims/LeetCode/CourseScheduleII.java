
/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
 * */

import java.util.*;

public class CourseScheduleII {
    
	public static int[] findOrder(int numCourses, int[][] prerequisites) {
		List<Integer> result = new ArrayList<>();
		
		Map<Integer, List<Integer> > map = new HashMap<>();
		Queue<Integer> que = new LinkedList<>();
		int[] degree = new int[numCourses];
		
		for(int[] pre: prerequisites ){
		    List<Integer> lst = map.getOrDefault( pre[1], new ArrayList<>() );
		    lst.add( pre[0] );
		    degree[pre[0]]++;
		    map.put( pre[1], lst );
		}
		
		for( int i = 0; i<degree.length; i++ ){
		    if( degree[i] == 0 ) que.add( i );
		}
		
		
		int count = 0;
		while( !que.isEmpty() ){
		    int cur = que.poll();
		    count ++;
		    result.add(cur);
		    
		    if( !map.containsKey(cur) ) continue;
		    
		    for(int neig: map.get(cur) ){
			degree[neig] --;
			
			if( degree[neig] == 0 ){
			    que.add( neig );
			}
		    }
		}
		
		if( count != numCourses) return new int[0];
		
		int[] arr = new int[result.size()];
		
		for(int i = 0; i<result.size(); i++ ){
		    arr[i] = result.get(i);
			
		}
	    
	    
		return arr;
	}
	
	public static void main (String[] args) {
		      
		int[] res = findOrder( 4, new int[][] {
		    {1,0}, {2,0}, {3,1}, {3,2}
		}  );
		
		for( int num: res ){
		    System.out.println(num);
		}
	}
}

