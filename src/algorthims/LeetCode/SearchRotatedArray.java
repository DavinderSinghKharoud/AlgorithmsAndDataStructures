package algorthims.LeetCode;

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
}
