package LeetCode;

import java.util.*;

public class TimeMap {

    public static void main(String[] args) {

        TimeMap o = new TimeMap();
        o.set("ctondw", "ztpearaw", 1);
        o.set("vrobykydll", "hwliiq", 2);
        o.set("gszaw", "ztpearaw", 3);
        o.set("ctondw", "gszaw", 4);

        System.out.println(o.get("gszaw", 5));
    }

    Map<String, TreeMap<Integer, String>> map;

    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap<>());
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if(map.containsKey(key)){
            TreeMap<Integer, String> treeMap = map.get(key);
            Integer index = treeMap.floorKey(timestamp);
            if(index != null){
                return treeMap.get(index);
            }
        }
        return "";
    }

    static class Node {
        String key, value;

        public Node(String k, String v) {
            key = k;
            value = v;
        }
    }
}
