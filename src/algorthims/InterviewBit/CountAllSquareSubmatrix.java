package algorthims.InterviewBit;

public class CountAllSquareSubmatrix {

	//O( m * n ) time complexity
    public static int countSubMatrix(int[][] mtrx,
									 int k, int p) {
		
		int rowLen = mtrx.length;
		int colLen = mtrx[0].length;
		
		int[][] dp  = new int[rowLen][colLen];
		
		createTable( mtrx, dp );
		
		return countHelper( mtrx, dp, k, p );
    }
    
    public static int  countHelper( int[][] mtrx, int[][] dp, int k, int target ){
		int res = 0;
		int subMatrSum = 0;
		
		
		for( int row = k - 1; row < mtrx.length; row++ ){
			for( int col = k - 1; col < mtrx[0].length; col++ ){
				
				if( row == (k - 1) || col == (k - 1) ){
					
					if( row == (k - 1) && col == (k - 1) ){ //If it is first matrix
						subMatrSum = dp[row][col];
						
						
					}else if( row == (k - 1) ){ // we need to set the appropriate column to make the square matrix
						subMatrSum = dp[row][col] - dp[row][col - k];
					}else {
						// we need to set the appropriate row to make the square matrix
						subMatrSum = dp[row][col] - dp[row - k][col];
					}
					
				}else{	
					subMatrSum = dp[row][col] - dp[row - k][col] - dp[row][col - k] + dp[row - k][col - k];//compensating that removed twice
				}
				
				if( subMatrSum >= target ){
					res++;
				}
			}
		}
		
		return res;
	}
    public static void createTable(int[][] mtrx, int[][] dp ){
		
		dp[0][0] = mtrx[0][0];
		
		//Fill the rows
		for( int row = 1; row < mtrx.length; row++ ){
			dp[row][0] = mtrx[row][0] + dp[row - 1][0];
		}
		
		//Fill the columns
		for( int col = 1; col < mtrx[0].length; col++ ){
			dp[0][col] = mtrx[0][col] + dp[0][col - 1];
		}
		
		for( int row = 1; row < mtrx.length; row++ ){
			for( int col = 1; col < mtrx[0].length; col++ ){
				
				dp[row][col] = mtrx[row][col] + dp[row - 1][col] + dp[row][col - 1] - dp[row - 1][col - 1];
			}
		}
	}

    public static void main(String[] args) {
		int[][] mtrx  = {
				{ 1, 7, 1, 1, 1 },
				{ 2, 2, 2, 2, 2 },
				{ 3, 9, 6, 7, 3 },
				{ 4, 3, 2, 4, 5 },
				{ 5, 1, 5, 3, 1 }
		};

		int k = 3;
		int p = 35;

		System.out.println( countSubMatrix(mtrx, k, p));
    }
}
