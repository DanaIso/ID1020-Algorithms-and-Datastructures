package AlgoLab4Task1;
/*
 * Lab4Task1
 * Created by Dana Ismail on 2020-10-06. Updated: 2020-10-06.
 * A method that is using Binary Search in a Symbol Table to store Keys and Values
 * With inputs as strings(Keys) and integers(Values)
 * Based on: Princeton code BinarySearchST.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    //attributerna till BSST
    private static final int Fixed_Capacity = 2;  //fixed length from start
    private Key[] keys;
    private Value[] vals;
    private int n = 0;          // n = amount of keys in ST

    public BinarySearchST() {
        this(Fixed_Capacity);
    }

    public BinarySearchST(int capacity) {               //initialiserar en tom symboltabell med den valda kapaciteten
        keys = (Key[]) new Comparable[capacity];        //comparable pga kan sorteras automatiskt
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        assert capacity >= n;                               //villkor som måste vara uppfyllt, annars exception
        Key[] tempk = (Key[]) new Comparable[capacity];     //skapar en ny temporär array för keys med den nya kapaciteten
        Value[] tempv = (Value[]) new Object[capacity];     //skapar en ny temporär array för values med den nya kapaciteten
        for(int i = 0; i < n; i++) {                        //kopierar över de existerande keys och values
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;                                       //omderigerar pekare till den nya arrayen
        keys = tempk;
    }

    public int size() {                                      //returnerar storlek
        return n;
    }
    public boolean isEmpty() {                              //returnerar true om tom, false annars
        return size() == 0;
    }

    //kollar om symboltabellen innehåller den angivna key:n, true om den finns, annars false
    public boolean contains(Key key) {
        if(key == null) {
            throw new IllegalArgumentException("Argument to contains() is null!");
        }
        return get(key) != null;            //skickar tillbaka true eller false
    }

    //Returnerar value för givna key för SymbolTable OM key existerar i ST
    public Value get(Key key) {
        if (key == null) { throw new IllegalArgumentException("Argument to get() is null!"); }
        if(isEmpty()) { return null; }
        int i = rank(key);                          //Retrive position of key in need
        if(i < n && keys[i].compareTo(key) == 0) {  //If key position is less than ST size && key retrived matches key searched
            return vals[i];                         //Return value of the index stored with key position
        } else { return null; }                     //Else Key does not exist
    }

    //använder en binary search algoritm, returnerar antalet keys som är mindre än den som söks efter
    public int rank(Key key) {
        if(key == null) { throw new IllegalArgumentException("Argument to rank() is null!"); }

        int lo = 0, hi = n-1;
        while(lo <= hi) {                       //Binary Search Algorithm
            int mid = lo + (hi-lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {                      //If key being search is less than mid key, search in left subarray
                hi = mid - 1;
            }
            else if (cmp > 0) {                //If key being searched is greater than mid key, search in right array
                lo = mid + 1;
            }
            else {                              //Else key funnen
                return mid;                     //Returnera position för key
            }
        }
        return lo;                              //Om sökning misslyckas return lo position
    }

    public void put(Key key, Value val) {       //Sätter in Key och Value till ST
        if(key == null) {
            throw new IllegalArgumentException("First argument to put() is null");
        }

        int i = rank(key);                          //Find key in ST, Else return pos for key to be inserted

        if(i < n && keys[i].compareTo(key) == 0) {  //If key already in ST
            vals[i] = val;                          //Replace the value with chosen value
            return;
        }

        if(n == keys.length) {                      //If key array is full, n = ammount of keys in ST
            resize(2 * keys.length);                //Double the array
        }

        for(int j = n; j > i; j--) {                 //Move keys and values infront of chosen key by 1 step
            //Gör plats för key & value för insättningen
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        keys[i] = key;                              //Nu sätt in den valda key och dess value i dens rätta position
        vals[i] = val;
        n++;                                        //Ökar antalet keys i ST
    }

    public Key min() {          //returnerar minsta värdet, första key, högst upp
        if (isEmpty()) {
            throw new NoSuchElementException("Called min() with empty symbol table!");
        }
        return keys[0];
    }

    public Key max() {          //returnerar max värdet, längst ner i listan
        if (isEmpty()) throw new NoSuchElementException("Called max() with empty symbol table!");
        return keys[n-1];
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("First argument to keys() is null!");
        }
        if (hi == null) {
            throw new IllegalArgumentException("Second argument to keys() is null!");
        }

        Stack<Key> stack = new Stack<Key>();

        if (lo.compareTo(hi) > 0) { return stack; }

        for (int i = rank(lo); i < rank(hi); i++) {
            stack.addToFirst(keys[i]);
        }

        if (contains(hi)) { stack.addToFirst(keys[rank(hi)]); }

        return stack;
    }
}
