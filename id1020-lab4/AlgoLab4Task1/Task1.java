package AlgoLab4Task1;
/*
 * Lab4Task1
 * Created by Dana Ismail on 2020-10-06. Updated: 2020-10-06.
 * A class for running Task1 - Finding a path between two points.
 * Based on: None.
 * Copyright © 2020 Dana Ismail. All rights reserved.
 */
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        SymbolGraph symGraph = new SymbolGraph("/Users/danaismail/Desktop/Algoritmer och Datastrukturer/Lab4/AlgoLab4Task1/src/AlgoLab4Task1/theDatabase.txt");

        System.out.println("Välj en startpunkt: ");
        String startPoint = in.nextLine().toUpperCase();
        int startingIndex = symGraph.indexOf(startPoint);

        System.out.println("Välj en slutpunkt: ");
        String endPoint = in.nextLine().toUpperCase();
        int endIndex = symGraph.indexOf(endPoint);

        Graph aGraph = symGraph.graph();

        DepthFirstPaths dfs = new DepthFirstPaths(aGraph, startingIndex);

        if(!dfs.hasPathTo(startingIndex)) {
            System.out.println("Ingen väg funnen!");
        } else {
            for(int f : dfs.pathTo(endIndex)) {
                if(f == endIndex) {
                    System.out.print(symGraph.nameOf(f));
                }
                else {
                    System.out.print(symGraph.nameOf(f) + " ——> ");
                }
            }
        }
        in.close();         //stänger inläsning
    }
}
