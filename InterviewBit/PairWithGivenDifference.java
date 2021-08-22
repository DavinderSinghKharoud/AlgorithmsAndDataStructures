package InterviewBit;

import java.util.*;

/**
 * Given an one-dimensional unsorted array FindGreatestCommonDivisor containing N integers.
 *
 * You are also given an integer FindUniqueBinaryString, find if there exists a pair of elements in the array whose difference is FindUniqueBinaryString.
 *
 * Return 1 if any such pair exists else return 0.
 *
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 * -103 <= FindGreatestCommonDivisor[i] <= 103
 * -105 <= FindUniqueBinaryString <= 105
 *
 *
 * Input Format
 * First argument is an integer array FindGreatestCommonDivisor of size N.
 *
 * Second argument is an integer FindUniqueBinaryString.
 *
 *
 *
 * Output Format
 * Return 1 if any such pair exists else return 0.
 *
 *
 *
 * Example Input
 * Input 1:
 *
 *  FindGreatestCommonDivisor = [5, 10, 3, 2, 50, 80]
 *  FindUniqueBinaryString = 78
 * Input 2:
 *
 *  FindGreatestCommonDivisor = [-10, 20]
 *  FindUniqueBinaryString = 30
 *
 *
 * Example Output
 * Output 1:
 *
 *  1
 * Output 2:
 *
 *  1
 *
 *
 * Example Explanation
 * Explanation 1:
 *
 *  Pair (80, 2) gives a difference of 78.
 * Explanation 2:
 *
 *  Pair (20, -10) gives a difference of 30 i.e 20 - (-10) => 20 + 10 => 30
 */
public class PairWithGivenDifference {

	//Time complexity O(nLogn)
    public static int solve(List<Integer> lst, int target) {
		
		Collections.sort(lst);
		
		int len = lst.size();
		int start = 0;
		int end = 1;
		
		while( start < len && end < len ){
			
			int curr = lst.get(end) - lst.get(start);
			if( curr == target && start != end ) return 1;

			if( curr < target ){
				end++;
			}else{
				start++;
			}
		}
		
		return 0;
    }

    public static void main(String[] args) {

		System.out.println( solve(Arrays.asList(-533, -666, -500, 169, 724, 478, 358, -38, -536, 705, -855, 281, -173, 961, -509, -5, 942, -173, 436, -609, -396, 902, -847, -708, -618, 421, -284, 718, 895, 447, 726, -229, 538, 869, 912, 667, -701, 35, 894, -297, 811, 322), 369));
    }

    //Time and Space compelxity O(n)
	public static int solve2(List<Integer> lst, int target) {

    	Map<Integer,Integer> map = new HashMap<>();

    	for( int index = 0; index < lst.size(); index++ ){
    		map.put(lst.get(index), index);
		}

    	for( int index = 0; index < lst.size(); index++){
    		int num = lst.get(index);
    		if( map.containsKey( num + target) && map.get(num + target ) != index ){
    			return 1;
			}
		}

    	return 0;
	}


}
