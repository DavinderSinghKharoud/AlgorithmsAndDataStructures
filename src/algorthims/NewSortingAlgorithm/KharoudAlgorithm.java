package algorthims.NewSortingAlgorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public class KharoudAlgorithm {

    static int[] unSortedList = {1,12,2,3,13};

    static int [] tree = new int[ 300];

    public static void main(String[] args) {

        createTree(unSortedList);

        System.out.println("Tree: ");
        for(int elements: tree){
            System.out.print(elements+" ");
        }

        System.out.print(" \nSortedList:\n");
        inOrderTraversal(0);

    }

    //Creating a tree
    private static void createTree(int[] unSortedList){

        Queue queue = new ArrayDeque();
        tree[0] = unSortedList[0];
        queue.add(0);

        for(int index=1; index<unSortedList.length; index++){

            int indexRemoved = (int) queue.remove();
            int num = unSortedList[index];

            if( num <= tree[indexRemoved]){

                int addElementIndex = 2* indexRemoved + 1;
                tree[ addElementIndex ] = num;
                queue.add(addElementIndex);

            }else{

                int addElementIndex = 2* indexRemoved + 2;
                tree[ addElementIndex] = num;
                queue.add(addElementIndex);

            }
        }

    }

    //Inorder Traversal
    private static void inOrderTraversal(int index){
        try {
            if( tree[index] == 0){
                return;
            }
        }catch (ArrayIndexOutOfBoundsException e){
            return;
        }

        inOrderTraversal( 2*index + 1);
        System.out.println(tree[index]);
        inOrderTraversal( 2*index +2);

    }


}
