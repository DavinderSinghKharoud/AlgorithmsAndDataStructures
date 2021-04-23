package Algorithms.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class MaximumValue {

    public static void main(String[] args) {

        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lst.add(i);
        }
        for (int i = 15; i > 6; i--) {
            lst.add(i);
        }
        System.out.println( max(lst));
    }

    public static int max( List<Integer> lst ){
        int x = -1;
        for (int b = lst.size() - 1; b >= 1; b /= 2) {
            while (x+b < lst.size() && x+b+1<lst.size() && lst.get(x+b) < lst.get(x+b+1)) {
                x += b;
            }
        }
        int k = x+1;
        return k;
    }
}
