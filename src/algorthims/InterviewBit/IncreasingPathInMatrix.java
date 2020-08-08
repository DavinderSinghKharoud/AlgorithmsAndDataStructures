package algorthims.InterviewBit;

import java.util.*;
public class IncreasingPathInMatrix {

    public static int solve(List<List<Integer>> lst) {
			int len1 = lst.size();
			int len2 = lst.get(0).size();

			if( len1 == 1 && len2 == 1 )return 1;

			int dp[][] = new int[len1][len2];

			for (int index = 0; index < len1; index++) {
				Arrays.fill(dp[index], -1 );
			}

			dp[0][0] = 0;
			int ans = -1;

			//Through the rows
			for (int row = 1; row < len1; row++) {
				if( lst.get(row).get(0) > lst.get(row - 1).get(0) ){
					dp[row][0] = dp[row - 1][0] + 1;
					ans = Math.max( ans, dp[row][0]);
				}else{
					break;
				}
			}

			for (int col = 1; col < len2; col++) {
				if( lst.get(0).get(col) > lst.get(0).get(col - 1) ){
					dp[0][col] = dp[0][col - 1] + 1;
					ans = Math.max( ans, dp[0][col] );
				}else{
					break;
				}
			}

			for (int row = 1; row < len1; row++) {
				for (int col = 1; col < len2; col++) {
					int num = lst.get(row).get(col);
					if( num > lst.get(row - 1).get(col) && dp[row - 1][col] != -1 ){
						dp[row][col] = Math.max( dp[row][col], dp[row - 1][col] + 1);
					}

					if( num > lst.get(row).get(col - 1) && dp[row][col - 1] != -1 ){
						dp[row][col] = Math.max( dp[row][col], dp[row][col - 1] + 1);
					}

					ans = Math.max( ans, dp[row][col] );
				}
			}

			if( dp[len1 - 1][len2 - 1] <= 0 ) return -1;
			return dp[len1 - 1][len2 - 1] + 1;
	}

    public static void main(String[] args) {

    	List<List<Integer>> lst = new ArrayList<>();

    	lst.add(Arrays.asList(63, 56, 11, 60, 25, 38, 49, 84, 96, 42, 3, 51));
//		lst.add(Arrays.asList(2, 2, 3, 4));
//		lst.add(Arrays.asList(3, 2, 3, 4));
//		lst.add(Arrays.asList(4, 5, 6, 7));

		System.out.println( solve(lst));
    }
}
