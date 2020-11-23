package algorithms.LeetCode;

import java.util.*;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.**/
public class CourseSchedule{
    
    //Using DFS and checking degree like, how many nodes are required
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
	Map<Integer,List<Integer>> map = new HashMap<>();
	int[] degree = new int[numCourses];
	
	for( int[] pr: prerequisites){
	    List<Integer> lst = map.getOrDefault( pr[1], new ArrayList<>());
	    lst.add( pr[0] );
	    degree[ pr[0] ]++;
	    map.put( pr[1], lst );
	}

	Queue<Integer> queue = new LinkedList<>();
	for( int i = 0; i<degree.length; i++ ){
	    if( degree[i] == 0) queue.add(i);
	}
	
	int count = 0;

	while( !queue.isEmpty() ){
	    int cur = queue.poll();
	    if( degree[cur] == 0 ) count++;
	    if( !map.containsKey(cur) ) continue;	
	    
	    for( int neig: map.get(cur) ){
		degree[neig]--;
		if( degree[neig] == 0 ) queue.add( neig );
		
	    }
	}
	
	return count == numCourses;
        
    }
    
    public static void main(String[] args) {
        
	System.out.println( canFinish( 3, new int[][] {
	    {1,0}, {0,2}
	}  ));
    }
}
