package InterviewBit;

import java.util.*;

/**
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
 *
 *  Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * The subsets must be sorted lexicographically.
 * Example :
 * If S = [1,2,2], the solution is:
 *
 * [
 * [],
 * [1],
 * [1,2],
 * [1,2,2],
 * [2],
 * [2, 2]
 * ]
 */
public class SubsetsII {

    public static ArrayList<ArrayList<Integer>> subsetsWithDup(ArrayList<Integer> lst) {
		
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		//res.add(new ArrayList<>());
		Collections.sort(lst);
		traverse( lst, res, 0, new ArrayList<>() );
		
		return res;
    }
    
    public static void traverse( ArrayList<Integer> lst, ArrayList<ArrayList<Integer>> res, int start, ArrayList<Integer> curr ){
		
		res.add( new ArrayList<>(curr));
		
		if( start >= lst.size() ){
			return;
		}
		
		for( int index = start; index < lst.size(); index++ ){
			if( index == start || !lst.get(index).equals(lst.get(index - 1))){
				curr.add(lst.get(index));
				traverse( lst, res, index + 1, curr );
				curr.remove( curr.size() - 1 );
			}
		}
	}

    public static void main(String[] args) {

		ArrayList<Integer> lst = new ArrayList<>();
		lst.add(1);lst.add(2);lst.add(2);
		System.out.println( subsetsWithDup(lst));
    }

}
