package AlgoLab4Task1;
/*
 * Lab4Task1
 * Created by Dana Ismail on 2020-10-06. Updated: 2020-10-06.
 * A class for creating a Symbol Graph using a ST.
 * Based on: Partly Princeton code SymbolGraph.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class SymbolGraph {
    private BinarySearchST<String, Integer> st;        // string blir till index
    private String[] keys;                            // index blir till string
    private Graph graph;                             // the underlying graph

    /**
     * Initializes a graph from a file using the specified "delimiter". Each line in
     * the file contains the name of a vertex, followed by a list of the names of
     * the vertices adjacent to that vertex, separated by split(" ").
     *
     * @param theFileToRead  the name of the file
     */
    public SymbolGraph(String theFileToRead) throws FileNotFoundException {
        st = new BinarySearchST<String, Integer>();

        //Läser in textfil
        Scanner in = new Scanner(new FileReader(theFileToRead));

        //First pass builds the index by reading strings to associate
        //distinct strings with an index
        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(" ");
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i])) {
                    st.put(a[i], st.size());    //st.size() representerar index för varje sträng som läggs till i ST:n.
                }
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];

        for (String name : st.keys()) {
            keys[st.get(name)] = name;         //ivertering sker, array med strängar som har arrayens indexering
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Graph(st.size());

        in = new Scanner(new FileReader(theFileToRead));

        while (in.hasNextLine()) {
            String[] a = in.nextLine().split(" ");
            int v = st.get(a[0]);
            int w = st.get(a[1]);
            graph.addEdge(v, w);
        }
    }

    /**
     * Does the graph contain the vertex named {@code s}?
     * @param s the name of a vertex
     * @return {@code true} if {@code s} is the name of a vertex, and {@code false} otherwise
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named {@code s}.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named {@code s}
     */
    public int indexOf(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer {@code v}.
     * @param  v the integer corresponding to a vertex (between 0 and <em>V</em> - 1)
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * @return the name of the vertex associated with the integer {@code v}
     */
    public String nameOf(int v) {
        return keys[v];
    }

    /**
     * Returns the graph assoicated with the symbol graph.
     * @return the graph associated with the symbol graph
     */
    public Graph graph() {
        return graph;
    }
}