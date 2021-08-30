package HackerRank.Test;

import java.util.*;

public class Pairing {
   public static void main(String[] args) {
      Pairing o = new Pairing();
   }

   public static long taskOfPairing(List<Long> freq) {
      long pairs = 0;
      int len = freq.size();
      for (int i = len - 1; i >= 0; i--) {
         if (i == 0) {
            pairs += freq.get(i) / 2;
         } else {
            long curr = freq.get(i);
            if (curr % 2 == 0) {
               pairs += curr / 2;
            } else {
               // If it is odd
               pairs += curr / 2;
               if (freq.get(i - 1) != 0) {
                  pairs++;
                  freq.set(i - 1, freq.get(i - 1) - 1);
               }
            }
         }
      }
      return pairs;
   }
}
