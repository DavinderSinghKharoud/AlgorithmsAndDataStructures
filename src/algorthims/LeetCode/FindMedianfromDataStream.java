package algorthims.LeetCode;

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
	
	 List<Integer> lst;
	
    public FindMedianfromDataStream() {
        lst = new ArrayList<>();

    }
    
    public void addNum(int num) {
        lst.add( num );
    }
    
    public double findMedian() {
        Collections.sort( lst );
		int len = lst.size();
		//if it is even
        if( (lst.size() & 1) == 0 ){
			return (double) ( lst.get( len/2 ) + lst.get( len/2 - 1 ) ) / 2;
		}

		//if it is odd
		return (double) lst.get( len/2 );
    }
    
	public static void main (String[] args) {

        FindMedianfromDataStream findMedianfromDataStream = new FindMedianfromDataStream();
        findMedianfromDataStream.addNum(2);
        System.out.println( findMedianfromDataStream.findMedian() );

        findMedianfromDataStream.addNum(3);
        System.out.println( findMedianfromDataStream.findMedian() );

        findMedianfromDataStream.addNum(4);
        System.out.println( findMedianfromDataStream.findMedian() );
	}
}

