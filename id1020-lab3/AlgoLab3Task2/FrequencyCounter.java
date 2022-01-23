package AlgoLab3Task2;
/*
 * Lab3Task2
 * Created by Dana Ismail on 2020-09-24. Updated: 2020-09-24.
 * A program that uses Symbol Table to count frequency of words from a txt file
 * Based on: Princeton code FrequencyCounter.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FrequencyCounter {
    public static void main(String[] args) throws FileNotFoundException {
        int words = 0;
        int maxWords = 1400;
        int minlength = 1;
        long starttime, endtime, time;

        BST<String, Integer> BST = new BST<String,Integer>();      //skapar nytt objekt av BST
        BinarySearchST<String, Integer> binarySearchST = new BinarySearchST<String, Integer>(); //skapar nytt objekt av BSST

        //Read in the txt file
        File myText = new File("/Users/danaismail/Desktop/Algoritmer och Datastrukturer/Lab3/AlgoLab3Task2/src/AlgoLab3Task2/aText.txt");
        Scanner ScanBST = new Scanner(myText);              //gör så att det går att läsa in
        Scanner ScanBinarySearchST = new Scanner(myText);   //gör så att det går att läsa in

        /*BinarySearchST Execution */
        starttime = System.nanoTime();
        while((ScanBinarySearchST.hasNext()) && (words < maxWords)) {
            String key = (ScanBinarySearchST.next()).toLowerCase();

            if(key.length() < minlength) {
                continue;                       //som i++ direkt, hoppar till nästa iteration
            }
            words++;

            if(binarySearchST.contains(key)) {
                binarySearchST.put(key, binarySearchST.get(key) + 1);
            }
            else {
                binarySearchST.put(key,1);
            }
        }

        String max = "";
        binarySearchST.put(max,0);
        for(String word: binarySearchST.keys()) {
            if(binarySearchST.get(word) > binarySearchST.get(max)) {
                max = word;
            }
        }

        endtime = System.nanoTime();
        time = (endtime - starttime) / 1000000;

        System.out.println("----------------------------------------------------");
        System.out.println("BinarySearchST operation took: " + time + " milliseconds to execute");
        System.out.println("The most frequent word was \"" + max + "\" that occured " + binarySearchST.get(max) + " times");


        /* BST Execution */
        words = 0;                              //sätter om words till 0
        starttime = System.nanoTime();
        while((ScanBST.hasNext()) && (words < maxWords)) {
            String key = (ScanBST.next()).toLowerCase();        // the och The blir samma

            if(key.length() < minlength) {      //om uppfylls, gå till nästa iteration
                continue;
            }

            words++;

            if(BST.contains(key)) {                     //räknar frekvensen av ett ord
                BST.put(key, BST.get(key) + 1);     //addera med ett om ordet redan finns i tabellen
            }
            else {
                BST.put(key,1);         //finns inte tidigare i tabellen, automatiskt 1
            }
        }

        String max2 = "";                        //Tom sträng, där vi placerar högst förekommande ordet
        BST.put(max2,0);                     //max har förekommit 0 gånger, tom key, max blir ju en key
        for(String word2: BST.keys()) {          //alla keys placeras i strängen word
            if(BST.get(word2) > BST.get(max2)) {      //itererar genom hela trädet och jämför värdet på nuvarande key
                max2 = word2;                         //byter om värdet/frekvensen på word är större än tidigare max
            }
        }

        endtime = System.nanoTime();
        time = (endtime - starttime) / 1000000;
        System.out.println("----------------------------------------------------");
        System.out.println("BST operation took: " + time + " milliseconds to execute");
        System.out.println("The most frequent word was \"" + max2 + "\" that occured " + BST.get(max2) + " times");

        ScanBST.close();                //"stänger" filen, annars error
        ScanBinarySearchST.close();     //"stänger" filen
    }
}