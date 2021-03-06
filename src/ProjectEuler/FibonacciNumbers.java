package ProjectEuler;

/**
 * Each new term in the Fibonacci sequence is generated by adding the previous two terms.
 * By starting with 1 and 2, the first 10 terms will be:
 *
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 *
 * By considering the terms in the Fibonacci sequence whose values do not exceed four million,
 * find the sum of the even-valued terms.
 */
public class FibonacciNumbers {

    public static void main(String[] args) {

        System.out.println(getFibonacci( 4000000));
    }

    private static int getFibonacci(int limit) {
        int first = 1;
        int second = 2;

        int sum = 0;

        do{

            int next = first + second;

            if( next%2 == 0){
                sum += next;
            }

            first = second;
            second = next;
        }
        while( sum<= limit);

        return sum + 2;

    }
}
