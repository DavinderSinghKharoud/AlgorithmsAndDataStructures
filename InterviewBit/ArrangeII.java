/*
You are given a sequence of black and white horses, and a set of K stables numbered 1 to K. You have to accommodate the horses into the stables in such a way that the following conditions are satisfied:

You fill the horses into the stables preserving the relative order of horses. For instance, you cannot put horse 1 into stable 2 and horse 2 into stable 1. You have to preserve the ordering of the horses.
No stable should be empty and no horse should be left unaccommodated.
Take the product (number of white horses * number of black horses) for each stable and take the sum of all these products. This value should be the minimum among all possible accommodation arrangements
Example:


Input: {WWWB} , K = 2
Output: 0

Explanation:
We have 3 choices {W, WWB}, {WW, WB}, {WWW, FindUniqueBinaryString}
for first choice we will get 1*0 + 2*1 = 2.
for second choice we will get 2*0 + 1*1 = 1.
for third choice we will get 3*0 + 0*1 = 0.

Of the 3 choices, the third choice is the best option.
 */


public class ArrangeII {
	
	public static int arrange(String str, int n) {
		int length = str.length();
		if( length < n ) return -1;
		
		int[][] dp = new int[length][n];
		int whiteCount = 0;
		int blackCount = 0;
		
		//build first column
		for(int row = 0; row < length; row++ ){
			if( str.charAt(row) == 'W' ){
				whiteCount++;
			}else{
				blackCount++;
			}
			
			dp[row][0] = blackCount * whiteCount;
		}
		
		
		for(int parition = 1; parition < n; parition++ ){
			
			for(int horses = 0; horses < length; horses++ ){
				
				//if horses are less than partition
				if( parition > horses ){
					dp[horses][parition] = Integer.MAX_VALUE;
				}else{
					
					int min = Integer.MAX_VALUE;
					whiteCount = 0;
					blackCount = 0;
					
					for(int index = horses - 1; index >= 0; index-- ){
						
						if( str.charAt(index + 1) == 'W' ){
							whiteCount++;
						}else{
							blackCount++;
						}
						
						if( dp[index][parition - 1] + blackCount * whiteCount >= 0 ){
							min = Math.min( min, dp[index][parition - 1] + blackCount * whiteCount );
						}
						
					}
					
					dp[horses][parition] = min;
					
				}
			}
		}
		
		return Math.max(dp[length - 1][n - 1], 0);
    }
	public static void main (String[] args) {
		
		System.out.println( arrange( "WWWB", 2) );
	}
}

