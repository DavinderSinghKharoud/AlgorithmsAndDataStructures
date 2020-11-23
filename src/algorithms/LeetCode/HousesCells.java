package algorithms.LeetCode;


import java.util.*;

//Question: if adjacent cells are both 1 and 1 or 0 and 0, change the cell to 
//inactive which is 0 else if adjacent cells are like 0 and 1 or 1 and 0
//then change it to active, which is 1

public class HousesCells {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    //O( m*n ) SpaceComplexity O(m)
    public static List<Integer> cellCompete(int[] states, int days)
    {
       List<Integer> res = new ArrayList<>();
        if( states.length == 0 || states == null){
            return res;
        }

            if( states.length == 1){
                res.add(states[0]);
                return res;
            }


       for( int i = 0; i<days; i++ ){
           int[] copy = states.clone();
           for( int j = 0; j<states.length; j++ ){

               if( j == 0 ){
                   if( copy[j + 1] == 0 ){
                       states[j] = 0;

                   }else{
                       states[j] = 1;
                   }
                   continue;
               }

               if( j == states.length - 1){
                   if( copy[j - 1] == 0 ){
                       states[j] = 0;
                   }else{
                       states[j] = 1;
                   }
                   continue;
               }

               if( copy[j - 1] == 0 && copy[j + 1] == 0 || (copy[j - 1] == 1 && copy[j + 1] == 1)){
                   states[j] = 0;
               }else{
                   states[j] = 1;
               }

           }
       }

       for( int num: states){
           res.add(num);
       }
       return res;
    }

    public static void main(String[] args) {
        List<Integer> lst = cellCompete(new int[]{
                1,0,0,1,0
        },3);
        for( int num: lst){
            System.out.println(num);
        }
    }
    
    //O( m*n )
    public static List<Integer> cellCompete2( int[] states, int days){
	//if active, change it to 2
	//if inactive, change it to -1
	boolean check = true;
	List<Integer> res = new ArrayList<>();
	
	for( int i = 0; i< days; i++ ){
	    
	    for( int j = 0; j<states.length; j++ ){
		//Checking first num
		if( j == 0 ){
		    if( states[j + 1] == 0 || states[j + 1 ] == -1 ){
			if( check ) states[j] = -1;
			else{ states[j] = 0; }
		    }else{ 
			    if(check) states[j] = 2; else{ states[j] = 1;}
			 }
		}
		
		if( j == states.length - 1 ){
		    if( states[j - 1] == 0 || states[j - 1 ] == -1 ){
			if( check ) states[j] = -1;
			else{ states[j] = 0; }
		    }else{ 
			    if(check) states[j] = 2; else{ states[j] = 1;}
			 }
		}
		
		 if( states[j - 1] == 0 && states[j + 1] == 0 || (states[j - 1] == 1 && states[j + 1] == 1)
		          && states[j - 1] == -1 && states[j + 1] == -1 || (states[j - 1] == 2 && states[j + 1] == 2)){
                   if( check ) states[j] = -1; else{ states[j] = 0; }
		   
               }else{
		   if( check ) states[j] = 2; else{ states[j] = 1;}
               }
	       
	    }
	    
	    
	}
	 for( int num: states){
	     if( num == 0 || num == -1 ){
		res.add(0); 
	     }else{
		 res.add(1);
	     }
	 }
       return res;
    }
}
