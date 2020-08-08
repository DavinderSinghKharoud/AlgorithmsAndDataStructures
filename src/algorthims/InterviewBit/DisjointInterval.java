package algorthims.InterviewBit;

import java.util.*;

public class DisjointInterval {

	//O( nLog(n) ) time complexity and O(n) space complexity
    public static int solve(ArrayList<List<Integer>> lst) {
		int len = lst.size();
		int[] dp = new int[len];
		int ans = -1;
		dp[0] = 1;

		lst.sort(Comparator.comparingInt(o -> o.get(0)));

		for( int index = 1; index < len; index++ ){
			int max = 1;
			for( int backward = index - 1; backward >= 0; backward-- ){
				
				if( !isOverlapping( lst.get(index), lst.get(backward) ) ){
					max = Math.max( max, dp[backward] + 1);
				}
				
			}
			dp[index] = max;
			ans = Math.max( ans, max);
		}
		
		return ans;
    }
    
    public static boolean isOverlapping( List<Integer> num1, List<Integer> num2 ){
		
		if(num1.get(0) > num2.get(1)){
			return false;
		}
		return true;
	}
    public static void main(String[] args) {

    	ArrayList<List<Integer>> lst = new ArrayList<>();
    	lst.add(Arrays.asList(1,1));
		lst.add(Arrays.asList(2,3));
		lst.add(Arrays.asList(4,5));
		lst.add(Arrays.asList(6,10));

		System.out.println( solve2(lst));

    }
    
       public static int solve2(ArrayList<List<Integer>> lst) {
			int len = lst.size();
			int ans = 1;


			lst.sort(Comparator.comparingInt(o -> o.get(0)));
		   int end = lst.get(0).get(1);

		   for( int index = 1; index < len; index++ ){
				
				int currStart = lst.get(index).get(0);
				int currEnd = lst.get(index).get(1);
				
				if( currStart > end ){
					ans++;
					end = currEnd;
				}else{
					end = Math.min(end, currEnd);
				}
			}
			
			return ans;

		
			
			
	   }
}
