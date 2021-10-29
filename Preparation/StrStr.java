package Preparation;

public class StrStr {

    public static void main(String[] args) {
        System.out.println(new StrStr().strStr2("aabaaaababaabbbabbabbbaabababaaaaaababaaabbabbabbabbaaaabbbbbbaabbabbbbbabababbaaabbaabbbababbb",
                "bba"));
        System.out.println(new StrStr().strStr("aab",
                "aab"));
    }


    //KMP
    public int strStr2(final String hayStack, final String needle) {
        int len1 = hayStack.length(), len2 = needle.length();
        if (len1 == 0 || len2 == 0 || len2 > len1) return -1;

        int[] pattern = new int[len2 + 1];

        int start = 0, end = 1;
        while (start < end && end < len2) {
            if (needle.charAt(start) == needle.charAt(end)) {
                start++;
                pattern[end + 1] = start;
                end++;
            } else {
                if (start == 0) {
                    end++;
                }
                start = pattern[start];
            }
        }

        int nIndex = 0;
        int hIndex = 0;

        while (nIndex < len2 && hIndex < len1) {
            if (hayStack.charAt(hIndex) == needle.charAt(nIndex)) {
                hIndex++;
                nIndex++;
                if (nIndex == len2) {
                    //Found the answer
                    System.out.println(hIndex + " " + nIndex);
                    return hIndex - nIndex ;
                }
            } else {
                if (nIndex == 0) {
                    hIndex++;
                }
                nIndex = pattern[nIndex];
            }
        }
        return -1;

    }

    long[] pows;
        //Rabin Carp
    public int strStr(final String hayStack, final String needle) {
        int base = 31;
        int len = hayStack.length(), nlen = needle.length();
        if (len == 0 || nlen == 0 || nlen > len) return -1;
        pows = new long[nlen + 1];
        pows[1] = base;
        for (int i = 2; i < pows.length; i++) {
            pows[i] = mul(pows[i - 1], base);
        }

        long target = needle.charAt(nlen - 1);
        int mulPow = 1;
        for (int i = nlen - 2; i >= 0; i--) {

            target = add(target, mul(needle.charAt(i), pows[mulPow++]));
        }

        //Traverse the whole hayStack
        long curr = hayStack.charAt(nlen - 1);
        mulPow = 1;
        for (int i = nlen - 2; i >= 0; i--) {
            //System.out.println(i + " " + mulPow);
            curr = add(curr, mul(hayStack.charAt(i), pows[mulPow++]));
        }

        if (curr == target) return 0;
        for (int end = nlen; end < len; end++) {
            curr = addNext(curr, hayStack.charAt(end - nlen), hayStack.charAt(end)
                    , pows[nlen - 1]);

            if (curr == target) return end - nlen + 1;
        }
        return -1;

    }

    long addNext(long val, int old, int add, long maxPow) {
        return add(add, mul((val - (mul(old, maxPow))), 31));
    }

    long add(long a, long b) {
        return (a + b);
    }

    long mul(long a, long b) {
        return (a * b);
    }
}

