package algorthims.LeetCode;

import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> res = new ArrayList<>();
        List<String> lst = new ArrayList<>();
        Map<String,List<String>> map = new HashMap<>();

        for( int i = 0; i<strs.length; i++ ){
            String sorted = sortString(strs[i]);
            List<String> list = map.getOrDefault(sorted,new ArrayList<>());
            list.add(strs[i]);
            map.put(sorted,list);
        }

        for(String key: map.keySet()){
            res.add( map.get(key));
        }

        return res;
    }

    public static String sortString(String inputString) {
        // convert input string to char array
        char tempArray[] = inputString.toCharArray();

        // sort tempArray
        Arrays.sort(tempArray);

        // return new sorted string
        return new String(tempArray);
    }

    public static void main(String[] args) {

        List<List<String>> res = groupAnagrams(new String[]{
                "eat", "tea", "tan", "ate", "nat", "bat"
        });

        for( int i = 0; i<res.size(); i++ ){
            List<String> lst = res.get(i);
            for( int j = 0; j<lst.size(); j++){
                System.out.print( lst.get(j) +",");
            }
            System.out.println();
        }
    }
}
