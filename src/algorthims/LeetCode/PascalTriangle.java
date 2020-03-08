package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
    public static List<List<Integer>> generate(int numRows) {

        List<List<Integer>> result = new ArrayList<>();

        for( int i = 0; i<numRows; i++ ){

            List<Integer> lst = new ArrayList<>();
            if( i == 0){
                lst.add(1);
                result.add(lst);
                continue;
            }

            if( i == 1){
                lst.add(1);
                lst.add(1);
                result.add( lst );
                continue;
            }

            lst.add(1);
            for( int j = 1; j<=i-1; j++){
                int value = result.get( result.size() - 1).get( j - 1) + result.get( result.size() - 1).get( j );
                lst.add( value );
            }
            lst.add(1);
            result.add( lst );

        }
        return result;
    }

    public static void main(String[] args) {

        List<List<Integer>> lst = generate(5);

        for( List tempLst: lst){

            System.out.println(tempLst);
        }
    }
}
