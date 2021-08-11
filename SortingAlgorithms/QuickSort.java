package SortingAlgorithms;

public class QuickSort {

    private static void Quicksort(int[] arr, int low, int high) {

        if( low<high ){

            int correctPlace = partition(arr, low, high);
            Quicksort( arr, low, correctPlace - 1);
            Quicksort( arr, correctPlace+1, high);

        }

    }

    private static int partition(int[] arr, int low, int high) {

        int pivot = arr[low];
        int border = low + 1;

        for ( int j = border; j<=high; j++){

            if( arr[j] <= pivot ){
                swap(arr, border, j);
                border++;
            }
        }

        swap( arr,low, border - 1);

        return border - 1;
    }

    private static void swap(int[] arr, int first, int second) {


        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public static void main(String[] args) {
        int []arr = {5,4,3,2,1,3,2,4};

        Quicksort(arr, 0, arr.length-1);

        for(int elm: arr){
            System.out.println(elm);
        }
    }


}
