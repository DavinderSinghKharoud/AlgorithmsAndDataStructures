package Preparation;

/**
 * FindingThreeDigitEvenNumbers complex number can be represented as a string on the form "real+imaginaryi" where:
 *
 * real is the real part and is an integer in the range [-100, 100].
 * imaginary is the imaginary part and is an integer in the range [-100, 100].
 * i2 == -1.
 * Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.
 *
 *
 *
 * Example 1:
 *
 * Input: num1 = "1+1i", num2 = "1+1i"
 * Output: "0+2i"
 * Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
 * Example 2:
 *
 * Input: num1 = "1+-1i", num2 = "1+-1i"
 * Output: "0+-2i"
 * Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.
 */
public class ComplexNumberMultiplication {

    public String complexNumberMultiply(String num1, String num2) {

        //(a + b)(a + b)
        int firstReal = getReal(num1);
        int secondReal = getReal(num2);
        String firstIma = getIm(num1);
        String secondIma = getIm(num2);

        int firstPart = firstReal * secondReal;
        System.out.println(getReal(firstIma) + " " + firstIma);
        String secondPart = ((getReal(firstIma) * secondReal)+ (firstReal * getReal(secondIma))) + "i";

        int thirdPart = -1 * (getReal(firstIma) * getReal(secondIma));

        StringBuilder sbr = new StringBuilder();
        System.out.println(firstPart + " " + thirdPart + " " + secondPart);
        sbr.append(firstPart).append(thirdPart).append("+").append(secondPart);

        return sbr.toString();
    }

    String getIm(String s){
        int index = 0;
        while(index <s.length() && s.charAt(index) != '+') index++;
        index++;
        StringBuilder sbr = new StringBuilder();
        for(;index < s.length(); index++){
            sbr.append(s.charAt(index));
        }
        return sbr.toString();
    }

    String mulRealAndIm(int real, String im){
        int secondReal = getReal(im);
        int product = real * secondReal;
        return product + "i";
    }
    int getReal(String s){
        int res = 0;
        boolean isNeg = false;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                res = res * 10 + (c - '0');
            }else{
                if(i == 0){
                    if( c == '-'){
                        isNeg = true;
                    }
                    continue;
                }
                break;
            }
        }
        return res * ((isNeg)?-1:1);
    }
}
