package algorthims.LeetCode;

import java.util.HashMap;

public class RomanToInteger {

    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        if( s.length() == 0 || s == null ){
            return 0;
        }

        int sum = 0;
        int initialValue =  map.get( s.charAt( s.length() - 1 ) );
        sum += initialValue;
        s = s.substring(0, s.length() - 1);

        for(int i = s.length() - 1; i >= 0; i--){

            int Nextvalue = map.get( s.charAt( i ) );

            if( initialValue <= Nextvalue ){
                sum += Nextvalue;
            }else{
                sum -= Nextvalue;
            }

            s = s.substring(0, s.length() - 1);
            initialValue = Nextvalue;
        }

        return sum;
    }

    public static void main(String[] args) {

        System.out.println( romanToInt("MCMXCIV"));
    }
}
