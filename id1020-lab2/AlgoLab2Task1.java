/**
 * Lab2Task1
 * Created by Dana Ismail on 2020-09-14. Updated: 2020-09-15.
 * What problem this code solves: Sorting an array of integers.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * An array is created with integers. That array is passed on to a method doing insertion sort.
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.Scanner;

public class AlgoLab2Task1 {

    /**
     * Sorting an array with integers in ascending order using insertion sort
     * @param theArray to be sorted with the help of insertion sort
     */
    public static void insertionSortAlgorithm(int[] theArray) {
        int i, j, imp, swapping;                    //initialize variables
        printArray(theArray);
        for(i = 1; i < theArray.length; i++) {      //outer-loop
            imp = theArray[i];                      //imp is the value in [i] at begin.
            j = i-1;                                //j begins at i-1 and decreases in the inner-loop
            while(j >= 0 && imp < theArray[j]) {    //inner-loop
                swapping = theArray[j];             //swapping
                theArray[j] = theArray[j+1];        //swapping
                theArray[j+1] = swapping;           //swapping
                printArray(theArray);
                j--;                                //decrease of j
            }
        }
    }

    /**
     * Inserts integers into the array
     * @param theArray to insert integers in
     */
    public static void insertingNumbers(int[] theArray) {
        Scanner scan = new Scanner(System.in);
        for(int i = 0; i < theArray.length; i++) {
            theArray[i] = scan.nextInt();
        }
    }

    /**
     * Method that prints the content of the array
     * @param theArray to be printed onto the terminal
     */
    public static void printArray(int[] theArray) {
        for(int i = 0; i< theArray.length; i++) {
            System.out.print("[" + theArray[i] + "] ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many elements do you want to sort?");
        int N = scan.nextInt();
        int[] anArray = new int[N];
        System.out.println("Insert " + N + " elements into the array.");
        insertingNumbers(anArray);
        insertionSortAlgorithm(anArray);
    }
}