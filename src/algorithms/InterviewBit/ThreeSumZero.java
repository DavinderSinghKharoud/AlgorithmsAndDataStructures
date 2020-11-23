package algorithms.InterviewBit;

import java.util.*;

public class ThreeSumZero {

    public static ArrayList<ArrayList<Integer>> threeSum(List<Integer> lst) {

        int len = lst.size();
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (len == 0) return res;


        //sort the list
        Collections.sort(lst);

        for (int index = 0; index < len; index++) {

        	if( index == 0 || (!lst.get(index).equals(lst.get(index - 1)))){
        		//As we do not want to check the duplicates
				findPair(lst, index, res);
			}

        }

        return res;
    }

    public static void findPair(List<Integer> lst, int index, ArrayList<ArrayList<Integer>> res) {
        long num = lst.get(index);
        int start = index + 1;
        int end = lst.size() - 1;
        long key = -1 * num;

        while (start < end) {
            long first = lst.get(start);
            long second = lst.get(end);

            if (first + second == key) {

                ArrayList<Integer> curr = new ArrayList<>();
                curr.add((int) num);
                curr.add((int) first);
                curr.add((int) second);
                res.add(curr);

                while ( start < end && lst.get(start).equals(lst.get(start + 1))){
                	start++;
				}

                while ( end > start && lst.get(end).equals(lst.get(end - 1))){
                	end--;
				}
				start++;
                end--;
            } else if (first + second < key) {
                start++;
            } else {
                end--;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println(threeSum(Arrays.asList(2147483647, -2147483648, -2147483648, 0, 1 )));
    }
}
