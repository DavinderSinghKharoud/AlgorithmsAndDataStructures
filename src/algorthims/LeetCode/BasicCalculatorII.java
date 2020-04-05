package algorthims.LeetCode;


import java.util.*;

public class BasicCalculatorII{
    
    public static void main(String[] args ) {

        System.out.println(calculate1("3+2*2"));
	}


    public static int calculate1 (String s ){
	
	    int prefix = 0, count = 0;
	    char sign = '+';
	    char[] arr = s.toCharArray();
	    
	    for( int i = 0; i<arr.length; i++ ){
		char ch = arr[i];
		if( Character.isDigit(ch) ){
		    //if character is digit
		    count = count * 10 + ch - '0';
		    
		}else if( "+-/*".indexOf(ch) != -1 ){
		    
		    //if character is positive sign 
		    if( ch == '+' || ch == '-'){

                if( sign == '+'){
                    prefix += count;
                    sign = ch;
                }else {
                    prefix -= count;
                    sign = ch;
                }

                count = 0;
            }
		    else if( ch == '/' || ch == '*' ){
			
			while( i < arr.length && (arr[i] == '/' || arr[i] == '*') ){
			    
			    if( arr[i] == '/'){
				int temp = 0;
				i++;
				
				//skipping the empty spaces
				while( i<arr.length && arr[i] == ' ' ){
				    i++;
				}
				
				while( i<arr.length && Character.isDigit( arr[i]) ){
				    temp = temp * 10 + arr[i] - '0';
				    i++;
				}
				count /= temp;
				    
			    }else if( arr[i] == '*'){
				int temp = 0;
				i++;
				
				//skipping the empty spaces
				while( i<arr.length && arr[i] == ' ' ){
				    i++;
				}
				
				while( i<arr.length && Character.isDigit( arr[i]) ){
				    temp = temp * 10 + arr[i] - '0';
				    i++;
				}
				count *= temp;
				    
			    }
			    
			    
			}
			i--;
		    }
		    
		    
		}
		
		
	    }
	    if( count > 0){
		prefix += sign == '+' ? count: (sign == '-') ? -count: count;
	    }

	return prefix;
    }


    public static int calculate3(String s) {

        Queue<Character> q1=new LinkedList<>();
        for(int i=0;i<s.length();i++)
        {
            if(s.charAt(i)==' ')
            {
                continue;
            }
            q1.add(s.charAt(i));
        }
        Stack<Integer> s1=new Stack<>();
        Stack<Character> s2=new Stack<>();


        while(!q1.isEmpty())
        {
            char c=q1.remove();


            if(Character.isDigit(c))
            {
                int num=0;
                while(Character.isDigit(c) && !q1.isEmpty() && Character.isDigit(q1.peek()))
                {
                    num=num*10+Character.getNumericValue(c);
                    c=q1.remove();
                }
                num=num*10+Character.getNumericValue(c);
                s1.push(num);
            }
            else if(c=='+' || c=='-' || c=='*' || c=='/')
            {
                while(!s2.isEmpty() && priority(c,s2.peek()))
                {
                    s1.push(compute(s1.pop(),s1.pop(),s2.pop()));

                }
                s2.push(c);

            }


        }

        while(!s2.isEmpty())
        {
            char ch=s2.pop();
            s1.push(compute(s1.pop(),s1.pop(),ch));
        }
        return s1.peek();
    }
    public static int compute(int b,int a,char op)
    {
        if(op=='+')
        {
            return a+b;
        }
        if(op=='-')
        {
            return a-b;
        }
        if(op=='*')
        {
            return a*b;
        }

        return  a/b;
    }

    public static boolean priority(char op1,char op2)
    {
        if((op1=='*' || op1=='/') && (op2=='+' || op2=='-'))
        {
            return false;
        }
        return true;
    }
}







