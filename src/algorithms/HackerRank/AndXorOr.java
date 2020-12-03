package algorithms.HackerRank;

import java.util.*;

public class AndXorOr {

    static int andXorOr(int[] arr) {


        int res = arr[0] ^ arr[1];
        Stack<Integer> stack = new Stack<>();

        for( int num: arr ){

            while( !stack.isEmpty() ){
                res = Math.max( res, stack.peek() ^ num );
                if( stack.peek() > num ){
                    stack.pop();
                }else break;

            }

            stack.push(num);
        }



        return res;
    }

    public static void main(String[] args) {

        System.out.println(andXorOr(new int[]{9, 6, 3, 5, 2}));
    }
}

