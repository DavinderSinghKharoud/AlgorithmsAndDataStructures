package Algorithms.LeetCode;

import java.util.*;

/**
 * Write an API that generates fancy sequences using the append, addAll, and multAll operations.
 *
 * Implement the Fancy class:
 *
 * Fancy() Initializes the object with an empty sequence.
 * void append(val) Appends an integer val to the end of the sequence.
 * void addAll(inc) Increments all existing values in the sequence by an integer inc.
 * void multAll(m) Multiplies all existing values in the sequence by an integer m.
 * int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.
 */
public class FancySequence {

    public static void main(String[] args) {
        FancySequence fancy = new FancySequence();
        fancy.append(3);   // fancy sequence: [2]
        fancy.append(7);
        fancy.multAll(4);
        fancy.addAll(6);
        fancy.append(7);
        fancy.append(3);

        System.out.println(fancy.getIndex(3));


    }

    private int mod = (int) 1e9 + 7;
    private long add = 0, mul = 1;
    List<Integer> list;

    public FancySequence() {
        list = new ArrayList<>();
    }

    public void append(int val) {
        list.add(appendNumber(val));
    }

    private int appendNumber(int n) {
        long modInverse = moduloInverse(mul, mod - 2);
        return (int) (modInverse * (n - add) % mod);
    }

    private long moduloInverse(long n, long pow) {
        if (n == 0)
            return 1;
        long res = 1;

        while (pow > 0) {
            if ((pow & 1) == 1) {
                // If it is odd
                res = (res * n) % mod;
            }
            pow /= 2;
            n = (n * n) % mod;

        }
        return res;
    }

    public void addAll(int inc) {
        add += inc;
        if (add >= mod) {
            add -= mod;
        }
    }

    public void multAll(int m) {
        mul = (mul * m) % mod;
        add = (add * m) % mod;

    }

    public int getIndex(int idx) {
        if (idx >= list.size())
            return -1;
        return getAns(list.get(idx) * mul + add);

    }

    private int getAns(long n) {
        n %= mod;
        if(n < 0){
             n += mod;
        }
        return (int) n;
    }
}
