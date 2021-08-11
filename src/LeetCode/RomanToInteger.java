package LeetCode;

import java.util.HashMap;

public class RomanToInteger {

    public static int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        if( s.length() == 0 || s == null ){
            return 0;
        }

        int sum = 0;
        int initialValue =  map.get( s.charAt( s.length() - 1 ) );
        sum += initialValue;

        for(int i = s.length() - 2; i >= 0; i--){

            int Nextvalue = map.get( s.charAt( i ) );

            if( initialValue <= Nextvalue ){
                sum += Nextvalue;
            }else{
                sum -= Nextvalue;
            }

            initialValue = Nextvalue;
        }

        return sum;
    }

    //MoreFaster
    public int romanToInt2(String s) {
        int nums[] = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            switch (s.charAt(i)) {
                case 'M':
                    nums[i] = 1000;
                    break;
                case 'D':
                    nums[i] = 500;
                    break;
                case 'C':
                    nums[i] = 100;
                    break;
                case 'L':
                    nums[i] = 50;
                    break;
                case 'X' :
                    nums[i] = 10;
                    break;
                case 'V':
                    nums[i] = 5;
                    break;
                case 'I':
                    nums[i] = 1;
                    break;
            }
        }

        int sum = 0;
        for(int i=0; i<nums.length-1; i++){
            if(nums[i] < nums[i+1])
                sum -= nums[i];
            else
                sum += nums[i];
        }

        return sum + nums[nums.length-1];
    }

    public static void main(String[] args) {

        System.out.println( romanToInt("MCMXCIV"));
    }
}
