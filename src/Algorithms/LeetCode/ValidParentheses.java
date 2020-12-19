package Algorithms.LeetCode;

import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {

    public static boolean isValid(String s) {

        if( s.length() % 2 != 0 ){
            return false;
        }

        HashMap<Character, Character> map =  new HashMap<>();
        map.put( '(', ')');
        map.put( '{', '}');
        map.put( '[', ']');



        Stack<Character> stack = new Stack<>();

        for( int i = 0; i<s.length(); i++ ){

            char c = s.charAt(i);

            if( map.containsKey( c ) ){
                stack.push( c );
            }else{

                if( stack.size() == 0 ){
                    return false;
                }

                if( map.get( stack.peek()).equals( c ) ){
                    stack.pop();
                }else{
                    return false;
                }

            }


        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println( isValid("()[]{}"));
    }
}

