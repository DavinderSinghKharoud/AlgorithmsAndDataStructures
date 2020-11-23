package algorithms.InterviewBit;

import java.util.*;

/**
 * Remove Element
 *
 * Given an array and a value, remove all the instances of that value in the array.
 * Also return the number of elements left in the array after the operation.
 * It does not matter what is left beyond the expected length.
 *
 *  Example:
 * If array A is [4, 1, 1, 2, 1, 3]
 * and value elem is 1,
 * then new length is 3, and A is now [4, 2, 3]
 * Try to do it in less than linear additional space complexity.
 */
public class RemoveElementFromArray {


    public static int removeElement(ArrayList<Integer> a, int b) {

        int setIndex = 0;
        for( int index = 0; index < a.size(); index++ ){
            if( a.get(index) != b ){
                a.set(setIndex++, a.get(index));
            }

        }
        return setIndex;
    }
    public static void main(String[] args) {
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(4);
        lst.add(1);
        lst.add(1);
        lst.add(2);
        lst.add(1);
        lst.add(3);
        System.out.println( removeElement2(lst, 1));
    }

    public static int removeElement2(ArrayList<Integer> a, int b) {


        int index = 0;
        int end = a.size() - 1;
        while ( index < a.size() ){
            if( a.get(index) == b ){
                a.set(index, a.get(end));
                end--;
            }else{
                index++;
            }
        }

        return end;
    }


    }
