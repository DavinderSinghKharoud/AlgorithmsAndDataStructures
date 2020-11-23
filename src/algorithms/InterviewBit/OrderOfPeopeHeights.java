package algorithms.InterviewBit;

import java.util.*;

public class OrderOfPeopeHeights {

    public static ArrayList<Integer> order(List<Integer> lst1, List<Integer> lst2) {
        int len = lst1.size();
        int[][] dp = new int[len][2];
        
        for( int index = 0; index < len; index++ ){
			dp[index] = new int[]{ lst1.get(index), lst2.get(index) };
		}
		
		Arrays.sort( dp, (o1,o2) -> ( o1[0] == o2[0] ) ? o1[1] - o2[1]: o2[0] - o1[0]);
		
		ArrayList<Integer> res = new ArrayList<>();
		for(int[] people: dp ){
			res.add( people[1], people[0] );
		}
		
		return res;
    }
    public static void main(String[] args) {


		System.out.println( order( Arrays.asList(5, 3, 2, 6, 1, 4), Arrays.asList(0, 1, 2, 0, 3, 2)));
    }
}
