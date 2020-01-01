package DivisorOfNumber;

import java.util.Scanner;

/**
 * The AdvancedArithmetic interface and the method declaration
 * for the abstract divisorSum(n) method are provided for you in the editor below.
 *
 * Complete the implementation of Calculator class, which implements the
 * AdvancedArithmetic interface. The implementation for the divisorSum(n) method
 * must return the sum of all divisors of n.
 */
class Calculator implements AdvancedArithmetic{

    @Override
    public int divisorSum(int n) {
        int sum = 0;
        for(int num=1; num<=n; num++){
            if(n%num == 0){
                sum+=num;
            }
        }
        return sum;
    }
}

public class DivisorSum {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number: ");
        int n = scan.nextInt();
        scan.close();

        AdvancedArithmetic myCalculator = new Calculator();
        int sum = myCalculator.divisorSum(n);
        System.out.println("I implemented: " + myCalculator.getClass().getInterfaces()[0].getName() );
        System.out.println(sum);
    }
}
