package InterviewBit;

import java.util.*;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 2 3 ... n.
 *
 * Make sure the combinations are sorted.
 *
 * To elaborate,
 *
 * Within every entry, elements should be sorted. [1, 4] is a valid entry while [4, 1] is not.
 * Entries should be sorted within themselves.
 * Example :
 * If n = 4 and k = 2, a solution is:
 *
 * [
 *   [1,2],
 *   [1,3],
 *   [1,4],
 *   [2,3],
 *   [2,4],
 *   [3,4],
 * ]
 */
public class Combinations {

    public static ArrayList<ArrayList<Integer>> combine(int n, int k) {
		
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		int[] arr = new int[n];
		
		for( int count = 1; count <= n; count++ ){
			arr[count - 1 ] = count;
		}
		
		traverse( arr, k, new ArrayList<>(), res, 0 );
		return res;
    }
    
    public static void traverse( int[] arr, int k, ArrayList<Integer> curr, ArrayList<ArrayList<Integer>> res, int start ){
			
		if( k == 0 ){
			res.add( new ArrayList<>(curr));
			return;
		}
		
		for( int index = start; index < arr.length; index++ ){
			curr.add(arr[index]);
			traverse( arr, k - 1, curr, res, index + 1 );
			curr.remove( curr.size() - 1);
		}
	}
    public static void main(String[] args) {

		System.out.println( combine(4, 2));
    }
}
