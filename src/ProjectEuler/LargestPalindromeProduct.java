package ProjectEuler;

/**
 * A palindromic number reads the same both ways.
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class LargestPalindromeProduct {

    public static void main(String[] args) {

       System.out.println( getLargestPalindrome(3) );



    }

    private static int getLargestPalindrome(int limit) {

        int initial = 100;
        int last = 1000;
        int maxPalindrome = -1;

        for( int i = initial; i<last; i++){

            for( int j = initial; j<last; j++){

                int value = i * j;
                String valueStr = String.valueOf( value );

                if( checkPalindrome( valueStr ) ){

                    if( maxPalindrome < value){

                        maxPalindrome = value;
                    }
                }

            }

        }

        return maxPalindrome;
    }

//    static boolean checkPalindrome( String number){
//        if( number.length() <= 1){
//            return true;
//        }
//
//        if( number.substring(0,1) .equals( number.substring( number.length()-1))){
//            checkPalindrome( number.substring(1, number.length()-1 ));
//        }else{
//            return false;
//        }
//
//        return true;
//
//    }

    static boolean checkPalindrome( String number){

        StringBuilder input = new StringBuilder();
        String first = number;

        input.append( number);
        input = input.reverse();

        String reverse = input.toString();

        if( first.equals(reverse)){
            return true;
        }

        return false;

    }
}
