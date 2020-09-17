package algorthims.InterviewBit;

import java.util.*;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 *
 * Each number in C may only be used once in the combination.
 *
 *  Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * Example :
 *
 * Given candidate set 10,1,2,7,6,1,5 and target 8,
 *
 * A solution set is:
 *
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 */
public class CombinationSumII {

    public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> lst, int target) {
		
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		ArrayList<Integer> curr = new ArrayList<>();
		Collections.sort(lst);
		traverse( lst, target, res, curr, 0 );
		return res;
		
    }
    
    public static void traverse( ArrayList<Integer> lst, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> curr, int start ){
		if( target == 0 ){
			res.add( new ArrayList<>(curr) );
			return;
		}
		
	
		for( int index = start; index < lst.size(); index++ ){
			if( index == start || lst.get(index) != lst.get(index - 1) ){
				curr.add( lst.get(index) );
				traverse( lst, target - lst.get(index), res, curr, index + 1 );
				curr.remove( curr.size() - 1);
			}
		}
	}
    public static void main(String[] args) {

    	ArrayList<Integer> lst = new ArrayList<>();
    	lst.add(10);lst.add(1);
    	lst.add(2);lst.add(7);
    	lst.add(6);lst.add(1);
    	lst.add(5);
		System.out.println( combinationSum(lst, 8));
    }
}
