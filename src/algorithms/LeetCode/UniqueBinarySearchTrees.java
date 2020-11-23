package algorithms.LeetCode;

/**
 * 
 * Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 * */
public class UniqueBinarySearchTrees {

	//O(n square) time complexity and O(n) space complexity
	public static int numTrees(int n) {
		if( n == 0 || n == 1){
			return 1;
		}
		
		return catalan( n );
        
    }
    
    public static int catalan( int n ){
		
		int dp[] = new int[n + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		int count;
		
		for( int i = 2; i<= n; i++ ){
			count = 0;
			
			for( int j = 1; j<= i; j++ ){
				count += dp[ j - 1] * dp[ i - j];
			}
			
			dp[i] = count;
		}
		
		return dp[n];
	}
	
	public static void main (String[] args) {
		
		System.out.println( numTrees(3) );
	}

	public static int numTrees2(int n) {
		long C = 1;

		for (int i = 0; i < n; ++i) {
			C = 2 * (2 * i + 1) * C / (i + 2);
		}

		return (int) C;
	}
}

