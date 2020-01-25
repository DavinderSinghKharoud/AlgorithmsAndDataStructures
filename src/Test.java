public class Test {

    private static void changeArr(int[]arr, int n){
        arr[0] = 4;
         n = 3;
    }
    public static void main(String[] args) {
        int arr[] ={1, 2, 3};
        int n = 1;

        changeArr(arr, n);

        for(int ele: arr){
            System.out.println(ele);
        }

    }
}
