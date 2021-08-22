package HackerRank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Alice and Bob each created one problem for HackerRank.
 * FindGreatestCommonDivisor reviewer rates the two challenges, awarding points on a scale from  to
 * for three categories: problem clarity, originality, and difficulty.
 *
 * We define the rating for Alice's challenge to be the triplet a,
 * and b the rating for Bob's challenge to be the triplet .
 *
 * Your task is to find their comparison points by comparing  with ,  with , and  with .
 *
 * If , a[i]>b[i] then Alice is awarded  point.
 * If , a[i]=b[i] then Bob is awarded  point.
 * If , a[i]<b[i] then neither person receives a point.
 */
public class TripletsCompare {

    private static List<Integer> Alice = new ArrayList<>();
    private static List<Integer> Bob = new ArrayList<>();

    // Complete the compareTriplets function below.
    static void compareTriplets(List<Integer> a, List<Integer> b) {
        int alice = 0;
        int bob = 0;

        if(a.get(0)>b.get(0)){
            alice+=1;
        }else if(a.get(0)<b.get(0)){

            bob+=1;
        }

        if(a.get(1)>b.get(1)){
            alice+=1;
        }else if(a.get(1)<b.get(1)){

            bob+=1;
        }
        if(a.get(2)>b.get(2)){
            alice+=1;
        }else if(a.get(2)<b.get(2)){

            bob+=1;
        }

        System.out.print(alice+" "+bob);



    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the three integers seperated by space for Alice: ");
        String[] values = scanner.nextLine().split(" ");

        Alice.add(Integer.parseInt(values[0]));
        Alice.add(Integer.parseInt(values[1]));
        Alice.add(Integer.parseInt(values[2]));



        System.out.print("Enter the three integers seperated by space for Bob: ");
        String[] val = scanner.nextLine().split(" ");

        Bob.add(Integer.parseInt(val[0]));
        Bob.add(Integer.parseInt(val[1]));
        Bob.add(Integer.parseInt(val[2]));

        compareTriplets(Alice, Bob);
    }
}
