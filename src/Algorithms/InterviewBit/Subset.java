package Algorithms.InterviewBit;

/**
 * Given a set of distinct integers, S, return all possible subsets.
 *
 *  Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * Also, the subsets should be sorted in ascending ( lexicographic ) order.
 * The list is not necessarily sorted.
 * Example :
 *
 * If S = [1,2,3], a solution is:
 *
 * [
 *   [],
 *   [1],
 *   [1, 2],
 *   [1, 2, 3],
 *   [1, 3],
 *   [2],
 *   [2, 3],
 *   [3],
 * ]
 */

import java.util.*;
public class Subset {

    public static ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> lst) {
		ArrayList<ArrayList<Integer>>  res = new ArrayList<>();
		Collections.sort(lst);
		traverse( res, lst, 0, new ArrayList<>() );
		return res;
    }
    
    public static void traverse( ArrayList<ArrayList<Integer>> res, ArrayList<Integer> lst, int start, ArrayList<Integer> curr ){
		
		res.add( new ArrayList<>(curr) );
		
		if( start >= lst.size() ){
			return;
		}
		
		for( int index = start; index < lst.size(); index++ ){
			curr.add( lst.get(index) );
			traverse( res, lst, index + 1, curr );
			curr.remove( curr.size() - 1 );
		}
	}
    public static void main(String[] args) {

        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(1);lst.add(2);lst.add(3);
        System.out.println( subsets(lst));
    }
}
