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
		
		System.out.println( backspaceCompare2("bbbextm",
				"bbb#extm"));
	}
	
	
	  public static boolean backspaceCompare2(String S, String T) {
			int endS = S.length() - 1;
			int endT = T.length() - 1;
			int skipS = 0;
			int skipT = 0;
			
		while( endS >= 0 || endT >= 0 ){

			while ( endS >= 0 ){
				if( S.charAt(endS) == '#') { skipS++; endS--; }
				else if( skipS >0 ){ skipS--; endS--;}
				else break;
			}

			while ( endT >= 0 ){
				if( T.charAt(endT) == '#') { skipT++; endT--; }
				else if( skipT >0 ){ skipT--; endT--;}
				else break;
			}

			if(endS >= 0 && endT >= 0 && S.charAt(endS) != T.charAt(endT) ){
				return false;
			}

			if( (endS >= 0) != (endT >= 0)) return false;
			endS--;
			endT--;
		}


		return true;
	  }


}


