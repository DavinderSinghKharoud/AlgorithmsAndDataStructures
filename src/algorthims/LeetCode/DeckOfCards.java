package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeckOfCards {

    public static  boolean hasGroupSizeGCD(int []deck){

        if( deck.length == 1){
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> temp = new ArrayList();

        for( int i = 0; i<deck.length; i++){

            int value = deck[i];
            if( map.get(value) == null){
                map.put(value, 1);
            }else{
                int oldValue = map.get(value);
                map.put( value, oldValue+1);
            }

            temp.add(value);
        }

        int gcd = map.get(  temp.get( 0 ));
        for( int i = 1; i<temp.size(); i++){

            gcd = getGCD( gcd , map.get( temp.get(i) ));
        }

        return  gcd>=2;
    }

    //Get Greatest common divisor
    private static int getGCD(Integer num, Integer num1) {

        return num==0 ? num1:getGCD( num1%num, num);
    }

    public static boolean hasGroupsSizeX(int[] deck) {

        if( deck.length == 1){
            return false;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> temp = new ArrayList();

        for( int i = 0; i<deck.length; i++){

            int value = deck[i];
            if( map.get(value) == null){
                map.put(value, 1);
            }else{
                int oldValue = map.get(value);
                map.put( value, oldValue+1);
            }

            temp.add(value);
        }

        int len = map.get( temp.get(0));

        int index = 1;
        while ( true) {

            while (index < temp.size()) {

                int value = temp.get(index);

                if (map.get(value) % len == 0) {
                    index += 1;
                }
                if( index == temp.size() ){
                    return true;
                }else{
                    return false;
                }

            }

            if( len%2 == 0){
                len = len/2;
            }else {
                return false;
            }

        }

    }
    public static void main(String[] args) {

        System.out.println( hasGroupSizeGCD(new int[]{
                1,2,3,4,4,3,2,1
        }) );
    }
}
