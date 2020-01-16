package SortingAlgorithms;

public class SelectionSort {

    private static void sort(int[] arr) {

        for(int i = 0; i<arr.length; i++){

            int min = i;

            for(int j = i; j<arr.length; j++){

                if(arr[min] > arr[j]){

                    min = j;
                }

            }

            swap( arr, i, min );


        }
    }

    //swapping
    private static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;

    }


    public static void main(String[] args) {
        int []arr = {6,5,4,3,1,2,3,45,3,2};

        sort(arr);

        for(int elem: arr){
            System.out.println(elem);
        }

        sort(arr);


    }


}
