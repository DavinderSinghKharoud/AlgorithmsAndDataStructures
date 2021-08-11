package HackerRank;

//Write a factorial
// function that takes a positive integer,  as a parameter and prints the result of  ( factorial).
public class Factorial {

    public static void main(String[] args) {

        System.out.println(factorial(0));
    }

    static int factorial(int n) {
        if(n<=1){
            return 1;
        }else{
            return factorial(n-1) * n;
        }

    }
}
