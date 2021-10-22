package Preparation;

import java.util.ArrayList;

/**
 * Rearrange a given array so that Arr[i] becomes Arr[Arr[i]] with O(1) extra space.
 *
 * Example:
 *
 * Input : [1, 0]
 * Return : [0, 1]
 * Lets say N = size of the array. Then, following holds true :
 *
 * All elements in the array are in the range [0, N-1]
 * N * N does not overflow for a signed integer
 */
public class RearrangeArray {
    public void arrange(ArrayList<Integer> lst) {
        int size = lst.size();
        // A = B + C* n
        // A % N = B
        // (A - B)/ N = C

        for(int i = 0; i < size; i++){
            int curr = lst.get(i);
            int  fill = lst.get(curr);
            if(curr < i){
                //Already modified
                fill = (fill % size);
            }
            lst.set(i, curr + (fill * size));
            // System.out.println(lst.get(i) + " " + fill);
        }

        for(int i = 0; i < size; i++){
            //  System.out.println(lst.get(i));
            int curr = lst.get(i);
            int fill = ( curr - (curr % size))/size;
            lst.set(i, fill);
        }
    }

}
