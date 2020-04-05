package algorthims.LeetCode;

import java.util.*;

public class A {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    static List<String> orderedJunctionBoxes(int numberOfBoxes, List<String> boxList)
    {
        List<String> oldBoxes = new ArrayList<>();
        List<String> newBoxes = new ArrayList<>();


        for( String item: boxList ){
            String[] box = item.split(" ");
            char c = box[1].charAt(0);
            Boolean isDigit = Character.isDigit(c);

            if( isDigit ){
                newBoxes.add(item);
            }else{
                oldBoxes.add(item);
            }
        }

        Comparator<String> cmp = (o1, o2) -> {
            String[] box1 = o1.split(" ");
            String[] box2 = o2.split(" ");
            StringBuilder str1 = new StringBuilder();
            StringBuilder str2 = new StringBuilder();

            for( int i = 1;  i<box1.length; i++ ){
                str1.append(box1[i]);
            }

            for( int i = 1; i<box2.length; i++ ){
                str2.append(box2[i]);
            }

            if( !str1.toString().equals(str2.toString()) ){
                return str1.compareTo(str2);
            }else{
                return box1[0].compareTo(box2[0]);
                //return (int) (parseStringToNumber(box1[0]) - parseStringToNumber(box1[0]));
            }
        };
        Collections.sort( oldBoxes, cmp );

        for( String box: newBoxes ){
            oldBoxes.add(box);
        }
        return oldBoxes;
        }

    public static void main(String[] args) {
        List<String> st = new ArrayList<>();
        st.add("09z cat hamster");
        st.add("236 cat dog");
        st.add("aa first qpx");
        st.add("a2 first qpx");
           List<String> res = orderedJunctionBoxes(2,st);

           for( String box: res ){
               System.out.println(box);
           }
    }

    private static double parseStringToNumber(String input){


        String DIGIT_AND_DECIMAL_REGEX = "[^\\d.]";
         String digitsOnly = input.replaceAll(DIGIT_AND_DECIMAL_REGEX, "");

        if("".equals(digitsOnly)) return 0;

        try{
            return Double.parseDouble(digitsOnly);
        }catch (NumberFormatException nfe){
            return 0;
        }
    }
}
