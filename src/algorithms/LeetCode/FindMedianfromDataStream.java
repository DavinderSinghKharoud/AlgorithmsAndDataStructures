package algorithms.LeetCode;

import java.util.*;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 *
 * For example,
 * [2,3,4], the median is 3
 *
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 *
 *
 * Example:
 *
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 */
public class FindMedianfromDataStream {
	
	 //List<Integer> lst;
	//Sorting O(nlogn) time complexity and O(n) space complexity
//    public FindMedianfromDataStream() {
//        lst = new ArrayList<>();
//
//    }
//
//    public void addNum(int num) {
//        lst.add( num );
//    }
//
//    public double findMedian() {
//        Collections.sort( lst );
//		int len = lst.size();
//		//if it is even
//        if( (lst.size() & 1) == 0 ){
//			return (double) ( lst.get( len/2 ) + lst.get( len/2 - 1 ) ) / 2;
//		}
//
//		//if it is odd
//		return (double) lst.get( len/2 );
//    }
    
	public static void main (String[] args) {

        FindMedianfromDataStream findMedianfromDataStream = new FindMedianfromDataStream();
        findMedianfromDataStream.addNum2(1);
        //System.out.println( findMedianfromDataStream.findMedian() );

        findMedianfromDataStream.addNum2(2);
        //System.out.println( findMedianfromDataStream.findMedian() );

        findMedianfromDataStream.addNum2(3);
        System.out.println( findMedianfromDataStream.findMedian() );
	}

	//O(log(n) ) time complexity and O(n) space complexity

	
		//max_heap
		PriorityQueue<Integer> lowers;
		//min_heap
		PriorityQueue<Integer> highers;
	
    public FindMedianfromDataStream() {
		lowers = new PriorityQueue<>( (o1, o2) -> o2 - o1 );
		highers = new PriorityQueue<>();
    }
	public void addNum2(int num) {
    	lowers.add(num);

    	highers.add(lowers.poll());

    	if( highers.size() > lowers.size() ){
    		lowers.add(highers.poll());
		}
	}

    public void addNum(int num) {

		
		if( lowers.size() == 0 || num < lowers.peek() ){
			lowers.add( num );
		}else{
			highers.add( num );
		}
		
		rebalance( );
    }
	
	public void rebalance ( ){
		PriorityQueue<Integer> bigger = ( lowers.size() > highers.size() ) ? lowers : highers;
		PriorityQueue<Integer> smaller = ( lowers.size() > highers.size() ) ? highers : lowers;
		
		if( bigger.size() - smaller.size() >= 2 ){
			smaller.add( bigger.poll() );
		}
	}
	
    public double findMedian() {
		PriorityQueue<Integer> bigger = ( lowers.size() > highers.size() ) ? lowers : highers;
		PriorityQueue<Integer> smaller = ( lowers.size() > highers.size() ) ? highers : lowers;
		
		if( bigger.size() == smaller.size() ){
			return (double) ( bigger.peek() + smaller.peek() )/2;
		}else{
			return bigger.peek();
		}
    }
}

