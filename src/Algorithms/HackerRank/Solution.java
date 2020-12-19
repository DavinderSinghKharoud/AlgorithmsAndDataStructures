package Algorithms.HackerRank;

import java.util.*;

public class Solution {


    public static int maxPairs(List<Integer> skillLevel, int minDiff) {
        int res = 0;
        List<Integer> copyList = new ArrayList<>();
        Collections.sort(skillLevel);
        for (int num : skillLevel) {
            if (copyList.isEmpty()) {
                copyList.add(num);
                continue;
            }

            long minNumber = num - minDiff;
            if (minNumber < 0) {
                copyList.add(num);
                continue;
            }

            int index = Collections.binarySearch(copyList, (int) minNumber);

            if (index < 0) { //If number do not exist
                index = -index - 1;

                //Now we need to check the number lesser than this index
                index--;
                if (index >= 0 && copyList.get(index) <= minDiff) {
                    res++;
                    copyList.remove(index);
                } else {
                    copyList.add(num);
                }

            } else { //If number exist
                copyList.remove(index);
                res++;
            }


        }

        return res;

    }

    public static void main(String[] args) {
        List<Integer> lst = new ArrayList<>();
        String s = "709552565,473251358, 803612259, 579542802, 183012194, 689345248, 151290765, 123232501, 994391793, 25107191, 862726097";
        for (String str : s.split(",")) {
            lst.add(Integer.valueOf(str.trim()));
        }

//        lst.add(3);
//        lst.add(4);
//        lst.add(5);
//        lst.add(2);
//        lst.add(1);
//        lst.add(1);
        //lst.add(3);
        System.out.println(maxPairs(lst, 440987423));
    }

}
