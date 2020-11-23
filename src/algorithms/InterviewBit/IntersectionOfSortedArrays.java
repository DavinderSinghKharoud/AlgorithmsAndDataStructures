package algorithms.InterviewBit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionOfSortedArrays {

    public static ArrayList<Integer> intersect(final List<Integer> lst1, final List<Integer> lst2) {

        int len1 = lst1.size();
        int len2 = lst2.size();
        ArrayList<Integer> res = new ArrayList<>();
        if (len1 == 0 || len2 == 0) {
            return res;
        }

        int index1 = 0, index2 = 0;

        while ( index1 < len1 && index2 < len2 ){
            int num1 = lst1.get(index1);
            int num2 = lst2.get(index2);
            if( num1 == num2 ){
                res.add(num1);
                index1++;
                index2++;
            }else if( num1 < num2 ){
                index1++;
            }else{
                index2++;
            }
        }
        return res;
    }

    public static void main(String[] args) {


        System.out.println(intersect(Arrays.asList(1, 2, 3, 3, 4, 5, 5, 6, 6), Arrays.asList(3, 3, 3, 5, 6, 6)));
    }
}
