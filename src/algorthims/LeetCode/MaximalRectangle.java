/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example:

Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
 */


public class  MaximalRectangle {

	//O(rows * columns) time complexity and O(columns) space complexity
	public static int maximalRectangle(char[][] matrix) {
        if( matrix.length == 0 ) return 0;
        int[] series = new int[matrix[1].length];
        int max = 0;
        for( int i = matrix.length - 1; i >= 0; i-- ){
			
			for( int j = 0; j< matrix[0].length; j++ ){
				char c = matrix[i][j];
				
				if( c == '1' ){
					series[j] += 1;
				}else{
					series[j] = 0;
				}
			}
			
			max = Math.max( max, findMaxRectangle( series ) );
		}
		
		return max;
    }


    public static int findMaxRectangle( int[] series ){
		int maxArea = 0;
		if( series == null || series.length == 0 ) return 0;
		int len = series.length;
		
		int[] left = new int[len];
		left[0] = -1;
		
		int[] right = new int[len];
		right[len - 1] = len;
		
		//calculate the next smallest left
		
		for( int i = 1; i< len; i++ ){
			
			int p = i - 1;
			
			while( p >= 0 && series[p] >= series[i] ){
				p = left[p];
			}
			
			left[i] = p;
		}
		
		//calculate the next right
		for( int i = len - 2; i>= 0; i-- ){
			int p = i + 1;
			
			while( p < len && series[p] > series[i] ){
				p = right[p];
			}
			
			right[i] = p;
		}
		
		for( int i = 0; i<len; i++ ){
			maxArea = Math.max( maxArea, series[i] * ( right[i] - left[i] - 1 ) );
		}
		
		return maxArea;
		
	}
	public static void main (String[] args) {
		
		System.out.println( maximalRectangle( new char[][]{
			{'1', '0', '1', '0', '0'}, 
			{'1', '0', '1', '1', '1'},
			{'1', '1', '1', '1', '1'},
			{'1', '0', '0', '1', '0'}
		}));
	}
}

