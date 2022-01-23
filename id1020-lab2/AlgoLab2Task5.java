/**
 * Lab2Task5
 * Created by Dana Ismail on 2020-09-17. Updated: 2020-09-17.
 * What problem this code solves: Compares the execution times of insertion and merge.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * An array is created with integers. That array is passed on to a methods doing insertion sort and
 * merge sort. The time is taken of those two.
 * Based on: The algorithm for the two merge functions are based on Princeton's code, Mergesort.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.Scanner;

public class AlgoLab2Task5 {

    /**
     * Sorting an array with integers in ascending order using insertion sort
     * @param theArray to be sorted with the help of insertion sort
     */
    public static void insertionSortAlgorithm(int[] theArray) {
        int i, j, imp, swapping;
        for(i = 1; i < theArray.length; i++) {          //outer-loop
            imp = theArray[i];                          //i begins at index 1 and increases theArray.length
            j = i-1;                                    //j begins at i-1 and decreases to 0, later
            while(j >= 0 && imp < theArray[j]) {        //inner-loop
                swapping = theArray[j];                 //swapping
                theArray[j] = theArray[j+1];            //swapping
                theArray[j+1] = swapping;               //swapping
                j--;                                    //j decreases
            }
        }
    }

    private static int[] merge(int[] left, int[] right) {       //mergesort will cont. until base-case
        int[] newArray = new int[left.length + right.length];         //puts left and right together
        int i = 0, j = 0;                                             //comparing first index of each array

        //variable k to iterate to place sorted integer from sub-array to newArray
        //variable i and j to iterate in each sub-array to compare
        for (int k = 0; k < newArray.length; k++) {
            if      (i >= left.length) {    //if left array is empty
                newArray[k] = right[j++];   //continue to insert values from right array, increment j
            }
            else if (j >= right.length) {   //if right array is empty
                newArray[k] = left[i++];    //continue to insert values from left array, increment i
            }
            else if (left[i] <= right[j]) { //if integer on right array is greater than int on left
                newArray[k] = left[i++];    //insert int from left to newArray, increment i
            }
            else {                          //if int on left is greater than int on right
                newArray[k] = right[j++];   //insert int from right to newArray, increment j
            }
        }
        return newArray;                        //return finished array
    }

    public static int[] mergesort(int[] theArray) {
        int size = theArray.length;                 //variable for the size of the array
        if (size <= 1) return theArray;             //base-case
        int[] left = new int[size/2];               //creating a new left array
        int[] right = new int[size - size/2];       //creating a new right array with the rest of the elements
        for (int i = 0; i < left.length; i++)       //filling the left array with the left side of original
            left[i] = theArray[i];
        for (int i = 0; i < right.length; i++)      //filling the right array with the right side of original
            right[i] = theArray[i + size/2];
        return merge(mergesort(left), mergesort(right));    //recursive call to merge
    }                                                       //will cont. until base-case

    /**
     * Inserts integers into the array
     * @param theArray to insert integers in
     */
    public static void insertingNumbers(int[] theArray) {
        for(int i = 0; i < theArray.length; i++) {
            theArray[i] = (int)(Math.random()*10);
        }
    }

    /**
     * Copying the first array, so that we have to identical arrays
     * @param ar1 first array
     * @param ar2 second array
     */
    public static void arrayCopy(int[] ar1, int[] ar2) {
        for(int i = 0; i < ar1.length; i++) {
            ar2[i] = ar1[i];
        }
    }
/*
    public static void printing(int[] a){
        for(int i = 0; i < a.length; i++) {
            System.out.print("["+a[i]+"] ");
        }
        System.out.println();
    }
*/
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many elements do you want to sort?");
        int N = scan.nextInt();
        int[] anArray = new int[N];
        insertingNumbers(anArray);
        int[] anArray2 = new int[N];
        arrayCopy(anArray, anArray2);

        double start1 = System.currentTimeMillis();         //the current time at that point
        insertionSortAlgorithm(anArray);
        double end1 = System.currentTimeMillis();           //the current time at that point
                                                            //then end1 - start1 to get it as a timer
        double start2 = System.currentTimeMillis();
        anArray2 = mergesort(anArray2);
        double end2 = System.currentTimeMillis();

        System.out.println("\nInsertion algorithm finished in: "+((end1-start1)/1000)+" seconds");
        System.out.println("\nMerge algorithm finished in: "+((end2-start2)/1000)+" seconds");
    }
}