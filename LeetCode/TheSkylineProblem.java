
import java.util.*;

/**
 * FindGreatestCommonDivisor city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure FindGreatestCommonDivisor), write a program to output the skyline formed by these buildings collectively (Figure FindUniqueBinaryString)
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure FindGreatestCommonDivisor are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure FindUniqueBinaryString) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. FindGreatestCommonDivisor key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.

For instance, the skyline in Figure FindUniqueBinaryString should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * **/

public class TheSkylineProblem {
	
	
	public static List<List<Integer>> getSkyline(int[][] buildings) {

		TreeMap<Integer,List<int[]>> map = new TreeMap<>();
		construct( map, buildings );
		
        PriorityQueue<int[]> heap = new PriorityQueue<>( (o1, o2) -> o2[2] - o1[2] );
        List<List<Integer>> res = new ArrayList<>();
        
        for( int key: map.keySet() ){
			
			 for( int[] building: map.get(key) ){
					
					if( building[0] == key ){
						//start of the building
						heap.offer(building);
					}else{
						//end of the building
						heap.remove( building );
					}
				}
				
				if( heap.isEmpty() ){
					//end of the building
					res.add( new ArrayList<>( Arrays.asList(key,0) ));
				}else{
					int maxHeight = heap.peek()[2];
					
					if( res.isEmpty() || res.get( res.size() - 1).get(1) != maxHeight ){
						res.add( new ArrayList<>( Arrays.asList(key, maxHeight )));
					}
				}
				
				
		}
		
		return res;
       
    }
    
    private static void construct( TreeMap<Integer, List<int[]>> map, int[][] buildings ){
		
		//construct a tree map ( in which keys always remain sorted )
		for( int[] building: buildings ){
			//it will return the list, if already present
			map.putIfAbsent( building[0], new ArrayList<>() );
			map.putIfAbsent( building[1], new ArrayList<>() );
			
			map.get( building[0] ).add( building );
			map.get( building[1] ).add( building );
			
		}
	}
    
	public static void main (String[] args) {
		
		System.out.println( getSkyline( new int[][]{
			{2,9,10}, {3,7,15}
		}));
	}
}

