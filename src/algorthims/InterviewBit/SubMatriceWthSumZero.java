package algorthims.InterviewBit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a 2D matrix, find the number non-empty sub matrices, such that the sum of the elements inside the sub matrix is equal to 0. (note: elements might be negative).
 * <p>
 * Example:
 * <p>
 * Input
 * <p>
 * -8 5  7
 * 3  7 -8
 * 5 -8  9
 * Output
 * 2
 * <p>
 * Explanation
 * -8 5 7
 * 3 7 -8
 * 5 -8 9
 * <p>
 * -8 5 7
 * 3 7 -8
 * 5 -8 9
 */
public class SubMatriceWthSumZero {

	//O(n ^ 3 ) time complexity and O(n) space complexity
    public static int solve(ArrayList<ArrayList<Integer>> arr) {

        int n = arr.size();
        if( n == 0 ) return 0;
        int m = arr.get(0).size();

        int count = 0;

        for (int left = 0; left < m; left++) {
            int[] dp = new int[n];

			for(int right = left; right < m; right++ ){
				
				for(int index = 0; index < n; index ++ ){
					dp[index] += arr.get(index).get(right);
				}
				
				count += find_target_in_array( dp, 0 );
			}
        }

		return count;
    }
    
    public static int helper( int[] dp ){
		
		int count = 0;
		
		for(int left = 0; left < dp.length; left++ ){
			int temp = dp[left];
			if( temp == 0 ) count++;
			
			for( int right = left + 1; right < dp.length; right++ ){
				temp += dp[right];
				if( temp == 0 ) count++;
			}
		}
		
		return count;
	}

    public static void main(String[] args) {
		
		ArrayList<ArrayList<Integer>> lst = new ArrayList<>();
		
		ArrayList<Integer> temp1 = new ArrayList<>();
		temp1.add(-8);temp1.add(5);temp1.add(7);
		ArrayList<Integer> temp2 = new ArrayList<>();
		temp2.add(3);temp2.add(7);temp2.add(-8);
		ArrayList<Integer> temp3 = new ArrayList<>();
		temp3.add(5);temp3.add(-8);temp3.add(9);
		
		lst.add(temp1);lst.add(temp2);lst.add(temp3);
		
		System.out.println( solve(lst) );

    }

    //O(n) time ans space complexity
	public static int find_target_in_array(int[] arr, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int count = 0;
		//because 0 will be the starting point
		map.put(0,1);

		for (int index = 0; index < arr.length; index++) {
			sum += arr[index];

			//if same key exists, it means sum between the boundaries is equal to target
			if( map.containsKey( sum - target) ){
				count += map.get( sum - target );
			}

			map.put( sum, map.getOrDefault(sum, 0) + 1 );
		}
		return count;
	}
}
