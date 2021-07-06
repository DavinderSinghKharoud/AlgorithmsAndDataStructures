package Algorithms.LeetCode;

import java.util.*;

public class LongestDuplicate {

    public static void main(String[] args) {
        LongestDuplicate o = new LongestDuplicate();
        System.out.println(o.longestDupSubstring(
                "okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajvielokmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel"));
    }

    public String longestDupSubstring(String s) {
        int start = 1, end = s.length() - 1;
        String res = "";
        while (start <= end) {
            int mid = (end + start) / 2;
            String poss = rabinCarp(s, mid);
            if (poss != null) {
                res = poss;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return res;
    }

    String rabinCarp(String s, int len) {
        Set<Long> set = new HashSet<>();
        long h = hash(s.substring(0, len));
        set.add(h);
        long pow = 1;
        for (int l = 1; l < len; l++) {
            pow = mul(pow, 31);
        }

        for (int i = 1; i < s.length() - len + 1; i++) {
            h = nextHash(pow, h, s.charAt(i - 1), s.charAt(i + len - 1));
            if (set.contains(h))
                return s.substring(i, i + len);
            set.add(h);
        }
        return null;
    }

    long mod = 999900004679L;

    long hash(String s) {
        long hash = 0;
        long mul = 1;
        // a * 31 ^ 2 + b * 31 ^ 1 + c * 1
        for (int i = s.length() - 1; i >= 0; i--) {
            hash = (hash + mul(s.charAt(i), mul)) % mod;
            mul = mul(mul, 31);
        }
        return hash;
    }

    long nextHash(long pow, long hash, char left, char right) {
        return (mul(hash - mul(left, pow) + mod, 31) + right) % mod;
    }

    long sec = 100000002359L;
    long third = 999900004679L;

    long mul(long a, long b) {
        return (a * b) % mod;
    }
}
