package SortingAlgorithms;

public class BubbleSort {


    private static void sort(int[] arr) {

        for( int roll = 0; roll<arr.length; roll++){

            for( int index = 0; index<arr.length - 1; index++){

                if( arr[index] > arr[index+1]){

                    swap( arr, index, index+1);
                }

            }
        }
    }

    private static void swap(int[] arr, int first, int second) {


        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;

    }


    public static void main(String[] args) {

        int[] arr = {4,3,2,1,3,2,4,4,5};

        sort(arr);

        for( int elem: arr){

            System.out.println(elem);
        }
    }

}
