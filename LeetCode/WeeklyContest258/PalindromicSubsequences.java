package LeetCode.WeeklyContest258;

import java.util.*;

public class PalindromicSubsequences {
    public static void main(String[] args) {
        PalindromicSubsequences o = new PalindromicSubsequences();
        System.out.println(o.maxProduct("aaaa"));
    }

    String s;
    boolean[][] vis;
    List<Integer> palindromes = new ArrayList<>();

    StringBuilder sbr = new StringBuilder();

    public int maxProduct(String s) {
        int len = s.length();
        this.s = s;
        int ans = -1;
        vis = new boolean[len][5000];
        findAll(0, 0);
        for (int i = 0; i < palindromes.size(); i++) {
//            if(palindromes.get(i) == 336){
//                System.out.println("stop");
//            }
            for (int j = i + 1; j < palindromes.size(); j++) {
                if (isGood(palindromes.get(i), palindromes.get(j))) {
                    ans = Math.max(ans, getLen(palindromes.get(i)) * getLen(palindromes.get(j)));
                }
            }
        }
        return ans;
    }

    private int getLen(int mask) {
        return Integer.bitCount(mask);
    }

    private boolean isGood(int a, int b) {
        //1 -> true
        //0 -> false
        for (int i = 0; i < s.length(); i++) {
            boolean first = (a & (1 << i)) > 0;
            boolean second = (b & (1 << i)) > 0;
            if (first && second) return false;
        }
        return true;
    }

    private void findAll(int index, int mask) {

        if (index == s.length()) {
            if (isPalindrome(mask)) {
                isPalindrome(mask);
                palindromes.add(mask);
            }
        } else {
            if (vis[index][mask]) return;
            vis[index][mask] = true;
            findAll(index + 1, mask);
            findAll(index + 1, (mask | (1 << index)));
        }
    }

    boolean isPalindrome(int mask) {
        sbr.delete(0, sbr.length());
        for (int i = 0; i < s.length(); i++) {
            if ((mask & (1 << i)) != 0) {
                sbr.append(s.charAt(i));
            }
        }
//        if(sbr.toString().equals("ete") || sbr.toString().equals("cdc")){
//            System.out.println("fou" +
//                    "");
//        }
        int start = 0, end = sbr.length() - 1;
        while (start <= end) {
            if (sbr.charAt(start) != sbr.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}
