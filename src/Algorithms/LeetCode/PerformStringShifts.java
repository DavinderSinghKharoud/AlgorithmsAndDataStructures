package Algorithms.LeetCode;

/**
 * You are given a string s containing lowercase English letters, and a matrix shift, where shift[i] = [direction, amount]:
 *
 * direction can be 0 (for left shift) or 1 (for right shift).
 * amount is the amount by which string s is to be shifted.
 * A left shift by 1 means remove the first character of s and append it to the end.
 * Similarly, a right shift by 1 means remove the last character of s and add it to the beginning.
 * Return the final string after all operations.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", shift = [[0,1],[1,2]]
 * Output: "cab"
 * Explanation:
 * [0,1] means shift to left by 1. "abc" -> "bca"
 * [1,2] means shift to right by 2. "bca" -> "cab"
 */
public class PerformStringShifts {
	
	public static String stringShift(String s, int[][] shift) {
		    
		    char []arr = new char[ s.length() ];
			
		    int left = 0;
		    int right = 0;
		    String move = "";
		    
		    for( int[] shifts: shift ){
			
			if( shifts[0] == 0 ){
			    left += shifts[1];
			}else{
			    right += shifts[1];
			}
		    }
		    
		    if( right > left ){
			right -= left;
			move = "right";
		    }else{
			left -= right;
			move = "left";
		    }
		    
		    
		    if( move.equals("right")){
			for(int i = 0; i<s.length(); i++ ){
			    char ch = s.charAt(i);
			    
			    int index = i + right;

			    while ( index < 0 || index>=s.length() ){

						index -= s.length();


				}

			    arr[index] = ch;
			}
		    }else{
			
			for(int i = 0; i<s.length(); i++ ){
			    char ch = s.charAt(i);
			    
			    int index = i - left;

				while ( index < 0 || index>=s.length() ){
					index += s.length();
				}
			    
			    arr[index] = ch;
			}
		    }
		    
		    StringBuilder str = new StringBuilder();
		    for( char ch: arr ){
			str.append(ch);
		    }
		    
		return str.toString();
	}
	public static void main (String[] args) {
		    System.out.println( stringShift("yisxjwry", new int[][]{
					{1,8},{1,4},{1,3},{1,6},{0,6},{1,4},{0,2},{0,1}
		    }));


	}
}

