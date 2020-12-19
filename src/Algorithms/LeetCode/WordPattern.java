package Algorithms.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Here follow means a full match, such that there is a bijection
 * between a letter in pattern and a non-empty word in str.
 *
 * Example 1:
 *
 * Input: pattern = "abba", str = "dog cat cat dog"
 * Output: true
 */
public class WordPattern {

    public static boolean wordPattern(String pattern, String str) {

        HashMap<String,String> map = new HashMap<>();
        List lst = new ArrayList();

        String []strings = str.split(" ");
        if( pattern.length() != strings.length){
            return false;
        }
        for(int i = 0; i<pattern.length(); i++){

            String ch = String.valueOf( pattern.charAt(i) );
            if( map.get( ch ) == null){
                map.put( ch, strings[i]);

                if( lst.contains( strings[i])){
                    return false;
                }
                lst.add( strings[i] );

            }else{

                if( !strings[i].equals( map.get( ch ) )){
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println( wordPattern( "abba", "dog cat cat dog"));
    }

}
