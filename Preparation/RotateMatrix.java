package Preparation;

import java.util.*;


public class RotateMatrix {
    //O(n * n) in place
    public void rotate(ArrayList<ArrayList<Integer>> arr) {
        int n = arr.size();

        int cut = 0;
        while (cut <= n - cut - 1) {
            //Swap the rows
            for (int j = 0; j < n; j++) {
                int temp = arr.get(cut).get(j);
                arr.get(cut).set(j, arr.get(n - cut - 1).get(j));
                arr.get(n - cut - 1).set(j, temp);
            }
            cut++;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int row = i, col = j;
                int temp = arr.get(row).get(col);
                arr.get(row).set(col, arr.get(col).get(row));
                arr.get(col).set(row, temp);
            }
        }
    }
}
