/*
Given two numbers represented as strings, return multiplication of the numbers as a string.

 Note: The numbers can be arbitrarily large and are non-negative.
Note2: Your answer should not have leading zeroes. For example, 00 is not a valid answer. 
For example, 
given strings "12", "10", your answer should be “120”.

NOTE : DO NOT USE BIG INTEGER LIBRARIES ( WHICH ARE AVAILABLE IN JAVA / PYTHON ). 
We will retroactively disqualify such submissions and the submissions will incur penalties.
 */


import java.util.Arrays;

public class MultiplyStrings {

    public static String multiply(String first, String second) {
        char[] c1 = first.toCharArray();
        char[] c2 = second.toCharArray();
        int len1 = c1.length, len2 = c2.length;
        char[] res = new char[ len1 + len2 ];
        Arrays.fill(res, '0' );

        for (int j = len2 - 1; j >= 0; j--) {
            for (int i = len1 - 1; i >= 0; i--) {

                int product = ( c1[i] - '0' ) * ( c2[j] - '0' );

                //get the value if exist
                int temp = res[i + j + 1] - '0' + product;
                res[i + j + 1] = (char) ( temp % 10 + '0' );

                //update the previous value as remainder
                res[i + j] = (char) ( (res[i + j] - '0' + temp/10 ) + '0');
            }
        }

        StringBuilder sbr = new StringBuilder();
        boolean started = false;

        for( char c: res ){
            if( c == '0' && !started ) continue;
            sbr.append(c);
            started = true;
        }

        return (sbr.length() == 0 ) ? "0": sbr.toString();
    }

    public static void main(String[] args) {

        System.out.println(multiply("55", "51"));
    }
}

