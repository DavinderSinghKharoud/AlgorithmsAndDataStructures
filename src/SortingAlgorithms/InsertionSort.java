package SortingAlgorithms;

public class InsertionSort {

    private static void sort(int[] arr) {

        int key, checkIndex;

        for( int i = 1; i<arr.length; i++){

            key = arr[i];
            checkIndex = i - 1;

            while( checkIndex>=0 && arr[checkIndex] > key){

                arr[ checkIndex+1 ] = arr[checkIndex];
                checkIndex = checkIndex - 1;
            }

            arr[ checkIndex + 1] = key;

        }
    }


    public static void main(String[] args) {

        int []arr = {3,1,2};

        sort(arr);

        for( int elem: arr){

            System.out.println(elem);
        }
    }


}
