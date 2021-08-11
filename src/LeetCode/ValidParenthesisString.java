/**
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:
 *
 * Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 * Any right parenthesis ')' must have a corresponding left parenthesis '('.
 * Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 * '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 * An empty string is also valid.
 * Example 1:Input: "(*)"
 * Output: True
 */
public class ValidParenthesisString {

    public static boolean checkValidString(String s) {
	    
	   return helper( s, 0, 0, 0 );
    }

    public static boolean helper( String s, int index, int start, int close ){
	
	if( index == s.length() && start == close ){
	    return true;
	}
	if( index >= s.length() ||  close > start){
	    return false;
	}
	if( s.charAt(index) == '(' ){
	    return helper(s , index + 1, start + 1, close );
	}else if( s.charAt( index ) == ')' ){
	    return helper( s, index + 1, start, close + 1);
	}else{
	    if( helper(s , index + 1, start + 1, close ) || helper(s , index + 1, start , close ) 
	    ||helper(s , index + 1, start , close + 1) ){
		return true;
	    }
	}
	
	return false;
    }
    
    public static void main(String[] args) {
	
	    System.out.println( checkValidString("()()*)"));
    }
}
