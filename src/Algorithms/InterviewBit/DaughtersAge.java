package Algorithms.InterviewBit;

import java.util.*;

/**
 * Two MIT math grads bump into each other at Fairway on the upper west side. They haven’t seen each other in over 20 years.
 *
 * The first grad says to the second: “how have you been?”
 * Second: “great! i got married and i have three daughters now”
 * First: “really? how old are they?”
 * Second: “well, the product of their ages is 72, and the sum of their ages is the same as the number on that building over there..”
 * First: “right, ok.. oh wait.. hmm, i still don’t know”
 * Second: “oh sorry, the oldest one just started to play the piano”
 * First: “wonderful! my oldest is the same age!”
 */
public class DaughtersAge {

    public static void main(String[] args) {
        int a;
        int b;
        int c;

        Set<String> set = new HashSet<>();
        for (int first = 1; first < 20; first++) {
            for (int second = 1; second < 19; second++) {
                for (int third = 1; third < 19; third++) {

                    if( first * second * third == 72 ){
                        a = first;
                        b = second;
                        c = third;
                        List<Integer> lst = new ArrayList<>();
                        lst.add(a);
                        lst.add(b);
                        lst.add(c);
                        Collections.sort(lst);
                        StringBuilder res = new StringBuilder();
                        for(Integer num: lst){
                            res.append(num);
                        }
                        if( !set.contains(res.toString()) ){
                            set.add(res.toString());

                            System.out.println( a + "," + b + "," + c + "sum: " + (a + b + c));
                        }
                    }
                }
            }
        }

        //All sums are unique except 14. So the age sum must have been 14, otherwise Shyam would have guessed the ages from hint 2 only.
        //he has an oldest girl (not two!!). So the ages must be 3, 3 and 8.
    }


}
