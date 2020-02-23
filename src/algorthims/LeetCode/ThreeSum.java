package algorthims.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort( nums );
        List< List<Integer>> output = new ArrayList<>();

        for( int i = 0; i<nums.length - 2; i++ ){

            if( i == 0 || ( i > 0 && nums[i] != nums[i - 1])){

                int low = i;
                int high = nums.length - 1;
                int sum = 0 - nums[i];

                while ( low < high ){

                    if( nums[low] + nums[high] == sum ){
                        output.add( Arrays.asList(nums[i], nums[low], nums[high]));

                        while ( low < high && nums[low] == nums[low + 1]){
                            low++;
                        }
                        while ( low < high && nums[high] == nums[high - 1]){
                            high--;
                        }

                        low++;
                        high--;
                    }else if( nums[low] + nums[high] > sum ){
                        high --;
                    }else{
                        low ++;
                    }

                }

            }
        }

        return output;

    }
//
//    public static List< List< Integer>> three( int []nums){
//        int len=nums.length;
//        Arrays.sort(nums);
//        List<List<Integer>> solution=new ArrayList<>();
//        for(int i=0;i<nums.length-2;i++){
//            if(i!=0&&nums[i]==nums[i-1]){
//                continue;
//            }
//            int left=i+1;
//            int right=len-1;
//            while(left!=right){
//                if(nums[i]+nums[left]+nums[right]>0){
//                    right--;
//                }else if(nums[i]+nums[left]+nums[right]<0){
//                    left++;
//                }else{
//                    solution.add(Arrays.asList(nums[i],nums[left],nums[right]));
//                    left++;
//                    while(nums[left]==nums[left-1]&&left!=right){
//                        left++;
//                    }
//                }
//            }
//
//        }
//        return solution;
//    }

    public static void main(String[] args) {

        List<List<Integer>> output = threeSum(new int[]{
                1,2,-2,-1
        });

        for (int i = 0; i < output.size(); i++) {
            List<Integer> lst = output.get(i);
            for (int j = 0; j < lst.size(); j++) {

                System.out.print(lst.get(j) + " ");

                System.out.println();
            }
        }
    }
}
