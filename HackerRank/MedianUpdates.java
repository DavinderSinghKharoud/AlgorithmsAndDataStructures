package HackerRank;

import java.util.*;

public class MedianUpdates {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int total = scanner.nextInt();
        List<Long> lst = new ArrayList<>();

        for (int count = 0; count < total; count++) {
           if( scanner.next().equals("r")){
               remove( scanner.nextInt(), lst);
           }else{
               add( scanner.nextInt(), lst);
           }
        }

    }


    public static void remove(long num, List<Long> lst) {
        int index = Collections.binarySearch(lst, num);
        if (index >= 0) {
            lst.remove(index);
            printMedian(lst);
        } else {
            System.out.println("Wrong!");
        }

    }

    public static void printMedian(List<Long> lst) {
        int size = lst.size();
        int half = size / 2;

        if (size == 0) {
            System.out.println("Wrong!");
        } else if (size % 2 == 0) {
            long num = (lst.get(half - 1) + lst.get(half));
            if (num % 2 == 0) {
                System.out.println(num / 2);
            } else {
                System.out.printf("%.1f", num / 2.0);
                System.out.println();
            }
        } else {
            System.out.println(lst.get(half));
        }
    }

    public static void add(long num, List<Long> lst) {
        int index = Collections.binarySearch(lst, num);

        if (index >= 0) {
            lst.add(index, num);
        } else {
            lst.add(-index - 1, num);
        }

        printMedian(lst);
    }

}
