/*
You are given a set of coins S. In how many ways can you make sum N assuming you have infinite amount of each coin in the set.

Note : Coins in set S will be unique. Expected space complexity of this problem is O(N).

Example :

Input : 
	S = [1, 2, 3] 
	N = 4

Return : 4

Explanation : The 4 possible ways are
{1, 1, 1, 1}
{1, 1, 2}
{2, 2}
{1, 3}	
 */

import java.util.*;
public class CoinSumInfinite {

	//O(N) space complexity and O(NM) time complexity
	//we cannot use coins after num approach as there will be a overlap [1,2,3] s = 4
	//4 would be 1 + 1 + 2 would repeat twice
	public static int coinchange2(ArrayList<Integer> coins, int n) {

		int mod = 1000007;

		int[] dp = new int[n + 1];
		dp[0] = 1;

		for (int index = 0; index < coins.size(); index++) {
			for (int num = coins.get(index); num <= n; num++) {
				dp[num] = (dp[num] + dp[ num - coins.get(index) ]) % mod ;
			}
		}

		return dp[n];
    }
	public static void main (String[] args) {
		ArrayList<Integer> coins = new ArrayList<>();
		coins.add(2);coins.add(5);coins.add(3);coins.add(6);
		
		System.out.println( coinchange2( coins, 10));
	}

	//Time limit exceeded
	public static int coinchange3(ArrayList<Integer> coins, int n) {
		HashMap<String, Integer> hashMap = new HashMap<>();
		return helper( coins, n, coins.size() - 1, hashMap );
	}
	
	public static int helper(ArrayList<Integer> coins, int sum, int index, HashMap<String, Integer> hashMap){
		if( sum == 0 ){
			return 1;
		}
		
		if( index < 0 || sum < 0){
			return 0;
		}

		if( hashMap.containsKey(index + "," + sum)){
			hashMap.get(index + "," + sum);
		}
		int with =  helper( coins, sum - coins.get(index), index, hashMap ) % 1000007;
		int without = helper( coins, sum, index - 1, hashMap) % 1000007;
		hashMap.put(index + "," + sum, with + without);
		return  without + with;
	}
}

