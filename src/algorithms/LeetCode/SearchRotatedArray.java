package algorithms.LeetCode;

import java.util.List;

/**
 * Suppose an array sorted in ascending order is rotated at some pivot
 * unknown to you beforehand.
 * (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
 * You are given a target value to search. If found in the array
 * return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 */
public class SearchRotatedArray {

    public static void main(String[] args) {

       int index =  searchElem( new int[]{
               4,5,6,7,8,1,2,3
        } , 8);

        System.out.println(index);
    }

    private static int searchElem(int[] arry, int value) {

        int start = 0;
        int end = arry.length-1;

        while ( start<=end){
            int mid = (end + start)/2;

            if( arry[mid] == value){
                return mid;
            }

            //Left side is sorted
            if( arry[start] <= arry[mid]){
                if( arry[start] <= value && value < arry[mid]){
                    end = mid-1;
                }else{
                    start = mid + 1;
                }

            }
            //Right side is sorted
            else{

                if( arry[mid] < value && value <= arry[end]){
                    start = mid + 1;
                }else {
                    end = mid - 1;
                }

            }

        }
        return -1;
    }


    //O( log(n) ) time complexity
    public static int search(final List<Integer> A, int B) {

        int pivot = -1;
        int len = A.size();
        if (len == 1) {
            if (A.get(0) == B) {
                return 0;
            } else {
                return -1;
            }
        }
        int start = 0;
        int end = A.size() - 1;
        int endNum = A.get(end);
        //Find the pivot around which array is rotated
        while (start <= end) {
            int mid = (end - start) / 2 + start;
            int num = A.get(mid);

            if (num == B) { //if exidentaly we found the key
                return mid;
            }

            if ((mid - 1 < 0 && A.get(mid + 1) > num) ||
                    (mid + 1 >= len && A.get(mid - 1) > num) ||
                    (A.get(mid - 1) > num && A.get(mid + 1) > num)) {
                pivot = mid;
                break;

            } else if (num >= endNum) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (pivot == -1) {
            //array is sorted
            return binarySearch(A, B, 0, A.size() - 1);
        } else if (B >= A.get(0) && B <= A.get(pivot - 1)) {
            return binarySearch(A, B, 0, pivot);
        } else {
            return binarySearch(A, B, pivot, A.size() - 1 );
        }
    }

    private static int binarySearch(List<Integer> lst, int key, int start, int end) {

        while ( start <= end ){
            int  mid = ( end - start )/2 + start;
            int num = lst.get(mid);
            if( num == key ){
                return mid;
            }else if( key <= num ){
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return -1;
    }
}
