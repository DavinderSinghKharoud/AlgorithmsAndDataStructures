package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LuckyNumberInMatrix {
    public static List<Integer> luckyNumbers(int[][] matrix) {
        if( matrix[0].length == 0){

            int min = Integer.MAX_VALUE;
            for( int i = 0; i<matrix[0].length; i++ ){
                min = Math.min(min, matrix[0][i]);

            }
        }

        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> mapY = new HashMap<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if (map.get(i) == null) {
                    map.put(i, matrix[i][j]);
                } else if(map.get(i) > matrix[i][j]) {
                    map.put(i, matrix[i][j]);
                }

                if (mapY.get(j) == null){
                    mapY.put(j, matrix[i][j]);
                }else if( mapY.get(j) < matrix[i][j]) {
                    mapY.put(j, matrix[i][j]);
                }
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for( int j = 0; j<matrix[0].length; j++ ){
                int x = map.get(i);
                int y = mapY.get(j);
                if ( x == y) {

                    res.add(map.get(i));
                }
            }
            }

        return res;
    }

    public static void main(String[] args) {

        System.out.println(luckyNumbers(new int[][]{
                {56216},{63251},{75772},{1945},{27014}
        }));
    }
}
