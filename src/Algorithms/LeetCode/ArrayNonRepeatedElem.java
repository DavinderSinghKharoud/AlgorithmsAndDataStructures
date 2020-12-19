package Algorithms.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArrayNonRepeatedElem {

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(3);
        list.add(0);
        System.out.println( getFirstTwoItemsWithoutPair(list));


    }
    public static List<Integer> getFirstTwoItemsWithoutPair(List<Integer> list) {

        List<Integer> lst = new ArrayList<>();
        HashMap<Integer,Integer> map = new HashMap<>();

        for( int i = 0; i<list.size(); i++){

            if( map.get( list.get(i) )== null){
                map.put(list.get(i), 1);
            }else{

                int value = map.get( list.get(i) );
                value++;
                map.put( list.get(i), value);

            }
        }

        int count = 0;
        for( int i = 0; i<list.size(); i++){
            if( map.get( list.get(i) ) == 1){
                lst.add( list.get(i));

                count++;
            }


            if( count == 2){
                break;
            }
        }

        return lst;

    }
}
