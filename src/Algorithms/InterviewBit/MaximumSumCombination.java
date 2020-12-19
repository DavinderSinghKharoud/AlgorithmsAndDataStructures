package Algorithms.InterviewBit;

import java.util.*;

/**
 * Given two equally sized 1-D arrays A, B containing N integers each.
 *
 * A sum combination is made by adding one element from array A and another element of array B.
 *
 * Return the maximum C valid sum combinations from all the possible sum combinations.
 *
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 *
 * 1 <= A[i] <= 103
 *
 * 1 <= C <= N
 *
 *
 *
 * Input Format
 * First argument is an one-dimensional integer array A of size N.
 *
 * Second argument is an one-dimensional integer array B of size N.
 *
 * Third argument is an integer C.
 *
 *
 *
 * Output Format
 * Return a one-dimensional integer array of size C denoting the top C maximum sum combinations.
 *
 * NOTE:
 *
 * The returned array must be sorted in non-increasing order.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  A = [3, 2]
 *  B = [1, 4]
 *  C = 2
 * Input 2:
 *
 *  A = [1, 4, 2, 3]
 *  B = [2, 5, 1, 6]
 *  C = 4
 *
 *
 * Example Output
 * Output 1:
 *
 *  [7, 6]
 * Output 1:
 *
 *  [10, 9, 9, 8]
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  7     (A : 3) + (B : 4)
 *  6     (A : 2) + (B : 4)
 * Explanation 2:
 *
 *  10   (A : 4) + (B : 6)
 *  9   (A : 4) + (B : 5)
 *  9   (A : 3) + (B : 6)
 *  8   (A : 3) + (B : 5)
 */

public class MaximumSumCombination {

	//Out of Memory as O(n ^ 2) time complexity
    public static ArrayList<Integer> solve(ArrayList<Integer> lst1, ArrayList<Integer> lst2, int k) {
		
		ArrayList<Integer> res = new ArrayList<>();
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		
		for( int num1: lst1 ){
			for( int num2: lst2 ){
				pq.add(num1 + num2);
			}
		}

		while ( k != 0 ){
			res.add(pq.remove());
			k--;
		}
    
		return res;
    }
    public static void main(String[] args) {

    	ArrayList<Integer> lst = new ArrayList<>();
    	lst.add(3);lst.add(2);
		ArrayList<Integer> lst2 = new ArrayList<>();
		lst2.add(1);lst2.add(4);

		System.out.println(solve2(Arrays.asList(59, 63, 65, 6, 46, 82, 28, 62, 92, 96, 43, 28, 37, 92, 5, 3, 54, 93, 83),
				Arrays.asList(59, 63, 65, 6, 46, 82, 28, 62, 92, 96, 43, 28, 37, 92, 5, 3, 54, 93, 83), 10));

    }


	public static ArrayList<Integer> solve2(List<Integer> lst1, List<Integer> lst2, int k) {

    	Collections.sort(lst1, Collections.reverseOrder());
    	Collections.sort(lst2, Collections.reverseOrder());

    	PriorityQueue<Pair> pq = new PriorityQueue<>(((o1, o2) -> o2.sum - o1.sum));
		ArrayList<Integer> res = new ArrayList<>();

		for (int index = 0; index < lst1.size(); index++) {
			pq.add( new Pair(index, 0, lst1.get(index) + lst2.get(0)));
		}

		for (int count = 0; count < k; count++) {
			Pair pair = pq.poll();
			res.add(pair.sum);
			if( pair.otherIndex == lst2.size() - 1 ){
				continue;
			}
			pq.add(new Pair(pair.myIndex, pair.otherIndex + 1, lst1.get(pair.myIndex) + lst2.get(pair.otherIndex + 1)));
		}

    	return res;
	}

	static class Pair{
		int myIndex,otherIndex,sum;
		Pair(int myIndex,int otherIndex,int sum){
			this.myIndex=myIndex;
			this.otherIndex=otherIndex;
			this.sum=sum;
		}
	}

	public static ArrayList<Integer> solve3(List<Integer> lst1, List<Integer> lst2, int k) {

		lst1.sort(Collections.reverseOrder());
		lst2.sort(Collections.reverseOrder());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		ArrayList<Integer> res = new ArrayList<>();

		for(int num1: lst1){
			for(int num2: lst2 ){

				int sum = num1 + num2;
				if(pq.size() >= k){
					if(pq.peek() < sum){
						pq.poll();
						pq.add(sum);
					}else {
						break;

					}
				}else{
					pq.add(sum);
				}
			}
		}

		while (!pq.isEmpty()){
			res.add(pq.remove());
		}
		res.sort(Collections.reverseOrder());
		return res;
	}
}
