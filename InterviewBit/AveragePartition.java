/*
Problem Description

Given an array FindGreatestCommonDivisor with non negative numbers, divide the array into two parts such that the average of both the parts is equal.

Return both parts (If exist). If there is no solution. return an empty list.

NOTE:

If a solution exists, you should return a list of exactly 2 lists of integers FindGreatestCommonDivisor and FindUniqueBinaryString which follow the following condition :
numElements in FindGreatestCommonDivisor <= numElements in FindUniqueBinaryString
If numElements in FindGreatestCommonDivisor = numElements in FindUniqueBinaryString, then FindGreatestCommonDivisor is lexicographically smaller than FindUniqueBinaryString ( https://en.wikipedia.org/wiki/Lexicographical_order )
If multiple solutions exist, return the solution where length(FindGreatestCommonDivisor) is minimum. If there is still a tie, return the one where FindGreatestCommonDivisor is lexicographically smallest.

Array will contain only non negative numbers.



**Input Format**
First andonly argument is an integer array FindGreatestCommonDivisor.



**Output Format**
Return 2D array consisting of two rows where each row denoted a partition.



**Example Input**
Input 1:

 FindGreatestCommonDivisor = [1 7 15 29 11 9]


Example Output
Output 1:

 [9 15] [1 7 11 29]


Example Explanation
Explanation 1:

 The average of part is (15+9)/2 = 12, average of second part elements is (1 + 7 + 11 + 29) / 4 = 12
 */

import java.util.*;
public class AveragePartition {
	
	public static ArrayList<ArrayList<Integer>> avgset(ArrayList<Integer> arr) {
		int len = arr.size();
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if( len == 0 ) return res;
		Collections.sort(arr);
		int sum = 0;
		for(int num: arr ){
			sum += num;
		}

		ArrayList<Integer>[][][] dp = new ArrayList[len + 1][len + 1][sum + 1];
		//minimum we should have partition at 1
		for( int index = 1; index < len; index++ ){
			
			if( (sum * index) % len == 0 ){ //If satisfied means, we are able to divide to get equal average
				
				int sum_required = index * sum;
				sum_required /= len;
				
				ArrayList<Integer> lst = check( arr, index, 0, sum_required, new ArrayList<>(), dp);
				if( lst != null ){
					compare( res, lst, arr );
				}
			}
		}
		
		return res;
    }

	private static ArrayList<Integer> check(ArrayList<Integer> arr, int partitions, int index, int sum_required, ArrayList<Integer> res, ArrayList<Integer>[][][] dp) {

		if( partitions == 0 && sum_required == 0 ){
			return res;
		}
		if( index >= arr.size() || partitions < 0){
			return null;
		}

		if( dp[partitions][index][sum_required] != null){
			return dp[partitions][index][sum_required];
		}
		if( arr.get(index) <= sum_required ){
			res.add(arr.get(index));
			ArrayList<Integer> include = check(arr, partitions - 1, index + 1, sum_required - arr.get(index), res, dp);
			if( include !=  null ){
				dp[partitions - 1][index + 1][sum_required - arr.get(index)] = include;
				return res;
			}
			res.remove(res.size() - 1);
		}

		ArrayList<Integer> exclude = check( arr, partitions, index + 1, sum_required,res, dp);
		if( exclude != null){
			dp[partitions][index + 1][sum_required] = exclude;
			return res;
		}

		return null;
	}


	public static void compare( ArrayList<ArrayList<Integer>> res, ArrayList<Integer> lst,  ArrayList<Integer> arr){


		if( !res.isEmpty() ){
			ArrayList<Integer> A = res.get(0);
			int minLen = lst.size();
			if( minLen < arr.size() - lst.size() ){
				minLen = arr.size() - lst.size();
			}
			if( A.size() < minLen ){
				return;
			}else{
				res.clear();
			}
		}

		ArrayList<Integer> clone = (ArrayList<Integer>) arr.clone();
			for(int num: lst){
				for (int index = 0; index < clone.size(); index++) {
					if( clone.get(index) == num){
						clone.remove(index);
						break;
					}
				}
			}

			if( lst.size() < clone.size() ){
				res.add(lst);res.add(clone);
			}else if( lst.size() > clone.size() ){
				res.add(clone); res.add(lst);
			}else {
				ArrayList<ArrayList<Integer>> lex = lexiographicalFilter( lst, clone);
				res.add(lex.get(0));res.add(lex.get(1));
			}


	}

	public static ArrayList<ArrayList<Integer>> lexiographicalFilter(ArrayList<Integer> lst, ArrayList<Integer> clone){

		ArrayList<ArrayList<Integer>> res = new ArrayList<>();

		for (int index = 0; index < lst.size(); index++) {
			if( lst.get(index) > clone.get(index) ){
				res.add(clone);res.add(lst);
				break;
			}else if( lst.get(index) < clone.get(index) ){
				res.add(lst);res.add(clone);
				break;
			}
		}
		return res;
	}

	public static void main (String[] args) {
		ArrayList<Integer> arr = new ArrayList<>();
		arr.add(1);arr.add(7);arr.add(15);arr.add(29);arr.add(11);arr.add(9);
		//arr.add(35);arr.add(44);arr.add(12);arr.add(39);arr.add(44);arr.add(19);arr.add(25);arr.add(2);
		ArrayList<ArrayList<Integer>> res = avgset( arr );

		System.out.println(Arrays.toString(new ArrayList[]{res.get(0)}));
		System.out.println( res.get(1) );

	}
}

