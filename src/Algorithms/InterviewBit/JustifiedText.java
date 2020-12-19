/*
Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
You should pack your words in a greedy approach; that is, pack as many words as you can in each line.

Pad extra spaces ‘ ‘ when necessary so that each line has exactly L characters.
Extra spaces between words should be distributed as evenly as possible.
If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
For the last line of text, it should be left justified and no extra space is inserted between words.

Your program should return a list of strings, where each string represents a single line.

Example:

words: ["This", "is", "an", "example", "of", "text", "justification."]

L: 16.

Return the formatted lines as:

[
   "This    is    an",
   "example  of text",
   "justification.  "
]
 */

import java.util.*;

public class JustifiedText {

    //O(n) time complexity
    public static ArrayList<String> fullJustify(List<String> words, int limit) {

        ArrayList<String> res = new ArrayList<>();
        int len = words.size();
        if (limit == 0 || len == 0) return res;

        int index = 0;

        while ( index < len ){
            int totalChars = words.get(index).length();
            int last = index + 1;

            //add the words until we cannot
            while ( last < len ){
                if( totalChars + 1 + words.get(last).length() > limit ) break;

                totalChars += 1 + words.get(last).length();
                last++;
            }

            int gaps = last - index - 1;

            StringBuilder sb = new StringBuilder();

            if( last == len || gaps == 0 ){ //only single word or last line

                for (int i = index; i < last ; i++) {
                    sb.append(words.get(i)).append(" ");
                }
                sb.deleteCharAt( sb.length() - 1 );

                while ( sb.length() < limit ){
                    sb.append(" ");
                }

            }else{
                int spaces =  ( limit - totalChars) / gaps;
                int rest = ( limit - totalChars ) % gaps; //extra spaces

                for (int i = index; i < last - 1; i++) {
                    sb.append( words.get(i) ).append(" ");

                    //we need to add the spaces, but we assume one space is already added
                    for (int j = 0; j < spaces + ( i - index < rest ? 1: 0); j++) {
                        sb.append(' ');
                    }
                }

                //add the last word as we do not want extra space after that
                sb.append( words.get(last - 1) );

            }

            res.add(sb.toString());
            index = last;
        }

        return res;

    }

    public static void main(String[] args) {

        System.out.println(fullJustify(Arrays.asList("What", "must", "be", "shall", "be."), 12));
    }

}

