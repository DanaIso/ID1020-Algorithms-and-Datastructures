package AlgoLab3Task4;
/*
 * Lab3Task4
 * Created by Dana Ismail on 2020-09-28. Updated: 2020-09-28.
 * Finds the indices for specific word in a text
 * Based on: Princeton code BinarySearchST.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AlgoLab3Task4 {

    // Visar index för när ordet uppkommer i textfilen
    public static void wordAtIndex(String word, BinarySearchST<String, ArrayList<Integer>> theFinder) {
        ArrayList<Integer> indexList = theFinder.get(word);
        if (indexList == null) {
            System.out.println("Text file do not contain the word: " + word + "");
            return;
        }
        System.out.println("The word \"" + word + "\" occurs on these indices: \n" + theFinder.get(word));
    }

    private static final String FILE_PATH = "/Users/danaismail/Desktop/Algoritmer och Datastrukturer/write.txt";

    private static Scanner fileScanner(String filePath) {
        try {
            return new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            System.out.println("The file is not found!");
            return null;
        }
    }

    public static BinarySearchST<String, ArrayList<Integer>> build(String filePath) {
        BinarySearchST<String, ArrayList<Integer>> theFinder = new BinarySearchST<String, ArrayList<Integer>>(1000);
        Scanner scanner = fileScanner(filePath);
        if (scanner == null) {
            return null;
        }
        scanner.useDelimiter("");       //delimiter låter scanner endast skanna en char i taget
        int counter = 1;
        StringBuilder sb = new StringBuilder();
        while (scanner.hasNext()) {
            char tecken = scanner.next().toLowerCase().charAt(0);
            if (Character.isAlphabetic(tecken)) {
                sb.append(tecken);                  //lägger in i stringbuilder
                counter++;
                continue;
            }
            String word = sb.toString();
            if (word.length() == 0) {           //gäller för sista tecknet i texten
                if (tecken == ' ') {
                    counter++;
                }
                continue;
            }

            ArrayList<Integer> arrayofIndex = theFinder.get(word);
            if (arrayofIndex == null) {
                arrayofIndex = new ArrayList<>();
            }
            arrayofIndex.add(counter - word.length());
            theFinder.put(word, arrayofIndex);
            sb = new StringBuilder();
            if (tecken == ' ') {                        //counter++, eftersom räknar ett mellanrum nu
                counter++;
            }
        }
        return theFinder;
    }

    public static void main(String[] args) {
        BinarySearchST<String, ArrayList<Integer>> theFinder = build(FILE_PATH); //allt är redan uträknat nu, alla index
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Enter a word to search for: ");
            wordAtIndex(scanner.nextLine().toLowerCase(), theFinder);
            System.out.println("Do you want to exit the program? (Yes/No)");
        } while (scanner.nextLine().equals("No"));
    }
}
