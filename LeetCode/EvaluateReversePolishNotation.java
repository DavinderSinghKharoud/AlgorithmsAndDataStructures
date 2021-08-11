package LeetCode;

import java.util.*;


public class EvaluateReversePolishNotation {
	
	public static int evalRPN(String[] tokens) {
		
		Stack<Integer> stack = new Stack<>();
		
		for(String item: tokens ){
		    
		if( isNumeric(item) ){
			stack.push( Integer.parseInt(item) );
			continue;
		    }

		    int num2 = stack.pop();
		    int num1 = stack.pop();
		    int temp;

		    if( item.equals("+") ){ temp = num1 + num2;}
		    else if( item.equals("-")) { temp = num1 - num2;}
		    else if( item.equals("*")) { temp = num1 * num2; }
		    else { temp = num1 / num2; }

		    stack.push( temp );
		}
        
	    return stack.pop();
	}
	public static void main (String[] args) {
		
		System.out.println( evalRPN( new String[] {
				"4","13","5","/","+"
		}) );
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch(NumberFormatException e){
			return false;
		}
	}
}

