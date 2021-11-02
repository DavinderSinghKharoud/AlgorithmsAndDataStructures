package Preparation;

import java.util.*;

public class LetterPhone {

    public ArrayList<String> letterCombinations(String s) {
        Map<Character, String[]> map = new HashMap<>();
        map.put('0', new String[]{"0"});
        map.put('1', new String[]{"1"});
        map.put('2', new String[]{"a", "b", "c"});
        map.put('3', new String[]{"d", "e", "f"});
        map.put('4', new String[]{"g", "h", "i"});
        map.put('5', new String[]{"j", "k", "l"});
        map.put('6', new String[]{"m", "n", "o"});
        map.put('7', new String[]{"p", "q", "r", "s"});
        map.put('8', new String[]{"t", "u", "v"});
        map.put('9', new String[]{"w", "x", "y", "z"});

        ArrayList<String> res = new ArrayList<>();
        generate(res, map, new StringBuilder(), s, 0);
        return res;
    }

    void generate(ArrayList<String> res, Map<Character, String[]> map, StringBuilder sbr, String s, int index) {
        if (index == s.length()) {
            res.add(sbr.toString());
        } else {

            for (String part : map.get(s.charAt(index))) {
                sbr.append(part);
                generate(res, map, sbr, s, index + 1);
                sbr.deleteCharAt(sbr.length() - 1);
            }
        }
    }
}
