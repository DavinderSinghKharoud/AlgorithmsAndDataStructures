package LeetCode;

public class ValidateIpAddress {

   public static void main(String[] args) {

      System.out.println(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));
   }

   public static String validIPAddress(String IP) {
      boolean isIp4 = true, isIp6 = true;
      if(IP.length() == 0 ) return "Neither";

      String[] arr = IP.split("\\.");
      if(arr.length != 4){
         isIp4 = false;
      }
      if(IP.charAt(0) == '.' || IP.charAt(IP.length() - 1) == '.'){
         isIp4 = false;
      }

      for(int i = 1; i < IP.length() - 1; i++){
         if (IP.charAt(i) == '.' && IP.charAt(i + 1) == '.') {
            isIp4 = false;
            break;
         }
      }
      for (String s : arr) {
         if(!isIp4){
            break;
         }
         // check if number is integer or not
         int num;
         try {
            num = Integer.parseInt(s);
         } catch (Exception e) {
            isIp4 = false;
            break;
         }
         if (num < 0 || num > 255) {
            isIp4 = false;
            break;
         }

         // Check if it conatins leading zeroes
         if (s.length() > 1 && s.charAt(0) == '0') {
            isIp4 = false;
            break;
         }

      }

      if (isIp4)
         return "IPv4";

      if(IP.charAt(0) == ':' || IP.charAt(IP.length() - 1) == ':'){
         isIp6 = false;
      }

      for(int i = 1; i < IP.length() - 1; i++){
         if (IP.charAt(i) == ':' && IP.charAt(i + 1) == ':') {
            isIp6 = false;
            break;
         }
      }

      arr = IP.split(":");
      if(arr.length != 8){
         isIp6 = false;
      }
      for (String s : arr) {

         if (s.length() <= 0 || s.length() > 4) {
            isIp6 = false;
            break;
         }

         for (char c : s.toCharArray()) {
            if (c < '0' || c > '9') {
               // It is some character other than numbers
               if (c >= 'a' && c <= 'f') {
                  continue;
               }
               if (c >= 'A' && c <= 'F') {
                  continue;
               }
               isIp6 = false;
               break;
            }
         }

         if (!isIp6)
            break;

      }

      if (isIp6) {
         return "IPv6";
      }
      return "Neither";
   }
}
