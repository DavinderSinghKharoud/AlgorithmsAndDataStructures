package algorithms.InterviewBit;

import java.util.HashMap;
import java.util.Map;

public class WaystoDecode {

    public static int numDecodings1(String str) {
        Map<String, Integer> map = new HashMap<>();
        return helper( str, 0, map);

    }

    public static int helper(String str, int index, Map<String, Integer> map) {
        if( index >= str.length() ){
            return 1;
        }

        if( map.containsKey(str + index) ){
            return map.get( str + index );
        }


        int count1 = Character.getNumericValue(str.charAt(index)) == 0 ? 0 :helper(str, index + 1, map);
        int count2 = 0;
        if(str.length() - index > 1 &&
                Integer.parseInt( str.substring(index, index + 2)) >= 10 &&
                Integer.parseInt( str.substring(index, index + 2)) <= 26  ){
            count2 = helper(str, index + 2, map);
        }
        map.put( str + index, count1 + count2 );
        return count1 + count2;
    }

    public static void main(String[] args) {

        System.out.println( numDecodings1("5163490394499093221199401898020270545859326357520618953580237168826696965537789565062429676962877038781708385575876312877941367557410101383684194057405018861234394660905712238428675120866930196204792703765204322329401298924190"));
        System.out.println(numDecodings1("121") );
    }

    public static int numDecodings2(String str) {
       int[] dp = new int[str.length() + 1];

       dp[0] = 1;
       dp[1] = str.charAt(0) == '0' ? 0: 1;

        for (int index = 2; index <= str.length(); index++) {
            int oneDigit = Integer.parseInt( str.substring(index - 1, index ) );
            int twoDigit = Integer.parseInt( str.substring( index - 2, index ) );

            if( oneDigit >= 1 ){
                dp[index] += dp[ index - 1 ];
            }
            if( twoDigit >= 10 && twoDigit <= 26 ){
                dp[index] += dp[ index - 2 ];
            }


        }
       return dp[str.length()];
    }
}
