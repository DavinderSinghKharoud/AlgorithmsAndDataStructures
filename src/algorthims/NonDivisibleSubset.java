package algorthims;

import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NonDivisibleSubset {


    public static int nonDivisibleSubset(int[]arr,int N, int K){
        int maxSize = 0;

        int[] modulus = new int[K];

        //Place the values in buckets based on mod K
        for(int i = 0; i < N; i++)
        {
            int bucket = arr[i] % K;
            modulus[bucket] += 1;
        }

        //Adds 1 if there is only 1 element in the 0 bucket or the k/2 bucket because these are edge cases
        maxSize  += (modulus[0] >= 1) ? 1 : 0;
        maxSize  += (K%2 == 0 && modulus[K/2] >= 1) ? 1 : 0;

        //Determine the max buckets we can use
        int maxBuckets = 0;
        if(K%2 == 0)
        {
            maxBuckets = (K/2)-1;
        }
        else
        {
            maxBuckets = K/2;
        }

        //Picks the biggest bucket of each pair for each even sum group
        for(int i = 1; i <= maxBuckets; i++)
        {
            maxSize += Math.max(modulus[i], modulus[K-i]);
        }

        return maxSize;
    }


    public static void main(String[] args) {

        List<Integer>  list = new ArrayList<>();

        list.add(1);
        list.add(7);
        list.add(2);
        list.add(4);

     //   System.out.println(nonDivisibleSubset(3, list ));

        System.out.println( nonDivisibleSubset(new int[]{1, 7, 2, 4}, 4, 3));

    }
}

//Reference by RyanFehr