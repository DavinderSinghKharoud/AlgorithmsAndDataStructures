package algorthims.LeetCode;

public class ValidateIPaddressEasy {

    static boolean validateIP(String s) {

        StringBuilder sum = new StringBuilder();
        for( int i = 0; i<s.length(); i++ ){

            if( s.charAt(i) == '.' ){
                if( Integer.parseInt(sum.toString() ) > 255 ) return false;
                sum = new StringBuilder();
                continue;
            }

            if( Character.isDigit( s.charAt(i) ) ){
                sum.append(s.charAt(i) );
            }else{
                return false;
            }
        }

        if( Integer.parseInt(sum.toString() ) > 255 ) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println( validateIP("123.24.59.99"));

    }
}
