package algorithms.AtCoderAndGeeksForGeeks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Therefore {

    public static void main(String[] args) {

        FastReader fastReader = new FastReader();
        find( fastReader.nextInt() );

    }

    public static void find( int num ){

        int lastDigit = num % 10;

        if( lastDigit == 2 || lastDigit == 4 || lastDigit == 5 || lastDigit == 7 || lastDigit == 9 ){
            System.out.println("hon");
        }
        else if( lastDigit == 0 || lastDigit == 1 || lastDigit == 6 || lastDigit == 8 ){
            System.out.println("pon");
        }else{
            System.out.println("bon");
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

