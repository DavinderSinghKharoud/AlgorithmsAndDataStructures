package InterviewBit;

import java.util.*;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 *
 * The same repeated number may be chosen from C unlimited number of times.
 *
 *  Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The combinations themselves must be sorted in ascending order.
 * CombinationA > CombinationB iff (a1 > b1) OR (a1 = b1 AND a2 > b2) OR … (a1 = b1 AND a2 = b2 AND … ai = bi AND ai+1 > bi+1)
 * The solution set must not contain duplicate combinations.
 */
public class CombinationSum {

    public static ArrayList<ArrayList<Integer>> combinationSum(ArrayList<Integer> lst, int target) {
		
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		Collections.sort(lst);
		traverse( lst, target, res, new ArrayList<>(), 0 );
		
		return res;
    }
    
    public static void traverse( ArrayList<Integer> lst, int target, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> curr, int start ){
		
		
		if( target == 0 ){
			res.add(new ArrayList<>(curr));
			return;
		}
		
		if( target < 0 ){
			return;
		}
		
		for( int index = start; index < lst.size(); index++ ){
			int num = lst.get(index);
			if( index == start || !lst.get(index).equals(lst.get(index - 1))){
				curr.add(num);
				traverse( lst, target - num, res, curr, index );
				curr.remove(curr.size() - 1);
			}
		}
	}
    public static void main(String[] args) {

    	ArrayList<Integer> lst = new ArrayList<>();
    	lst.add(8);lst.add(10);lst.add(6);lst.add(11);
    	lst.add(1);lst.add(16);lst.add(8);
		System.out.println( combinationSum(lst, 28));
    }

	public static ArrayList<ArrayList<Integer>> combinationSum2(ArrayList<Integer> a, int b) {
		ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
		if (a==null || a.size()==0) return ans;
		Collections.sort(a);
		generate(0,a,new ArrayList<>(),ans,b);

		return ans;
	}

	public static void generate(int index,ArrayList<Integer> a,ArrayList<Integer> curr,ArrayList<ArrayList<Integer>> ans,int target){
		if (target<=0){
			if (target==0){
				ans.add(new ArrayList<>(curr));
			}
			return;
		}

		for (int i=index;i<a.size();i++){
			if (i==index || !a.get(i).equals(a.get(i - 1))){
				curr.add(a.get(i));
				generate(i,a,curr,ans,target-a.get(i));
				curr.remove(curr.size()-1);}
		}
	}
}
