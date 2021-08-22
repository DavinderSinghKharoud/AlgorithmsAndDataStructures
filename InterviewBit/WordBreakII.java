/*
 * WordBreakII.java
 *
 * Copyright 2020 Davinder singh kharoud <davindersinghkharoud@Davinders-MacBook-Pro.local>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR FindGreatestCommonDivisor PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
 *
 */

import java.util.*;

public class WordBreakII {

    static ArrayList<String> res = new ArrayList<>();
    static Map<Integer, Boolean> map = new HashMap<>();

    public static ArrayList<String> wordBreak(String a, ArrayList<String> b) {
        int len = a.length();

        Set<String> set = new HashSet<>();

        if (len == 0) return res;

        int max = 0;
        for (String s : b) {
            max = Math.max(max, s.length());
            set.add(s);
        }


        helper(a, set, "", 0, max);
        return res;
    }

    public static boolean helper(String a, Set<String> set, String curr, int index, int max) {

        if (index == a.length()) {
            res.add(curr.trim());
            return true;
        }
        //because we will only add the index to the map, if it is not possible to split the string such that words are in the dictionary.
        if (map.containsKey(index) && !map.get(index) ) {
            return false;
        }

        boolean check = false;
        for (int end = index + 1; end <= a.length() && end - index <= max; end++) {
            String sub = a.substring(index, end);
            if (set.contains(sub) && helper(a, set, curr + " " + sub, end, max)) {
                check = true;
            }
        }

        if (!check) {
            map.put(index, false);
        }

        return true;
    }

    public static void main(String[] args) {
        ArrayList<String> arr = new ArrayList<>();
        arr.add("abb");
        arr.add("aab");
        arr.add("b");
        arr.add("a");
        arr.add("ab");
        arr.add("abaabbaa");
        //arr.add("cat");arr.add("cats");arr.add("and");arr.add("sand");arr.add("dog");
        System.out.println(wordBreak2("baababbbbabbbbbbbbabbbbbabbbb", arr));

        //System.out.println(wordBreak("catsanddog", arr ) );
    }

    //This one works for interview Bit
    public static ArrayList<String> wordBreak2(String A, ArrayList<String> B) {
        ArrayList<String> resList = new ArrayList<String>();

        wordBreakUtil(A, B, "", resList);

        return resList;
    }

    public static void wordBreakUtil(String A, ArrayList<String> B, String result, ArrayList<String> resList) {

        for(int i = 1; i <= A.length(); i++) {
            String prefix = A.substring(0, i);

            if(B.contains(prefix)) {

                if(i == A.length()) {
                    result += prefix;
                    resList.add(result);
                    return;
                }

                wordBreakUtil(A.substring(i), B, result + prefix + " ", resList);

            }
        }

    }
}

