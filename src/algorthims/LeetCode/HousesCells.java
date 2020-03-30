package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class HousesCells {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public static List<Integer> cellCompete(int[] states, int days)
    {
       List<Integer> res = new ArrayList<>();
        if( states.length == 0 || states == null){
            return res;
        }

            if( states.length == 1){
                res.add(states[0]);
                return res;
            }


       for( int i = 0; i<days; i++ ){
           int[] copy = states.clone();
           for( int j = 0; j<states.length; j++ ){

               if( j == 0 ){
                   if( copy[j + 1] == 0 ){
                       states[j] = 0;

                   }else{
                       states[j] = 1;
                   }
                   continue;
               }

               if( j == states.length - 1){
                   if( copy[j - 1] == 0 ){
                       states[j] = 0;
                   }else{
                       states[j] = 1;
                   }
                   continue;
               }

               if( copy[j - 1] == 0 && copy[j + 1] == 0 || (copy[j - 1] == 1 && copy[j + 1] == 1)){
                   states[j] = 0;
               }else{
                   states[j] = 1;
               }

           }
       }

       for( int num: states){
           res.add(num);
       }
       return res;
    }

    public static void main(String[] args) {
        List<Integer> lst = cellCompete(new int[]{
                1
        },1);
        for( int num: lst){
            System.out.println(num);
        }
    }
}
