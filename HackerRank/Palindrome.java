package HackerRank;

public class Palindrome {

    public static void main(String[] args) {
        String checkString = "madame";
        if( checkPalindrome(checkString)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }


    }

    private static boolean checkPalindrome(String checkString) {
        if( checkString.length()<=1 ){
            return true;
        }

        if( checkString.charAt(0) == checkString.charAt(checkString.length()-1)){
            String submit =  checkString.substring(1, checkString.length()-1 );
            checkPalindrome( submit );
        }else{
            return false;
        }


        return true;

    }
}
