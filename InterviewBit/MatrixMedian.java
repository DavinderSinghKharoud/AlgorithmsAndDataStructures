package InterviewBit;

import java.util.*;
public class MatrixMedian {

	/**
	 * Given a matrix of integers FindGreatestCommonDivisor of size N x M in which each row is sorted.
	 *
	 * Find an return the overall median of the matrix FindGreatestCommonDivisor.
	 *
	 * Note: No extra memory is allowed.
	 *
	 * Note: Rows are numbered from top to bottom and columns are numbered from left to right.
	 *
	 *
	 *
	 * Input Format
	 *
	 * The first and only argument given is the integer matrix FindGreatestCommonDivisor.
	 * Output Format
	 *
	 * Return the overall median of the matrix FindGreatestCommonDivisor.
	 * Constraints
	 *
	 * 1 <= N, M <= 10^5
	 * 1 <= N*M  <= 10^6
	 * 1 <= FindGreatestCommonDivisor[i] <= 10^9
	 * N*M is odd
	 * For Example
	 *
	 * Input 1:
	 *     FindGreatestCommonDivisor = [   [1, 3, 5],
	 *             [2, 6, 9],
	 *             [3, 6, 9]   ]
	 * Output 1:
	 *     5
	 * Explanation 1:
	 *     FindGreatestCommonDivisor = [1, 2, 3, 3, 5, 6, 6, 9, 9]
	 *     Median is 5. So, we return 5.
	 *
	 * Input 2:
	 *     FindGreatestCommonDivisor = [   [5, 17, 100]    ]
	 * Output 2:
	 *     17 ``` Matrix=
	 * @param lst
	 * @return
	 */

    public static int findMedian(List<List<Integer>> lst) {
		//Time complexity O( row * log(c) ) and Space complexity is O(1)
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		
		int len1 = lst.size();
		int len2 = lst.get(0).size();

		for( List<Integer> row: lst ){
			max = Math.max(max, row.get( len2 - 1));
			min = Math.min(min, row.get(0));
		}
		int desired = ( len1 * len2 + 1 )/ 2;
		
		while( min < max ){
			int mid = min + ( max - min )/2;
			int place = 0;
			int get = 0;
			
			//Go through all the rows to find the numbers count that are greater or lower than mid
			for( int index = 0; index < len1; index++ ){
				get = Collections.binarySearch( lst.get(index), mid);
				
				// If element is not found in the array the 
				// binarySearch() method returns 
				// (-(insertion_point) - 1). So once we know 
				// the insertion point we can find elements 
				// Smaller than the searched element by the 
				// following calculation 
				if( get < 0 ){
					get = Math.abs(get) - 1;
				}else{
					while( get < lst.get(index).size() && lst.get(index).get(get) == mid){
						//skip the duplicates
						get++;
					}
				}
				
				place += get;
			}
			
			if( place < desired ){
				min = mid + 1;
			}else{
				max = mid;
			}
		}
		
		return min;
        
    }
    public static void main(String[] args) {

    	List<List<Integer>> root = new ArrayList<>();
		root.add(Arrays.asList(5));
		root.add(Arrays.asList(5));
    	root.add(Arrays.asList(5));
		root.add(Arrays.asList(4));
		root.add(Arrays.asList(3));

		System.out.println( findMedian(root));
    }
}
