package SortingAlgorithms;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {5, 4, 3, 2, 1, 0};
        int low = 0;
        int high = arr.length - 1;

        for(int elem: arr){
            System.out.print(elem + " ");
        }
        System.out.println("\n Sorted arr");

        sortArray(arr, low, high);
        for(int elem: arr){
            System.out.print(elem + " ");
        }
    }

    private static void sortArray(int[] arr, int low, int high) {


        if (low < high) {
            int mid = (low + high) / 2;

            sortArray(arr, low, mid);
            sortArray(arr, mid + 1, high);

            merge(arr, low, mid, high);
        }

    }

    private static void merge(int[] arr, int low, int mid, int high) {

        //getting size
        int n1 = mid - low + 1;
        int n2 = high - mid;

        //creating two arrays
        int Left[] = new int[n1];
        int Right[] = new int[n2];

        //copying data
        for (int i = 0; i < n1; ++i) {

            Left[i] = arr[low + i];
        }
        for (int j = 0; j < n2; ++j) {

            Right[j] = arr[mid + j + 1];

        }

        int leftIndex = 0, rightIndex = 0;
        int finalListIndex = low;

        while (leftIndex < n1 && rightIndex < n2) {

            if (Left[leftIndex] <= Right[rightIndex]) {

                arr[finalListIndex] = Left[leftIndex];
                leftIndex++;
            } else {
                arr[finalListIndex] = Right[rightIndex];
                rightIndex++;
            }

            finalListIndex++;


        }

        //Copy remaining elements
        while (leftIndex < n1) {

            arr[finalListIndex] = Left[leftIndex];
            leftIndex++;
            finalListIndex++;
        }

        while (rightIndex < n2) {

            arr[finalListIndex] = Right[rightIndex];
            rightIndex++;
            finalListIndex++;

        }
    }

}