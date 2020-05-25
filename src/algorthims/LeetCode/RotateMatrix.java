package algorthims.LeetCode;

import java.util.ArrayList;

public class RotateMatrix {
    public static void rotate(ArrayList<ArrayList<Integer>> a) {

        //do the transpose
        for (int i = 0; i < a.size(); i++) {
            for (int j = i; j < a.get(0).size(); j++) {

                int temp = a.get(i).get(j);
                a.get(i).set(j, a.get(j).get(i));
                a.get(j).set(i, temp);
            }
        }

        //flit the matrix horizontally
        for (int i = 0; i < a.size(); i++) {

            for (int j = 0; j < a.get(0).size() / 2; j++) {

                int temp = a.get(i).get(j);

                a.get(i).set(j, a.get(i).get(a.size() - 1 - j));
                a.get(i).set(a.size() - 1 - j, temp);
            }
        }
    }

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);
        ArrayList<Integer> list3 = new ArrayList<>();
        list3.add(7);
        list3.add(8);
        list3.add(9);

        list.add(list1);
        list.add(list2);
        list.add(list3);

        rotate( list );

        for(ArrayList<Integer> arr: list ){
            System.out.println( arr.get(0) + "," + arr.get(1));
        }
    }
}
