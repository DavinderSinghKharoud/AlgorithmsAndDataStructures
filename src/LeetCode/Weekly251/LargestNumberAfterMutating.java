package LeetCode.Weekly251;

public class LargestNumberAfterMutating {
    public static void main(String[] args) {
        LargestNumberAfterMutating o = new LargestNumberAfterMutating();


        System.out.println(o.maximumNumber("214010", new int[]{6, 7, 9, 7, 4, 0, 3, 4, 4, 7}));
    }

    public String maximumNumber(String num, int[] change) {
        StringBuilder sbr = new StringBuilder(num);

        boolean started = false;
        for (int i = 0; i < sbr.length(); i++) {
            int index = sbr.charAt(i) - '0';
            if (change[index] == index) {
                if (started) {
                    sbr.setCharAt(i, (char) (change[index] + '0'));
                }
            } else if (change[index] > index) {
                started = true;
                sbr.setCharAt(i, (char) (change[index] + '0'));
            } else {
                if (started)
                    break;
            }
        }
        return sbr.toString();
    }
}
