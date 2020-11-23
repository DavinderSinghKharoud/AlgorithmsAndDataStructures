package algorithms.LeetCode;
/**
 * In a town, there are N people labelled from 1 to N.  There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person labelled a trusts the person labelled b.

If the town judge exists and can be identified, return the label of the town judge.  Otherwise, return -1.

 

Example 1:

Input: N = 2, trust = [[1,2]]
Output: 2
* **/

public class FindtheTownJudge {

	//O(n len ) time complexity
	public static int findJudge(int N, int[][] trust) {
        
        int max;
        boolean check;

		for (int i = 1; i <= N; i++) {
			max = 0;
			check = true;
			for( int[] pair: trust ){
				if( pair[0] == i ) {
					check = false;
					break;
				}
				if( pair[1] == i ){
					max++;
				}
			}

			if( check && max == N - 1 ){
				return i;
			}
		}
		
		return -1;
    }
    
	public static void main (String[] args) {
		
//		System.out.println( findJudge( 4, new int[][]{
//			{1, 3}, {1, 4}, {2, 3},{2, 4}, {4, 3}
//		}));
		System.out.println( findJudge2( 3, new int[][]{
				{1, 3}, {2, 3},{3, 1}
		}));
	}

	//O( n + len of trust ) time complexity and O(n) space complexity
	public static int findJudge2(int N, int[][] trust) {

		int[] indegre = new int[ N + 1 ];
		int[] outdegre = new int[ N + 1 ];
		
		for(int[] pair: trust ){
			outdegre [ pair[0] ] ++;
			indegre[ pair[1] ] ++;
		}
		
		for( int i = 1; i<= N ; i++ ){
			if( indegre[i] == N - 1 && outdegre[i] == 0 ){
				return i;
			}
		}
		
		return -1;
	}
}















