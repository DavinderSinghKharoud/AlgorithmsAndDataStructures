package algorthims.LeetCode;


import java.util.HashMap;

/**
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 *
 * Examples:
 *
 * s = "leetcode"
 * return 0.
 *
 * s = "loveleetcode",
 * return 2.
 */
public class FirstUniqueCharacterInAString {

    public static int firstUniqChar(String s) {
        if( s.length() == 0 ){
            return -1;
        }
        HashMap<Character, Integer> map = new HashMap<>();

        for( int i = 0; i < s.length(); i++ ){

            if( map.get( s.charAt(i) ) == null){
                map.put( s.charAt(i), 0);
            }else{
                int value = map.get( s.charAt(i));
                map.put( s.charAt(i), ++value);
            }
        }

        for( int i = 0; i < s.length(); i++ ){
            if( map.get( s.charAt(i)) == 0){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        System.out.println( firstUniqChar("loveleetcode"));
    }
}
