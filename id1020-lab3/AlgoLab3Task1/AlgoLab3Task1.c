/*
 @author Dana Ismail
 created 24/09-2020. updated 24/09-2020
 Filtering a text file
 */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

void replace() {
    FILE *readTheFile = fopen("thetext.txt", "r");
    FILE *writeToFile = fopen("writeto.txt", "w");
    
    if(readTheFile == NULL || writeToFile == NULL) {
        printf("An error occurred!\n");
        exit(1);
    }
    
    char c;
    while((c = fgetc(readTheFile)) != EOF) {
        if(!isalpha(c)) {   //om inte en bokstav byt då med ett mellanslag
            fputc(' ', writeToFile);
        }
        else {
            fputc(c, writeToFile);
        }
    }
}


int main(int argc, const char * argv[]) {
    replace();
    return 0;
}
