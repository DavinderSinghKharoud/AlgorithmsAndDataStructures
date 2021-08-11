/*
 n a N x N grid composed of 1 x 1 squares, each 1 x 1 square consists of a /, \, or blank space.  These characters divide the square into contiguous regions.

(Note that backslash characters are escaped, so a \ is represented as "\\".)

Return the number of regions.

 

Example 1:

Input:
[
  " /",
  "/ "
]
Output: 2
Explanation: The 2x2 grid is as follows:

Example 2:

Input:
[
  " /",
  "  "
]
Output: 1
Explanation: The 2x2 grid is as follows:

Example 3:

Input:
[
  "\\/",
  "/\\"
]
Output: 4
Explanation: (Recall that because \ characters are escaped, "\\/" refers to \/, and "/\\" refers to /\.)
The 2x2 grid is as follows:

Example 4:

Input:
[
  "/\\",
  "\\/"
]
Output: 5
Explanation: (Recall that because \ characters are escaped, "/\\" refers to /\, and "\\/" refers to \/.)
The 2x2 grid is as follows:

Example 5:

Input:
[
  "//",
  "/ "
]
Output: 3
Explanation: The 2x2 grid is as follows:

 

Note:

1 <= grid.length == grid[0].length <= 30
grid[i][j] is either '/', '\', or ' '.
 */


public class RegionsCutBySlashes {
	
	public static int regionsBySlashes(String[] grid) {
        
		int len = grid.length;
		UF dsu = new UF( 4 * len * len );
		
		for( int r = 0; r < len; r++ ){
			for( int c = 0; c < len; c++ ){
				int root = 4 * ( r * len + c );
				char val = grid[r].charAt(c);
				
				if( val != '\\' ){
					dsu.union(root, root + 1);
					dsu.union( root + 2, root + 3);
				}
				
				if( val != '/' ){
					dsu.union(root, root + 2 );
					dsu.union( root + 1, root + 3 );
				}
				
				//north south
				if( r + 1 < len ){
					dsu.union( root + 3, (root + 4 * len));
					
				}
				if( r - 1 >= 0 ){
					dsu.union(root, ( root - 4 * len )  + 3 );
				}
				
				//east west
				if( c + 1 < len ){
					dsu.union( root + 2, ( root + 4 ) + 1 );
				}
				
				if( c - 1 >= 0 ){
					dsu.union( root + 1, ( root - 4 ) + 2 );
				}
			}
		}
		
		int res = 0;
		for( int x = 0; x < 4 * len * len; x++ ){
			if( dsu.find(x) == x ){
				res++;
			}
		}
		
		return res;
    }
    
	public static void main (String[] args) {
		
		System.out.println( regionsBySlashes(new String[]{
				" /", "/ "
		}));
	}
	

}

class Subset{
	int parent;
	int rank;
}

class UF{
	Subset[] sub;
	
	UF(int n){
		sub = new Subset[n];
		for( int i = 0; i < n; i++ ){
			sub[i] = new Subset();
			sub[i].parent = i;
			sub[i].rank = 0;
		}
	}
	
	public int find( int x ){
		if( x != sub[x].parent ){
			sub[x].parent = find( sub[x].parent);
		}
		return sub[x].parent;
	}
	
	public void union( int x, int y ){
		int xroot = find(x);
		int yroot = find(y);
		
		
		if( sub[xroot].rank < sub[yroot].rank ){
			sub[xroot].parent = sub[yroot].parent;
		}else if( sub[xroot].rank > sub[yroot].rank ){
			
			sub[yroot].parent = sub[xroot].parent;
		}else{
			
			sub[xroot].parent = sub[yroot].parent;
			sub[yroot].rank++;
		}
	}
}


















