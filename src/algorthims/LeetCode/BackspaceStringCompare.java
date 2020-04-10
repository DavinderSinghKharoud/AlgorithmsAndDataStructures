package algorthims.LeetCode;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 *
 * Example 1:
 *
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 */
public class BackspaceStringCompare {
	
	 public static boolean backspaceCompare(String S, String T) {
	     
		    StringBuilder sb1 = new StringBuilder();
		    StringBuilder sb2 = new StringBuilder();
		    int skip = 0;
		    
		    for(int i = S.length() - 1; i>=0 ; i-- ){
			char ch = S.charAt( i );
			
			if( ch == '#' ){
			    skip++;
			}else{
			    
			    if( skip >= 1 ){
				skip--;
			    }else{
				sb1.append(ch);
			    }
			}	
		    }
		    
		    skip = 0;
		    
		    for(int i = T.length() - 1; i>=0 ; i-- ){
			char ch = T.charAt( i );
			
			if( ch == '#' ){
			    skip++;
			}else{
			    
			    if( skip >= 1 ){
				skip--;
			    }else{
				sb2.append(ch);
			    }
			}	
		    }
        
		    return sb1.toString().equals( sb2.toString() );
	    }
	    
	public static void main (String[] args) {
		
		System.out.println( backspaceCompare("a#c", "b"));
	}
}

