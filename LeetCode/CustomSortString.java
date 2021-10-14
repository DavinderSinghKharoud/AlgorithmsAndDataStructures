package LeetCode;

public class CustomSortString {
    public String customSortString(String order, String s) {
        int len = s.length();
        int[] count = new int[26];
        int a = 'a';
        for(int i = 0;i  < len; i++){
            count[s.charAt(i) - a]++;
        }
        StringBuilder sbr = new StringBuilder();
        for(int i = 0 ;i < order.length(); i++){
            char c = order.charAt(i);
            while(count[c - a]-- > 0){
                sbr.append(c);
            }
        }

        for(int i = 0; i < count.length; i++){
            while(count[i]-- > 0){
                sbr.append( (char)(i + a));
            }
        }
        return sbr.toString();
    }
}
