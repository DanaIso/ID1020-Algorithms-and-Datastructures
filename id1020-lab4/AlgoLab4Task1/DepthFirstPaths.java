package AlgoLab4Task1;
/*
 * Lab4Task1
 * Created by Dana Ismail on 2020-10-06. Updated: 2020-10-06.
 * Algorithm for searching through graph using DFS
 * Based on: Partly Princeton code DepthFirstPaths.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 */
public class DepthFirstPaths {
    private final boolean[] marked;       //kontrollerar om dfs() har blivit anropad för detta hörn?
    private final int[] edgeTo;           //senaste hörnet på den kända vägen till detta hörn
    private final int s;            //källan, (start hörnet), roten

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];        //skapar en array med plats för grafens antal hörn
        edgeTo = new int[G.V()];            //skapar en array med plats för grafens antal hörn
        this.s = s;                         //s skapas, den som har skickats in i metoden
        dfs(G, s);                          //dfs() anropas
    }

    private void dfs(Graph G, int v) {      //metoden som gör själva sökningen
        marked[v] = true;                   //i början blir källan markerad
        for (int w : G.adj(v)) {            //kollar angränsande hörn
            if (!marked[w]) {
                edgeTo[w] = v;              //sparar pathen från källan till nuvarande hörn. Kan användas för backtacking.
                dfs(G, w);                  //rekursivt anrop
            }
        }
    }

    public boolean hasPathTo(int v) {  return marked[v];  }     //returnerar true eller false

    //går baklänges, backtracking
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();         //skapar en ny kö, sparar hörnen
        for (int x = v; x != s; x = edgeTo[x]) {
            path.addToFirst(x);                             //pushar den senaste besökta hörnet
        }
        path.addToFirst(s);
        return path;
    }
}