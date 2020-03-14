package algorthims.LeetCode;

public class CountPrimes {

    public static int countPrimes(int n) {
        if( n == 1){
            return 0;
        }
        boolean[] arr = new boolean[n];

        int count = 0;
        for (int i = 2; i < n; i++) {

            if (arr[i] != true) {
                checkPrime(arr, i);
                count++;
            }
        }
        return count;
    }

    private static void checkPrime(boolean[] arr, int index) {

        for (int i = index; i < arr.length; i++) {
            if( i % index == 0){
                arr[i] = true;
            }
        }

    }

    public static void main(String[] args) {

        System.out.println(countPrimes(10));
    }
}
