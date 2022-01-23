/*
 * Lab2Task6
 * Created by Dana Ismail on 2020-09-20. Updated: 2020-09-20.
 * What problem this code solves: Sorting an array of integers. And prints the number of swaps.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * An array is created with integers. That array is passed on to a method doing mergesort with a cut-off.
 * In the cut-off arrays the elements are sorted insertion sort. Time is taken using System.currentTimeMillis();
 * Based on: The algorithm for the two merge functions are based on Princeton's code, Mergesort.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.Scanner;

public class AlgoLab2Task6 {

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

    private static int[] merge(int[] a, int[] b) {  //mergesort will cont. until base-case
        int[] c = new int[a.length + b.length];     //puts left and right together
        int i = 0, j = 0;                           //comparing first index of each array
        //variable k to iterate to place sorted integer from sub-array to newArray
        //variable i and j to iterate in each sub-array to compare
        for (int k = 0; k < c.length; k++) {
            if (i >= a.length) {                //if left array is empty
                c[k] = b[j++];                  //continue to insert values from right array, increment j
            }
            else if (j >= b.length) {           //if right array is empty
                c[k] = a[i++];                  //continue to insert values from left array, increment i
            }
            else if (a[i] <= b[j]) {            //if integer on right array is greater than int on left
                c[k] = a[i++];                  //insert int from left to newArray, increment i
            }
            else {                              //if int on left is greater than int on right
                c[k] = b[j++];                  //insert int from right to newArray, increment j
            }
        }
        return c;                               //return finished array
    }

    public static int[] mergesort(int[] input, int cutOff) {
        int N = input.length;                       //variable for the size of the array
        if (N <= cutOff) {                          //new base-case with cut-off
            insertionSortAlgorithm(input);          //sorting the sub-arrays before merged together in merge above
            return input;
        }
        int[] a = new int[N/2];                     //creating a new left array
        int[] b = new int[N - N/2];                 //creating a new right array with the rest of the elements
        for (int i = 0; i < a.length; i++)          //filling the left array with the left side of original
            a[i] = input[i];
        for (int i = 0; i < b.length; i++)          //filling the right array with the right side of original
            b[i] = input[i + N/2];
        return merge(mergesort(a,cutOff), mergesort(b,cutOff));     //recursive call to merge
    }                                                               //will cont. until base-case

    /**
     * Inserts integers into the array
     * @param theArray to insert integers in
     */
    public static void insertingNumbers(int[] theArray) {
        for(int i = 0; i < theArray.length; i++) {
            theArray[i] = (int)(Math.random()*10);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many elements do you want to sort?");
        int N = scan.nextInt();
        int[] anArray = new int[N];
        insertingNumbers(anArray);
        System.out.println("What cut-off would you like?");
        int cutOff = scan.nextInt();

        double start1 = System.currentTimeMillis();
        anArray = mergesort(anArray, cutOff);
        double end1 = System.currentTimeMillis();

        System.out.println("\nThe execution time is: "+((end1-start1)/1000)+" seconds");
    }
}