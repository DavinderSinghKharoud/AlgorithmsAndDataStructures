package algorithms.InterviewBit;

import java.util.ArrayList;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 *
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3 ) :
 *
 * 1. "123"
 * 2. "132"
 * 3. "213"
 * 4. "231"
 * 5. "312"
 * 6. "321"
 * Given n and k, return the kth permutation sequence.
 *
 * For example, given n = 3, k = 4, ans = "231"
 *
 *  Good questions to ask the interviewer :
 * What if n is greater than 10. How should multiple digit numbers be represented in string?
 *  In this case, just concatenate the number to the answer.
 * so if n = 11, k = 1, ans = "1234567891011"
 * Whats the maximum value of n and k?
 *  In this case, k will be a positive integer thats less than INT_MAX.
 */
public class KthPermutationSequence {

    //Time limit Exceeded
    public static String getPermutation(int n, int k) {

        int[] arr = new int[n];

        for (int num = 1; num <= n; num++) {
            arr[num - 1] = num;
        }
        ArrayList<String> save = new ArrayList<>();

        permute(arr, 0, save);

        return save.get(k - 1);

    }

    public static void permute(int[] arr, int start, ArrayList<String> save) {

        if (start == arr.length) {

            StringBuilder sbr = new StringBuilder();
            for (int num : arr) {
                sbr.append(num);
            }
            save.add(sbr.toString());
            return;
        }

        for (int index = start; index < arr.length; index++) {
            swap(arr, start, index);
            permute(arr, start + 1, save);
            swap(arr, start, index);
        }

    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void main(String[] args) {

        System.out.println(getPermutation2(3, 2));

    }


    public static String getPermutation2(int n, int k) {
		ArrayList<Integer> lst = new ArrayList<>();

        for (int count = 1; count <= n; count++) {
            lst.add(count);
        }
		StringBuilder sbr = new StringBuilder();

        ArrayList<Integer> fact = new ArrayList<>();
        fact.add(1);fact.add(1);
        for (int index = 2; index <= 11; index++) {
            fact.add(fact.get(fact.size() - 1) * index);
        }

        for (int index = 12; index <= n ; index++) {
            fact.add(Integer.MAX_VALUE);
        }

        k--;
		while( !lst.isEmpty() ){
            int factorial = fact.get(--n);
			int index = (k) / factorial;

			sbr.append(lst.get(index));
			lst.remove(index);
			
			k %= factorial;
			
		}
		
		return sbr.toString();
    }



}
