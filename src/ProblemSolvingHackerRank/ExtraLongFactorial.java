package ProblemSolvingHackerRank;

import java.math.BigInteger;

//Calculate and print the factorial of very big integer.
public class ExtraLongFactorial {

    static void extraLongFactorials(int  n) {

        BigInteger factorial = BigInteger.valueOf(1);
        for(int i = n; i>0; i--){
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        System.out.println(factorial);
    }
    public static void main(String[] args) {

        extraLongFactorials(25);
    }
}
