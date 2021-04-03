package Algorithms.LeetCode;

import java.util.*;

public class ReconstructItenary {

    public static void main(String[] args) {
        List<List<String>> lst = new ArrayList<>();
        String t = "MUC,LHR],JFK,MUC],SFO,SJC],LHR,SFO]]";
//      t = t.replaceAll("[", "");
        t = t.replaceAll("]", "");

        String[] split = t.split(",");
        for (int i = 0; i < split.length; i += 2) {
            String a = split[i];
            String b = split[i + 1];
            List<String> curr = new ArrayList<>();
            curr.add(a);
            curr.add(b);
            lst.add(curr);
        }
        ReconstructItenary obj = new ReconstructItenary();
        System.out.println(obj.findItinerary(lst));

    }

    Map<String, List<String>> tree = new HashMap<>();
    List<String> res = new ArrayList<String>();

    public List<String> findItinerary(List<List<String>> tickets) {

        for (List<String> ticket : tickets) {
            String a = ticket.get(0), b = ticket.get(1);
            List<String> curr = tree.getOrDefault(a, new LinkedList<>());
            curr.add(b);
            tree.put(a, curr);
        }

        for (Map.Entry<String, List<String>> entry : tree.entrySet()) {
            entry.getValue().sort(Comparator.reverseOrder());
        }

        traverse("JFK");
        List<String> clone = new ArrayList<>();
        for(int i = res.size() - 1; i >= 0; i--){
            clone.add(res.get(i));
        }
        return clone;
    }

    void traverse(String curr) {
        if (tree.containsKey(curr)) {
            while (!tree.get(curr).isEmpty()) {
                int len = tree.get(curr).size() - 1;
                String ele = tree.get(curr).remove(len);
                traverse(ele);
            }
        }
        res.add(curr);
    }
}
