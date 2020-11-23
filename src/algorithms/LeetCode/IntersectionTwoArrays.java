package algorithms.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 */
public class IntersectionTwoArrays {

    public static int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> lst = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();


            for (int i = 0; i < nums1.length; i++) {
                if (map.get(nums1[i]) == null) {
                    map.put(nums1[i], 0);
                } else {
                    int value = map.get(nums1[i]);
                    map.put(nums1[i], ++value);
                }

            }

            for( int j = 0; j<nums2.length; j++ ){

                if( map.get( nums2[j]) != null && map.get( nums2[j]) >= 0 ){
                    int value = map.get( nums2[j] );
                    value--;
                    lst.add( nums2[j]);
                    map.put( nums2[j],value );
                }
            }

            int[] arr = new int[lst.size()];
            for ( int i = 0; i<arr.length; i++ ){
                arr[i] = lst.get(i);
            }

            for( int i = 0; i<arr.length; i++ ){
                System.out.println( arr[i]);
            }
            return arr;
    }

    public static void main(String[] args) {

         intersect(new int[]{
                 4,9,5

        }, new int[]{
                 9,4,9,8,4
        });
    }
}
