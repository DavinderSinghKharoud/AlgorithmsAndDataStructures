package Templates.Sieve;

import java.util.*;

public class Sieve {
    public static void main(String[] args) {
        Sieve o = new Sieve();
        //System.out.println(o.segmentedSieve((int) 1e8, (int) 1e8 + 1000));
        System.out.println(o.countPrimes((int) 1e8));
    }

    int countPrimes(int n) {
        int size = 10000, res = 0;

        List<Integer> primes = sieve(n);

        boolean[] block = new boolean[size];
        for (int i = 0; i * size <= n; i++) {
            Arrays.fill(block, true);
            int start = i * size;
            for (int prime : primes) {
                int startInd = (start + prime - 1) / prime;
                int j = Math.max(prime, startInd) * prime - start;
                for (; j < size; j += prime) {
                    block[j] = false;
                }
            }
            if (i == 0) {
                block[0] = block[1] = false;
            }
            for (int k = 0; k < size && start + k <= n; k++) {
                if (block[k])
                    res++;
            }
        }
        return res;
    }


    List<Integer> sieve(int n) {
        int lim = (int) Math.sqrt(n);
        List<Integer> lst = new ArrayList<>();
        boolean[] isNotPrime = new boolean[lim + 1];

        for (int i = 2; i <= lim; i++) {
            if (!isNotPrime[i]) {
                lst.add(i);
                for (int j = i * i; j <= lim; j += i) {
                    isNotPrime[j] = true;
                }
            }
        }
        return lst;
    }

    List<Integer> segmentedSieve(int l, int r) {
        // generate all primes up to r
        List<Integer> primesSqrt = sieve(r);

        boolean[] notPrime = new boolean[r - l + 1];
        for (int prime : primesSqrt) {
            int div = (l / prime * prime);
            if (div < l)
                div += prime;
            for (int i = div; i <= r; i += prime) {
                notPrime[i - l] = true;
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < notPrime.length; i++) {
            if (!notPrime[i])
                res.add(l + i);
        }
        return res;
    }

}
