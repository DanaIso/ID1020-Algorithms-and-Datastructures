package AlgoLab4Task1;
/*
 * Lab4Task1
 * Created by Dana Ismail on 2020-10-06. Updated: 2020-10-06.
 * A class for creating a graph
 * Based on: Partly Princeton code Graph.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 */
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Graph
{   //privata attributer för en graf
    private final int V;            //antalet hörn
    private int E;                  //antalet kanter
    private Bag<Integer>[] adj;     //adjacency lists, lista med angränsande hörn

    //Objekt av typen graf skapas.
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];      //skapa en array av listor
        for (int v = 0; v < V; v++) {           //initiera alla listor
            adj[v] = new Bag<Integer>();        //att tömma
        }
    }

    //Läser in en textfil, lägger till alla angränsningar mha addEdge().
    public Graph(Scanner in) {
        if (in == null) throw new IllegalArgumentException("Argumentet är null!");
        try {
            this.V = in.nextInt();
            if (V < 0) throw new IllegalArgumentException("Antalet hörn i en graf får ej vara negativt!");
            adj = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new Bag<Integer>();
            }
            int E = in.nextInt();
            if (E < 0) throw new IllegalArgumentException("Antalet kanter i en graf får ej vara negativt!");
            for (int i = 0; i < E; i++) {
                int v = in.nextInt();       //läs in ett hörn
                int w = in.nextInt();       //läs in ett annat hörn
                addEdge(v, w);
            }
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Ogiltig input format i Graph konstruktorn", e);
        }
    }

    public int V()  {  return V;  }     //returnerar antalet hörn
    public int E()  {  return E;  }     //returnerar antalet kanter

    //tar emot två hörn och skapar en undirected kant, 2st men räknas som 1.
    public void addEdge(int v, int w) {
        adj[v].add(w);                  //lägg till w till v's lista(bag)
        adj[w].add(v);                  //lägg till v till w's lista(bag)
        E++;                            //++ antalet kanter
    }

    public Iterable<Integer> adj(int v) {  return adj[v];  }
}