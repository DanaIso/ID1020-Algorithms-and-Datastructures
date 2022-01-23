//
//  main.c
//  Labb1Task1Reverse
//
//  Created by Dana Ismail on 2020-08-26. Updated:
//  What problem this code solves: This code takes a word from strdin and prints it backwards
//  How this code is used(how it is executed, what input it takes, what it outputs): It takes in charachters using getchar();
//                                                                                   and displays the word backwards in the terminal.
//  What this code has been based on:
//  Copyright © 2020 Dana Ismail. All rights reserved.
//

#include <stdio.h>
#include <string.h>

//en metod som gör inputen baklänges rekursivt
void reverseRecursive() {
    char ord;                           //lagrar characters in ord
    if ((ord = getchar()) != '\n') {    //getchar tar emot characters o lagrar i "ord", lagras som en stack
        reverseRecursive();             //Kallar på sig själv, rekursiv, blir som en loop
    }
    putchar(ord);                       //Det skrivs ut baklänges, pga LIFO, characters är lagrade som en stack
}                                       //Anledningen till varför man inte behöver array i den rekursiva är för att
                                        //den metoden anropar sig själv lagrar som en stack. Medans den iterativa måste
                                        //spara någonstans, vilket är i arrayen

//en metod som gör inputen baklänges iterativt
void reverseIterative() {
    char arr[20];                  //skapar en array av char med 20 platser
    int i = 0, j, counter = 0;          //initierar variablerna i, j och counter
    
     while((arr[i] = getchar()) != '\n') {        //räknar hur många karaktärer som ordet består av
        counter++;                                //värdet av antalet karaktärer placerar i variabeln counter
        i++;                                      //nästa index i arrayen, lagrar bokstav för bokstav
    }
    
    for(j=counter; j>=0; j--) {          //detta printar ut hela ordet bokstav för bokstav bakifrån
        printf("%c", arr[j]);
    }
}

int main() {
    printf("%s", "Enter your text here: ");
    reverseRecursive();
    printf("\n\n");
    
    printf("%s", "Enter another text here: ");
    reverseIterative();
    printf("\n\n");
    
    return 0;
}
