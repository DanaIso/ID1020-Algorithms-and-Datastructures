/**
 * Lab2Task3
 * Created by Dana Ismail on 2020-09-16. Updated: 2020-09-16.
 * What problem this code solves: Ordering an array of integers. Positives on one side and negative on the other.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * An array is created with integers. That array is passed on to a method ordering positive and negative integers.
 * Method for printing the array.
 *
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
#include <stdio.h>

void orderingArray(int arr[], int size) {
    int i, imp, temp;
    int position = 0;                   //will increase later on
    for(i = 0; i < size; i++) {         //looping through the array
        imp = arr[i];                   //imp starts of at i
        if (imp < 0) {                  //checks if element is negative
            temp = arr[position];       //swapping
            arr[position] = arr[i];     //swapping
            arr[i] = temp;              //swapping
            position++;                 //position increases by 1
        }
    }
}

void printArray(int arr[], int size) {
    int i;
    for(i = 0; i < size; i++) {
        printf("[%d] ", arr[i]);
    }
    printf("\n");
}

int main(int argc, const char * argv[]) {
    int anArray[] = {-1,0,3,-4,1,-2,5,0};               //skapar en fixed array
    int size = sizeof(anArray)/sizeof(anArray[0]);      //sizeof(anArray) är antalet bitar för hela arrayen
                                                        //delat på antalet bitar för datatypen
    printf("%s", "This is the original array: ");
    printArray(anArray, size);                          //size som argument eftersom det blir fel annars
    orderingArray(anArray, size);                       //anropar metoden för att ordna talen
    printf("%s", "This is the ordered array: ");
    printArray(anArray, size);
    return 0;
}
