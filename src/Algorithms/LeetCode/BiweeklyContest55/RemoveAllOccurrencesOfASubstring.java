package Algorithms.LeetCode.BiweeklyContest55;

public class RemoveAllOccurrencesOfASubstring {
    public static void main(String[] args) {
        RemoveAllOccurrencesOfASubstring o = new RemoveAllOccurrencesOfASubstring();
        System.out.println(o.removeOccurrences("aa", "abc"));
    }

    public String removeOccurrences(String s, String part) {
        StringBuilder sbr = new StringBuilder(s);
        boolean isChanged = true;
        while (isChanged) {
            boolean isFound = false;
            for (int i = 0; i < sbr.length(); i++) {
                if (sbr.substring(i, Math.min(sbr.length(), part.length() + i)).equals(part)) {
                    sbr.delete(i, Math.min(sbr.length(), part.length() + i));
                    isFound = true;
                    break;
                }
            }
            if (!isFound)
                isChanged = false;
        }
        return sbr.toString();
    }
}
