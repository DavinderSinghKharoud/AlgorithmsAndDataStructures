package algorthims.InterviewBit;

import java.util.*;

public class SetMatrixZeros {

    //Time complexity O( mn * (m + n) )
    public static void setZeroes(List<List<Integer>> lst) {

        int len1 = lst.size();
        int len2 = lst.get(0).size();

        for (int row = 0; row < len1; row++) {
            for (int col = 0; col < len2; col++) {

                if (lst.get(row).get(col) == 0) {
                    submerge(lst, row, col);
                }
            }
        }

        for (int row = 0; row < len1; row++) {
            for (int col = 0; col < len2; col++) {

                if (lst.get(row).get(col) == -1) {
                    lst.get(row).set(col, 0);
                }
            }
        }

    }

    public static void submerge(List<List<Integer>> lst, int indexRow, int indexCol) {

        for (int col = 0; col < lst.get(0).size(); col++) {
            if (lst.get(indexRow).get(col) != 0) {
                lst.get(indexRow).set(col, -1);
            }
        }

        for (int row = 0; row < lst.size(); row++) {
            if (lst.get(row).get(indexCol) != 0) {
                lst.get(row).set(indexCol, -1);
            }
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> root = new ArrayList<>();
        root.add(Arrays.asList(1, 0, 1, 1));
        root.add(Arrays.asList(1, 1, 1, 1));
        root.add(Arrays.asList(1, 0, 1, 1));
        setZeroes2(root);
        System.out.println(root);
    }

    //Time complexity O(m*n) and O(1) space complexity
    public static void setZeroes2(List<List<Integer>> lst) {
        int len1 = lst.size();
        int len2 = lst.get(0).size();
        boolean isCol = false;
        for (List<Integer> integerList : lst) {

            if (integerList.get(0) == 0) {
                isCol = true;
            }
            for (int col = 1; col < len2; col++) {
                if (integerList.get(col) == 0) {

                    integerList.set(0, 0);
                    lst.get(0).set(col, 0);

                }
            }
        }

        for (int row = 1; row < len1; row++) {
            for (int col = 1; col < len2; col++) {
                if (lst.get(row).get(0) == 0 || lst.get(0).get(col) == 0 ) {
                    lst.get(row).set(col, 0);
                }
            }
        }

        if (lst.get(0).get(0) == 0) {
            for (int col = 0; col < len2; col++) {
                lst.get(0).set(col, 0);
            }
        }

        if(isCol){
            for (List<Integer> integers : lst) {
                integers.set(0, 0);
            }
        }

    }

}
