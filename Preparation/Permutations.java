package Preparation;

import LeetCode.Template.A;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Permutations {

    public static void main(String[] args) {
        ArrayList<Integer> lst = new ArrayList<>();
        lst.add(2);
        lst.add(1);
        lst.add(9);
        System.out.println(new Permutations().permute(lst));
    }

    public ArrayList<ArrayList<Integer>> permute(ArrayList<Integer> lst) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        generate(res, lst, 0);
        return res;
    }

    void generate(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> lst, int start){
        if(start == lst.size()){
            res.add(new ArrayList<>(lst));
        }else{

            Set<Integer> vis = new HashSet<>();
            for(int i = start; i < lst.size(); i++){
                if(vis.contains(lst.get(i))) continue;
                vis.add(lst.get(i));
                Collections.swap(lst, i, start);
                generate(res, lst, start + 1);
                Collections.swap(lst, i, start);
            }
        }
    }
}
