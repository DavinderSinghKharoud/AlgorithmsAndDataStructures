package ProjectEuler;

public class HighlyDivisibleTriangularNumber {

    public static void main(String[] args) {

        getNumber(500);
    }

    private static void getNumber( int limit) {

        int j = 0;
        int n = 0;
        int numberOfDivisors = 0;

        while (numberOfDivisors <= limit) {

            numberOfDivisors = 0;
            j++;
            n = getTriange(j);

            for (int i = 1; i <= Math.sqrt(n); i++)
                if (n % i == 0)
                    numberOfDivisors++;

            //multiply it by 2 to include the other corresponding half
            numberOfDivisors *= 2;
        }


        System.out.println(n);
    }


    private static int getTriange(int triangle) {

        int sum = 0;
        for( int i = 1; i<=triangle;i++){
            sum+=i;
        }
        return sum;
    }
}
