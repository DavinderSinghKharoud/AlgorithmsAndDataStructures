package Preparation;

import java.util.*;

public class PrettyJson {
    public static void main(String[] args) {
        ArrayList<String> ans =
                new PrettyJson().prettyJSON("{\"attributes\":[{\"nm\":\"ACCOUNT\",\"lv\":[{\"v\":{\"Id\":null,\"State\":null},\"vt\":\"java.util.Map\",\"cn\":1}],\"vt\":\"java.util.Map\",\"status\":\"SUCCESS\",\"lmd\":13585},{\"nm\":\"PROFILE\",\"lv\":[{\"v\":{\"Party\":null,\"Ads\":null},\"vt\":\"java.util.Map\",\"cn\":2}],\"vt\":\"java.util.Map\",\"status\":\"SUCCESS\",\"lmd\":41962}]}\n");
        for(String s: ans){
            System.out.println(s);
        }
    }

    public ArrayList<String> prettyJSON(String s) {
        StringBuilder sbr = new StringBuilder();
        ArrayList<String> res = new ArrayList<>();

        Set<Character> starts = new HashSet<>();
        starts.add('[');
        starts.add('{');
        Set<Character> ends = new HashSet<>();
        ends.add(']');
        ends.add('}');

        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if( starts.contains(c) || ends.contains(c)){
                if(i + 1 < s.length() && s.charAt(i + 1) == ','){
                    res.add(c + ",");
                    i++;
                }else{
                    res.add(String.valueOf(c));
                }
            }else{
                sbr.delete(0, sbr.length());
                while( i < s.length() && !starts.contains(s.charAt(i)) &&
                        !ends.contains(s.charAt(i)) &&
                        s.charAt(i) != ','){
                    sbr.append(s.charAt(i++));
                }
                if(i < s.length() ){
                    if(s.charAt(i) == ','){
                        sbr.append(',');
                    }else i--;
                }
                res.add(sbr.toString());
            }
        }

        int count = 0;
        ArrayList<String> ans = new ArrayList<>();
        for (String re : res) {
            char c = re.charAt(0);
            if (starts.contains(c)) {
                ans.add(getString(re, count));
                count++;
            } else if (ends.contains(c)) {
                count--;
                ans.add(getString(re, count));
            } else {
                ans.add(getString(re, count));
            }
        }
        return ans;
    }

    String getString(String s, int count) {
        StringBuilder sbr = new StringBuilder();
        while (count-- > 0) sbr.append("\t");
        sbr.append(s);
        return sbr.toString();
    }
}
