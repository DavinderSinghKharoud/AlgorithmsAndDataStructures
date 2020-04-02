package algorthims.LeetCode;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 *
 * Example 1:
 *
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 */

import java.util.*;

public class MergeIntervals {
    
     public static int[][] merge(int[][] intervals) {
		 List<int[]> lst = new ArrayList<>();
		 int len = intervals.length;
		 if( len <= 1) return intervals;

		 Arrays.sort(intervals, new Comparator<int[]>() {
			 @Override
			 public int compare(int[] o1, int[] o2) {
				 return o1[0] - o2[0];
			 }
		 });



		 int index = 1;
		 int []tem = new int[2];
		 tem[0] = intervals[0][0];
		 tem[1] = intervals[0][1];

		 while( index < len ){

			 if( tem[1] >= intervals[index][0]){
				 tem[1] = ( intervals[index][1] > tem[1] ) ? intervals[index][1] : tem[1];

			 }else{
				 int[] copy = new int[2];
				 copy[0] = tem[0];
				 copy[1] = tem[1];
				 lst.add( copy );
				 tem[0] = intervals[index][0];
				 tem[1] = intervals[index][1];

			 }
			 if( index == len - 1){
				 lst.add( tem );
			 }
			 index++;
		 }

		 int[][] result = new int[lst.size()][2];
		 for( int i = 0; i<lst.size(); i++ ){
			 result[i][0] = lst.get(i)[0];
			 result[i][1] = lst.get(i)[1];
		 }

		 return result;
        

    }
    
    public static void main(String[] args ) {
	int[][] result = merge( new int[][]{
	    {1,4},{0,4}
	});
	
	for( int i = 0; i<result.length; i++ ){
	    for( int j = 0; j<result[0].length; j++ ){
		System.out.print( result[i][j] +"  " );
	    }
	    System.out.println();
	}
    }
}
