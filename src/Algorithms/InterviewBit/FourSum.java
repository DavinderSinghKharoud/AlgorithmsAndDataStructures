package Algorithms.InterviewBit;

import java.util.*;

public class FourSum {

    public static ArrayList<ArrayList<Integer>> fourSum(List<Integer> lst, int target) {

        int len = lst.size();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        Collections.sort(lst);

        for (int index1 = 0; index1 < len - 3; index1++) {
            for (int index2 = index1 + 1; index2 < len - 2; index2++) {

                int sum1 = target - lst.get(index1) - lst.get(index2);
                int start = index2 + 1;
                int end = lst.size() - 1;

                while ( start < end ){
                    int sum2 = lst.get(start) + lst.get(end);
                    if( sum2 > sum1 ) end--;
                    else if( sum2 < sum1 ) start++;
                    else{
                        ArrayList<Integer> temp= new ArrayList<Integer>();
                        temp.add(lst.get(index1)); temp.add(lst.get(index2));
                        temp.add(lst.get(start)); temp.add(lst.get(end));
                        if (!res.contains(temp)) res.add(temp);
                        start++; end--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        System.out.println(fourSum(Arrays.asList(9, -8, -10, -7, 7, -8, 2, -7, 4, 7, 0, -3, -4, -5, -1, -4, 5, 8, 1, 9, -2, 5, 10, -5, -7, -1, -6, 4, 1, -5, 3, 8, -4, -10, -9, -3, 10, 0, 7, 9, -8, 10, -9, 7, 8, 0, 6, -6, -7, 6, -4, 2, 0, 10, 1, -2, 5, -2), 0));

    }

    public static ArrayList<ArrayList<Integer>> fourSum2(List<Integer> lst, int target) {

        Collections.sort(lst);
        return KSum(lst, target, 0, 4);

    }

    private static ArrayList<ArrayList<Integer>> KSum(List<Integer> lst, int target, int start, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        if (start == lst.size() || lst.get(start) * k > target || target > lst.get(lst.size() - 1) * k) {
            return res;
        }

        if (k == 2) {
            return twoSum(lst, target, start);
        }

        for (int index = start; index < lst.size(); index++) {
            if (index == start || !lst.get(index - 1).equals(lst.get(index))) {

                for (ArrayList<Integer> set : KSum(lst, target - lst.get(index), index + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(lst.get(index))));
                    res.get(res.size() - 1).addAll(set);
                }
            }
        }
        return res;
    }

    private static ArrayList<ArrayList<Integer>> twoSum(List<Integer> lst, int target, int startIndex) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();

        int start = startIndex;
        int end = lst.size() - 1;

        while (start < end) {

            if (start != startIndex && lst.get(start) == lst.get(start - 1) ){
                start++;continue;
            }
            if( end != lst.size() - 1 && lst.get(end) == lst.get(end + 1) ){
                end--;
                continue;
            }
            int num1 = lst.get(start);
            int num2 = lst.get(end);

            if (num1 + num2 == target) {
                ArrayList<Integer> curr = new ArrayList<>();
                curr.add(num1);
                curr.add(num2);
                res.add(curr);
                start++;
                end--;
                continue;
            }else if( num1 + num2 < target ){
                start++;
            }else{
                end--;
            }
        }

        return res;
    }
}
