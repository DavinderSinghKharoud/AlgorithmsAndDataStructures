package algorthims.InterviewBit;

import java.util.*;

public class WindowString {

    //Time Limit Exceeded But correct
    //O( n square ) time complexity
    public static String minWindow(String s, String t) {

        int len1 = s.length();
        int len2 = t.length();

        if (len2 > len1) return "";

        int[] tArray = new int[128];
        for (char c : t.toCharArray()) {
            tArray[c]++;
        }

        int start = 0;
        int end = len2 - 1;
        int min = Integer.MAX_VALUE;
        int resStart = -1;
        int resEnd = -1;

        char[] sArray = s.toCharArray();
        while (end < len1 && start <= end) {

            if (isContained(sArray, start, end, tArray)) {

                if (end - start < min) {
                    min = end - start;
                    resStart = start;
                    resEnd = end;
                }
                start++;

            } else {
                end++;
            }


        }

        if (resStart == -1) return "";

        StringBuilder sbr = new StringBuilder();
        for (int index = resStart; index <= resEnd; index++) {
            sbr.append(sArray[index]);
        }

        return sbr.toString();

    }

    public static boolean isContained(char[] sArray, int start, int end, int[] tArray) {

        int[] curr = new int[128];
        for (int index = start; index <= end; index++) {
            curr[sArray[index]]++;
        }
        for (int index = 0; index < tArray.length; index++) {
            if (tArray[index] > curr[index]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println(minWindow2("ADOBECODEBANC", "ABC"));
    }

    //O( s + t ) time and space complexity
    public static String minWindow2(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        if (len2 > len1) return "";

        Map<Character, Integer> requiredCharacters = new HashMap<>();
        for (char c : t.toCharArray()) {
            requiredCharacters.put(c, requiredCharacters.getOrDefault(c, 0) + 1);
        }

        //Length of required characters
        int required = requiredCharacters.size();
        int formed = 0;
        int left = 0, right = 0;
        int[] ans = {-1, 0, 0};

        Map<Character, Integer> windowCounts = new HashMap<>();

        while (right < len1) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);

            //If t contains char
			if( requiredCharacters.containsKey(c) && windowCounts.get(c).intValue() == requiredCharacters.get(c).intValue() ){
			    formed++;
            }

			//Now we will contract the window
            while ( left <= right && formed == required ){
                c = s.charAt(left);
                //Update the ans
                if( ans[0] == -1 || right - left + 1 < ans[0] ){
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                //Reduce the character from the window
                windowCounts.put(c, windowCounts.get(c) - 1 );

                //Update the formed
                if( requiredCharacters.containsKey(c) && requiredCharacters.get(c) > windowCounts.get(c)){
                    formed--;
                }

                left++;
            }

            right++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

}
