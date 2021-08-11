package ProjectEuler;

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143 ?
 */
public class LargestPrimaryNumber {

    static long getLargestPrime( long num){

        while ( num%2 == 0){
            num /= 2;
        }

        for( long fac = 3; fac<num; fac+=2 ){

            while ( num%fac == 0 && fac<num){

                num/=fac;
            }
        }
        return num;
    }
    public static void main(String[] args) {

        String num = "15";
        System.out.println(getLargestPrime( Long.parseLong( num ) ));
    }
}
