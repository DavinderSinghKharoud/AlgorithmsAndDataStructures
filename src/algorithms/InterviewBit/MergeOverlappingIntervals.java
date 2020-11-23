/*
Given a collection of intervals, merge all overlapping intervals.

For example:

Given [1,3],[2,6],[8,10],[15,18],

return [1,6],[8,10],[15,18].

Make sure the returned intervals are sorted. 
 */

import java.util.*;
public class MergeOverlappingIntervals{
	
	public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
		
		if( intervals.size() == 0 ){ return intervals; };
		Collections.sort( intervals, (o1, o2) -> o1.start - o2.start );
		
		ArrayList<Interval> res = new ArrayList<>();
		res.add( intervals.get(0) );
		
		for( int index = 1; index < intervals.size(); index++ ){
			Interval curr = intervals.get(index);
			Interval prev = res.get( res.size() - 1 );
			
			if( prev.end < curr.start ){
				res.add( curr );
			}else {
				int min = Math.min( prev.start, curr.start );
				int max = Math.max( prev.end, curr.end );
				res.remove( res.size() - 1);
				res.add(new Interval( min, max ));
			}
		}
		
		return res;
    }
	public static void main (String[] args) {
		ArrayList<Interval> lst = new ArrayList<>();
		lst.add(new Interval(1, 3));
		lst.add(new Interval(2, 6));
		lst.add(new Interval(8, 10));
		lst.add(new Interval(15, 18));
		System.out.println( merge( lst ));
	}
	
	static class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }

		@Override
		public String toString() {
			return "Interval{" +
					"start=" + start +
					", end=" + end +
					'}';
		}
	}
}

