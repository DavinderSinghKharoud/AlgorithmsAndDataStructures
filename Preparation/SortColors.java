package Preparation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SortColors {

    public static void main(String[] args) {
        List<Integer> arrayList = Arrays.asList(2, 0, 0, 1, 0, 0, 2, 2, 1, 1, 0, 0, 1, 0, 2, 1, 1, 0, 1, 0, 1, 2, 2, 2, 0, 0, 1, 0, 2, 1, 1, 2, 1, 2, 2, 1, 0, 2, 2, 1, 1, 1, 0, 1, 0, 1, 0, 2, 1, 2, 0, 2, 0, 1, 1, 0, 2, 2, 1, 2, 0, 2, 1, 1, 1, 2, 0, 1, 0, 2, 2, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 2, 1, 1, 0, 2, 1, 2, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 1, 1, 0, 2, 1, 2, 2, 2, 1, 2, 2);
        new SortColors().sortColors(arrayList);
        System.out.println(arrayList.toString());
    }

    public void sortColors2(List<Integer> a) {
        int count1 = 0, count2 = 0, count3 = 0;
        for (int num : a) {
            if (num == 0) count1++;
            else if (num == 1) count2++;
            else count3++;
        }

        int index = 0;
        while (count1-- > 0) {
            a.set(index++, 0);
        }
        while (count2-- > 0) {
            a.set(index++, 1);
        }
        while (count3-- > 0) {
            a.set(index++, 2);
        }

    }

//    void sortColors(int A[], int n) {
//        int second=n-1, zero=0;
//        for (int i=0; i<=second; i++) {
//            while (A[i]==2 && i<second) swap(A[i], A[second--]);
//            while (A[i]==0 && i>zero) swap(A[i], A[zero++]);
//        }
//    }
    public void sortColors(List<Integer> a) {

        if (a.size() < 3) {
            if (a.size() == 1) return;
            else {
                if (a.get(0) > a.get(1)) {
                    int temp = a.get(1);
                    a.set(1, a.get(0));
                    a.set(0, temp);
                }
                return;
            }
        }
        int count1 = 0, count2 = 0, count3 = 0;
        for (int num : a) {
            if (num == 0) count1++;
            else if (num == 1) count2++;
            else count3++;
        }

        int first = 0, second = count1, third = count1 + count2;
        int i = 0;
        while (i < a.size()) {
            while (a.get(first) == 0 && (first + 1 < count1)) {
                first++;
            }
            while (a.get(second) == 1 && (second + 1 < count1 + count2)) {
                second++;
            }
            while (a.get(third) == 2 && third + 1 < a.size()) {
                third++;
            }

            int curr = a.get(i);
            if (curr == 0) {
                if (i >= count1) {
                    int temp = a.get(first);
                    a.set(first, 0);
                    a.set(i, temp);
                } else i++;
            } else if (curr == 1) {
                if (i < count1 || i >= count1 + count2) {
                    int temp = a.get(second);
                    a.set(second, 1);
                    a.set(i, temp);
                } else i++;
            } else {
                if (i < count1 + count2) {
                    int temp = a.get(third);
                    a.set(third, 2);
                    a.set(i, temp);
                } else i++;
            }

        }
    }
}
