package algorthims.LeetCode;

import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {

        Map<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String sorted = sortString(strs[i]);
            List<String> list = map.getOrDefault(sorted, new ArrayList<>());
            list.add(strs[i]);
            map.put(sorted, list);
        }

        return new ArrayList(map.values());
    }

    public static String sortString(String inputString) {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

    //Without sorting
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        int[] count = new int[26];

        for (String s : strs) {
            Arrays.fill(count, 0);
            for (Character c : s.toCharArray()) {
                count[ c - 'a']++;
            }
            StringBuilder sbr = new StringBuilder();
            for( int i = 0; i<26; i++){
                sbr.append("#");
                sbr.append(count[i]);
            }
            String key = sbr.toString();
            if(!map.containsKey(key)) map.put(key, new ArrayList());
            map.get(key).add(s);
        }

        return new ArrayList(map.values());
    }

    public static void main(String[] args) {

        List<List<String>> res = groupAnagrams(new String[]{
                "eat", "tea", "tan", "ate", "nat", "bat"
        });

        for (int i = 0; i < res.size(); i++) {
            List<String> lst = res.get(i);
            for (int j = 0; j < lst.size(); j++) {
                System.out.print(lst.get(j) + ",");
            }
            System.out.println();
        }
    }
}
