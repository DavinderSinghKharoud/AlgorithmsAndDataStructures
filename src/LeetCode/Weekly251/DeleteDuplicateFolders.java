package LeetCode.Weekly251;

import java.util.*;

public class DeleteDuplicateFolders {
    public static void main(String[] args) {
        DeleteDuplicateFolders o = new DeleteDuplicateFolders();
        System.out.println(o.deleteDuplicateFolder(Arrays.asList(Arrays.asList("a"),
                Arrays.asList("c"),
                Arrays.asList("d"),
                Arrays.asList("a", "b"),
                Arrays.asList("c", "b"),
                Arrays.asList("d", "a"))));
    }

    Trie root = new Trie("");
    List<List<String>> res = new ArrayList<>();
    Map<String, Integer> keys = new HashMap<>();

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {

        // Generate a tree
        for (List<String> path : paths) {
            add(path);
        }

        // Add a hashKey
        generateHash(root);

        // Remove a duplicate paths
        remove(root);

        // convert answer
        convertBack(root, new ArrayList<>());
        return res;
    }

    void convertBack(Trie curr, List<String> level) {
        if (level.size() != 0)
            res.add(new ArrayList<>(level));
        for (String child : curr.map.keySet()) {
            level.add(child);
            Trie c = curr.map.get(child);
            convertBack(c, level);
            level.remove(level.size() - 1);
        }
    }

    void remove(Trie curr) {
        if (curr.map.size() == 0)
            return;
        List<String> toDelete = new ArrayList<>();
        for (String childName : curr.map.keySet()) {
            Trie child = curr.map.get(childName);
            remove(child);
            if (keys.containsKey(child.key) && keys.get(child.key) > 1) {
                toDelete.add(childName);
            }
        }
        for (String s : toDelete) {
            curr.map.remove(s);
        }
    }

    void generateHash(Trie node) {
        StringBuilder s = new StringBuilder();
        if (node.map.size() == 0) {
            node.key = s.toString();
            return;
        }
        for (String child : node.map.keySet()) {
            Trie c = node.map.get(child);
            generateHash(c);
            s.append(child).append(c.key);
        }

        node.key = s.toString();
        keys.put(node.key, keys.getOrDefault(node.key, 0) + 1);
    }

    void add(List<String> path) {
        Trie curr = root;
        for (String a : path) {
            if (!curr.map.containsKey(a))
                curr.map.put(a, new Trie(a));
            curr = curr.map.get(a);
        }
    }

    static class Trie {
        String name, key;
        Map<String, Trie> map;

        public Trie(String name) {
            map = new HashMap<>();
            this.name = name;
        }
    }
}
