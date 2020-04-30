package algorthims.LeetCode;

/**
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.
 *
 * Example:
 *
 * Input:
 *
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * Output: 4
 */

public class MaximalSquare {
	
	//Brute Force O( (mn) square ) and No extra space needed
	public static int maximalSquare(char[][] matrix) {
		
		int rows = matrix.length;
		int cols = rows > 0 ? matrix[0].length: 0;
		
		int maxLen = 0;
		
		for( int i = 0; i<rows; i++ ){
			for( int j = 0; j<cols; j++ ){
				
				if( matrix[i][j] == '1' ){
					int tempLen = 1;
					boolean flag = true;
					
					while( i + tempLen < rows && j + tempLen < cols && flag ){
						
						//checking cols for next row
						for( int k = j; k <= tempLen + j; k++ ){
								
							if( matrix[i + tempLen][ k ] == '0' ){
								flag = false;
								break;
							}
						}
						
						//checking for rows
						for( int k = i; k <= tempLen + i; k++ ){
							
							if( matrix[k][ j + tempLen ] == '0' ){
								flag = false;
								break;
							}
						}
						
						if( flag ){
							tempLen++;
						}
					}
					
					maxLen = Math.max( maxLen, tempLen );
				}
			}
		}
		
		return maxLen * maxLen;
        
    }
    
	public static void main (String[] args) {
		
		System.out.println( maximalSquare( new char[][]{
			{'1', '1', '1', '0', '0'},
			{'1', '1', '1', '1', '1'},
			{'1', '1', '1', '0', '1'},
			{'1', '0', '0', '1', '0'}
		}));
		
	}

	// O(m * n ) time complexity and space complexity
//	public static int maximalSquare2(char[][] matrix) {
//
//	}
}

