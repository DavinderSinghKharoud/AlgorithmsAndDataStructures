package GoogleKickStart;

import java.util.*;

public class Vestigium {

    public static List<Integer> solve(int[][] matrix, int len){
        List<Integer> result = new ArrayList<>();

        int total = len * ( len + 1)/2;
        int diagonal = 0;
        int rows = 0;
        int cols = 0;

        for (int i = 0; i < len; i++) {
            diagonal += matrix[i][i];
        }

        for (int i = 0; i < len; i++) {
            Set<Integer> seen = new HashSet<>();

            for (int j = 0; j < len; j++) {
                if( seen.contains(matrix[i][j]) ){
                    rows++;
                    break;
                }
                seen.add( matrix[i][j]);
            }
        }

        for (int i = 0; i < len; i++) {
            Set<Integer> seen = new HashSet<>();

            for (int j = 0; j < len; j++) {
                if( seen.contains(matrix[j][i]) ){
                    cols++;
                    break;
                }
                seen.add( matrix[j][i]);
            }
        }

        result.add( diagonal);
        result.add(rows);
        result.add(cols);
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int caseNum = input.nextInt();
        for (int ks = 1; ks <= caseNum; ks++) {
            int len = input.nextInt();
            int[][] matrix = new int[len][len];

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    matrix[i][j] = input.nextInt();
                }
            }

            List<Integer> lst = solve( matrix, len );

            System.out.println(String.format("Case #%d: %d %d %d", ks, lst.get(0), lst.get(1),lst.get(2)));
        }
    }
}
