package LeetCode;

public class SearchRotatedArrayII {

    public static void main(String[] args) {
        System.out.println(new SearchRotatedArrayII().search(new int[]{1,0,1,1,1}, 0));
    }
    public boolean search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while(start <= end){
            int mid = (end + start)/2;
            if(nums[mid] == target)return true;
            if(nums[mid] == nums[start] ) {
                start++;
                continue;
            }
            if(nums[mid] > nums[start]){
                //Left is sorted
                if( nums[start] <= target && target <= nums[mid]) end = mid - 1;
                else start = mid + 1;
            }else{
                //Right is sorted
                if( nums[mid] <= target && target <= nums[end]) start = mid + 1;
                else end = mid - 1;
            }
        }
        return false;
    }
}
