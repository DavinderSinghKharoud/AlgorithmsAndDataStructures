package GoogleKickStart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ParentingPartneringReturns {

    public static String solve(int[][] partition) {
        StringBuilder result = new StringBuilder();
        char[] temp = new char[partition.length];
        Map<String, Integer > map = new HashMap<>();

        if( partition.length == 1 ){
            return "J";
        }
        int[][] activities = new int[partition.length][3];
        for (int i = 0; i < partition.length ; i++) {
            int[] activity = new int[]{
                    partition[i][0], partition[i][1], i
            };
            activities[i] = activity;
        }
        //sort the activites
        Arrays.sort(activities, Comparator.comparingInt(o -> o[0]));

        Map[] dp = new Map[activities.length];
        map.put("J", activities[0][1]);
        temp[activities[0][2]] = 'J' ;
        map.put("C", 0);
        dp[0] = map;



        for (int i = 1; i < activities.length; i++) {
            int start = activities[i][0];
            int end = activities[i][1];

            int jim = (int) dp[i - 1].get("J");
            int cam = (int) dp[i - 1].get("C");

            if (start < jim && start < cam) {
                return "IMPOSSIBLE";
            }

            //cam is free
            if( start < jim ){
                temp[activities[i][2]] = 'C' ;
                map.put("C", end );
                dp[i] = map;
            }else{
                //jam is free
                temp[activities[i][2]] = 'J' ;
                map.put("J", end);
                dp[i] = map;
            }
        }

        for(char c: temp){
            result.append(c);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        FastReader fastReader = new FastReader();
        int caseNum = fastReader.nextInt();
        for (int ks = 1; ks <= caseNum; ks++) {
            int len = fastReader.nextInt();
            int[][] activities = new int[len][2];

            for (int i = 0; i < len; i++) {
                for (int j = 0; j < 2; j++) {
                    activities[i][j] = fastReader.nextInt();
                }
            }

            System.out.println(String.format("Case #%d: %s", ks, solve(activities)));
        }
    }
}


class FastReader
{
    BufferedReader br;
    StringTokenizer st;

    public FastReader()
    {
        br = new BufferedReader(new
                InputStreamReader(System.in));
    }

    String next()
    {
        while (st == null || !st.hasMoreElements())
        {
            try
            {
                st = new StringTokenizer(br.readLine());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt()
    {
        return Integer.parseInt(next());
    }

    long nextLong()
    {
        return Long.parseLong(next());
    }

    double nextDouble()
    {
        return Double.parseDouble(next());
    }

    String nextLine()
    {
        String str = "";
        try
        {
            str = br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return str;
    }
}
