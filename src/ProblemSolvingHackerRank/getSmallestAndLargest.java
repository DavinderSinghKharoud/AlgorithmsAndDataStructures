package ProblemSolvingHackerRank;

import java.lang.reflect.Array;

public class getSmallestAndLargest {

    public static String getSmallestAndLargest(String s, int k) {
        String smallest = "";
        String largest = "";
        String []arr = new String[ s.length() - (k-1)];

        for(int i = 0 ; i<s.length()-(k-1); i++){

            arr[i] = s.substring(i, i+k);

        }

        java.util.Arrays.sort(arr);

        smallest = arr[0];
        largest = arr[arr.length-1];



        return smallest + "\n" + largest;
    }

    public static void main(String[] args) {

        System.out.println(getSmallestAndLargest("welcometojava",3));
    }
}
