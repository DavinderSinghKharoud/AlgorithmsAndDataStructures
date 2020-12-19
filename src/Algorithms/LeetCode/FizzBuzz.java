package Algorithms.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {

    public static List<String> fizzBuzz(int n) {

        List<String> result = new ArrayList<>();
        for (int i = 1; i <= n; i++) {

            if (i % 3 == 0 && i % 5 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(i + "");
            }


        }

        return result;
    }


        public List<String> fizzBuzz2(int n) {

            List<String>s=new ArrayList<String>();
            for(int i=1;i<=n;i++)
            {
                if(i%3==0 && i%5!=0)
                    s.add("Fizz");
                else if(i%5==0 && i%3!=0)
                    s.add("Buzz");
                else if(i%3==0 && i%5==0)
                    s.add("FizzBuzz");
                else
                    s.add(Integer.toString(i));
            }
            return s;
        }


    public static void main(String[] args) {

        System.out.println(fizzBuzz(15));
    }
}
