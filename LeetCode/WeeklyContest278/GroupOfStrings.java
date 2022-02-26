package LeetCode.WeeklyContest278;

import java.util.*;

/**
 * You are given a 0-indexed array of strings words. Each string consists of lowercase English letters only. No letter occurs more than once in any string of words.
 *
 * Two strings s1 and s2 are said to be connected if the set of letters of s2 can be obtained from the set of letters of s1 by any one of the following operations:
 *
 * Adding exactly one letter to the set of the letters of s1.
 * Deleting exactly one letter from the set of the letters of s1.
 * Replacing exactly one letter from the set of the letters of s1 with any letter, including itself.
 * The array words can be divided into one or more non-intersecting groups. SortEvenAndOdd string belongs to a group if any one of the following is true:
 *
 * It is connected to at least one other string of the group.
 * It is the only string present in the group.
 * Note that the strings in words should be grouped in such a manner that a string belonging to a group cannot be connected to a string present in any other group. It can be proved that such an arrangement is always unique.
 *
 * Return an array ans of size 2 where:
 *
 * ans[0] is the total number of groups words can be divided into, and
 * ans[1] is the size of the largest group.
 *
 *
 * Example 1:
 *
 * Input: words = ["a","b","ab","cde"]
 * Output: [2,3]
 * Explanation:
 * - words[0] can be used to obtain words[1] (by replacing 'a' with 'b'), and words[2] (by adding 'b'). So words[0] is connected to words[1] and words[2].
 * - words[1] can be used to obtain words[0] (by replacing 'b' with 'a'), and words[2] (by adding 'a'). So words[1] is connected to words[0] and words[2].
 * - words[2] can be used to obtain words[0] (by deleting 'b'), and words[1] (by deleting 'a'). So words[2] is connected to words[0] and words[1].
 * - words[3] is not connected to any string in words.
 * Thus, words can be divided into 2 groups ["a","b","ab"] and ["cde"]. The size of the largest group is 3.
 * Example 2:
 *
 * Input: words = ["a","ab","abc"]
 * Output: [1,3]
 * Explanation:
 * - words[0] is connected to words[1].
 * - words[1] is connected to words[0] and words[2].
 * - words[2] is connected to words[1].
 * Since all strings are connected to each other, they should be grouped together.
 * Thus, the size of the largest group is 3.
 *
 *
 * Constraints:
 *
 * 1 <= words.length <= 2 * 104
 * 1 <= words[i].length <= 26
 * words[i] consists of lowercase English letters only.
 * No letter occurs more than once in words[i].
 */
public class GroupOfStrings {

    public static final int CHARS_LEN = 26;
    Union unionSet;
    Map<Integer, Integer> wordsMap = new HashMap<>(), delWordsMap = new HashMap<>();

    public static void main(String[] args) {
    }

    public int[] groupStrings(String[] words) {
        int len = words.length;
        unionSet = new Union(len);
        for (int index = 0; index < len; index++) {
            String word = words[index];
            process(word, index);
        }
        return new int[]{unionSet.getGroupCount(), unionSet.getMaxGroupSize()};
    }

    private void process(String word, int index) {
        int mask = getMask(word);
        for (int charIndex = 0; charIndex < CHARS_LEN; charIndex++) {
            if (isCharExist(mask, charIndex)) {
                int maskAfterDelete = toggleMaskAtIndex(mask, charIndex);
                if (wordsMap.containsKey(maskAfterDelete)) {
                    unionSet.uniteIfPossible(index, wordsMap.get(maskAfterDelete));
                }
                if (delWordsMap.containsKey(maskAfterDelete)) {
                    unionSet.uniteIfPossible(index, delWordsMap.get(maskAfterDelete));
                } else {
                    delWordsMap.put(maskAfterDelete, index);
                }
            } else {
                int maskAfterAddition = toggleMaskAtIndex(mask, charIndex);
                if (wordsMap.containsKey(maskAfterAddition)) {
                    unionSet.uniteIfPossible(index, wordsMap.get(maskAfterAddition));
                }
            }
        }
        wordsMap.put(mask, index);
    }

    private boolean isCharExist(int mask, int charIndex) {
        return (mask & (1 << charIndex)) > 0;
    }

    private int toggleMaskAtIndex(int mask, int charIndex) {
        return mask ^ (1 << charIndex);
    }

    private int getMask(String word) {
        int mask = 0;
        for (int index = 0; index < word.length(); index++) {
            char c = word.charAt(index);
            mask |= (1 << (c - 'a'));
        }
        return mask;
    }
}

class Union {
    int[] parent, size;
    int groupCount;

    public Union(int len) {
        initSizeArray(len);
        initParentArray(len);
        groupCount = len;
    }

    int findParent(int index) {
        if (parent[index] == index) return index;
        return parent[index] = findParent(parent[index]);
    }

    void uniteIfPossible(int index1, int index2) {
        int parent1 = findParent(index1), parent2 = findParent(index2);
        if (parent1 != parent2) {
            unite(index1, index2);
        }
    }

    private void unite(int index1, int index2) {
        int parent1 = findParent(index1), parent2 = findParent(index2);
        if (size[parent2] > size[parent1]) {
            parent1 = parent2 ^ parent1 ^ (parent2 = parent1);
        }
        parent[parent2] = parent1;
        size[parent1] += size[parent2];

        size[parent2] = 0;

        decreaseGroupCount();
    }

    private void initParentArray(int len) {
        parent = new int[len];
        for (int index = 0; index < len; index++) {
            parent[index] = index;
        }
    }

    private void initSizeArray(int len) {
        size = new int[len];
        Arrays.fill(size, 1);
    }

    private void decreaseGroupCount() {
        groupCount--;
    }

    public int getGroupCount() {
        return groupCount;
    }

    public int getMaxGroupSize() {
        return Arrays.stream(size).max().getAsInt();
    }
}