package ProjectEuler;

public class SpecificPrime {

    public static void main(String[] args) {

        System.out.println(getPrime( 10001 ));
    }

    private static long getPrime(int limit) {

        long count = 3;
        long currentNumber, primeCount;

        primeCount = 1;

        for( currentNumber = 3; primeCount!=limit; currentNumber+=2){

            for( count = 3; count<currentNumber; count+=2){

                if( currentNumber%count == 0){
                    break;
                }
            }


            if( count == currentNumber){
                primeCount++;
            }
        }

        return count;




    }
}
