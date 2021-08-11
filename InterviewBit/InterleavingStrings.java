/*
Given A, B, C, find whether C is formed by the interleaving of A and B.

Input Format:*

The first argument of input contains a string, A.
The second argument of input contains a string, B.
The third argument of input contains a string, C.
Output Format:

Return an integer, 0 or 1:
    => 0 : False
    => 1 : True
Constraints:

1 <= length(A), length(B), length(C) <= 150
Examples:

Input 1:
    A = "aabcc"
    B = "dbbca"
    C = "aadbbcbcac"

Output 1:
    1
    
Explanation 1:
    "aa" (from A) + "dbbc" (from B) + "bc" (from A) + "a" (from B) + "c" (from A)

Input 2:
    A = "aabcc"
    B = "dbbca"
    C = "aadbbbaccc"

Output 2:
    0

Explanation 2:
    It is not possible to get C by interleaving A and B.
 */


public class InterleavingStrings {

	//time complexity O( 2 ^ n ) and space complexity O(1)
	public static int isInterleave(String a, String b, String c) {

		boolean check = helper( a, b, c );
		return check ? 1: 0;
    }

    public static boolean helper( String a, String b, String c ){

		if( c.isEmpty() ){
			return true;
		}

		boolean check = false;
		for(int index = 1; index <= c.length(); index++ ){
			String sub = c.substring(0, index);
			int len = sub.length();
			if( len <= a.length() && a.charAt(0) == c.charAt(0) && sub.equals( a.substring(0, len) ) ){
				check = check || helper( a.substring(len), b, c.substring(index) );
			}

			if( len <= b.length() && b.charAt(0) == c.charAt(0) && sub.equals( b.substring(0, len) ) ){
				check = check || helper( a, b.substring(len), c.substring(index) );
			}
		}
		return check;
	}
	public static void main (String[] args) {
		
		System.out.println( isInterleave3("aa", "bb", "aabb"));
	}

	public static int isInterleave2(String a, String b, String c) {

		boolean check = helper2( a, b, c, 0, 0, 0 );
		return check ? 1: 0;
	}

	public static boolean helper2(String a, String b, String c, int index1, int index2, int index3){

		if(index3 >= c.length() ){
			return true;
		}

		boolean check = false;

		if( index1 < a.length() && a.charAt(index1) == c.charAt(index3) ){
			check |= helper2(a, b, c, index1 + 1, index2, index3 + 1);
		}
		if (index2 < b.length() && b.charAt(index2) == c.charAt(index3) ){
			check |= helper2(a, b, c, index1, index2 + 1, index3 + 1);
		}
		return check;
	}

	//Time complexity O(M*N) and space complexity O(M*N)
	public static int isInterleave3(String a, String b, String c) {

		if( c.length() != a.length() + b.length() ){
			return 0;
		}

		boolean[][] match = new boolean[a.length() + 1][b.length() + 1];

		//Find the starting point
		for(int index = 1; index <= a.length(); index++ ){
			if( a.charAt(index - 1) == c.charAt(index - 1) ){
				match[index][0] = true;
			}else{
				break;
			}
		}

		for (int index = 1; index <= b.length(); index++) {
			if( b.charAt(index - 1) == c.charAt(index - 1) ){
				match[0][index] = true;
			}else{
				break;
			}
		}

		for (int row = 1; row <= a.length(); row++) {
			char ai = a.charAt(row - 1);
			for (int col = 1; col <= b.length(); col++) {
				char bi = b.charAt(col - 1);
				int cIndex = row + col;
				char ci = c.charAt(cIndex - 1);
				if( ai == ci ){
					match[row][col] = match[row - 1][col] || match[row][col];
				}
				if( bi == ci ){
					match[row][col] = match[row][col - 1] || match[row][col];
				}
			}
		}

		return ( match[a.length()][b.length()] ) ? 1: 0;
	}
}

