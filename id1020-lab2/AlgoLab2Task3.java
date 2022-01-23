/**
 * Lab2Task3
 * Created by Dana Ismail on 2020-09-14. Updated: 2020-09-15.
 * What problem this code solves: Sorting an array of integers. And prints the number of swaps.
 *                                And prints the inversions made before sorting.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * An array is created with integers. That array is passed on to a method doing insertion sort.
 * Methods for printing the array, inserting integers and printing inversions.
 *
 * Time Complexity for the printInversion method is O(n^2).
 *
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.Scanner;

public class AlgoLab2Task3 {

    /**
     * Sorting an array with integers in ascending order using insertion sort
     * @param theArray to be sorted with the help of insertion sort
     */
    public static void insertionSortAlgorithm(int[] theArray) {
        int i, j, imp, swapping;                    //initialize variables
        int swaps = 0;                              //number of swaps from start
        printArray(theArray);                       //prints how the array is from start
        System.out.println();
        for(i = 1; i < theArray.length; i++) {          //outer-loop
            imp = theArray[i];                          //i begins at index 1 and increases theArray.length
            j = i-1;                                    //j begins at i-1 and decreases to 0, later
            while(j >= 0 && imp < theArray[j]) {        //inner-loop
                swapping = theArray[j];                 //swapping
                theArray[j] = theArray[j+1];            //swapping
                theArray[j+1] = swapping;               //swapping
                swaps++;                                //counter for how many swaps increases by 1.
                j--;                                    //j decreases
                printArray(theArray);                   //prints the array after each swap
                System.out.println();
            }
        }
        System.out.println(swaps+" swaps was made!");
    }

    /**
     * Prints out the inversions made
     * @param theArray tp print its inversions
     */
    public static void printInversion(int[] theArray) {
        int invrs = 0;                  //counter for the amount of inverses
        int i = 0;                      //pointer
        while(i < theArray.length) {    //
            int j = i + 1;              //pointer
            //If any element after i is greater than i, then that is a inversion
            while(j < theArray.length && theArray[i] > theArray[j]) {
                System.out.println("[["+i+","+theArray[i]+"], ["+j+","+theArray[j]+"]]");
                invrs++;                //inversion count increase by 1
                j++;                    //j moves to next index and checks if there is other inversions for current i
            }
            i++;
        }
        System.out.println("How many inversions? Answer: "+invrs);
        System.out.println();
    }

    /**
     * Method that prints the content of the array
     * @param theArray to be printed onto the terminal
     */
    public static void printArray(int[] theArray) {
        for(int i = 0; i< theArray.length; i++) {
            System.out.print("[" + theArray[i] + "] ");
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

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many elements do you want to sort?");
        int N = scan.nextInt();
        int[] anArray = new int[N];
        System.out.println("Insert " + N + " elements into the array.");

        insertingNumbers(anArray);
        printInversion(anArray);
        insertionSortAlgorithm(anArray);
    }
}