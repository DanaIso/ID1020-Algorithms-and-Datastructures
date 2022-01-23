package AlgoLab3Task4;
/*
 * Lab3Task4
 * Created by Dana Ismail on 2020-09-28. Updated: 2020-09-28.
 * A method that is using Binary Search in a Symbol Table to store Keys and Values
 * With inputs as strings(Keys) and integers(Values)
 * Based on: Princeton code BinarySearchST.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.NoSuchElementException;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
    //attributerna till ST
    private static final int INIT_CPACITY = 2;  //fixed length from start
    private Key[] keys;
    private Value[] vals;
    private int n = 0;          // n = amount of keys in ST

    public BinarySearchST() {   //Initializes an empty symbol table, constructor
        this(INIT_CPACITY);
    }

    public BinarySearchST(int capacity) { //Constructor, initializes an empty symbol table with the chosen capacity
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    private void resize(int capacity) {
        assert capacity >= n;                               //villkor som måste vara uppfyllt, annars exception
        Key[] tempk = (Key[]) new Comparable[capacity];     //Creates a new temporary array for keys with new capacity
        Value[] tempv = (Value[]) new Object[capacity];     //Creates a new temporary array for values with new capacity
        for(int i = 0; i < n; i++) {                        //Copies over the existing keys & values
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }
        vals = tempv;                                       //Redirect pointers to the new array
        keys = tempk;
    }

    public int size() {                                      //Returns size
        return n;
    }
    public boolean isEmpty() {                              //Returns true if empty, false if not
        return size() == 0;
    }

    public boolean contains(Key key) { //checks if the symbol table contains the given key, True if it cointains the key, otherwise false
        if(key == null) {
            throw new IllegalArgumentException("Argument to contains() is null");
        }

        return get(key) != null;            //skickar tillbaka true eller false
    }

    public Value get(Key key) {  //Returns value of the given key from the Symbol Table IF key exists in the ST
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }

        if(isEmpty()) {
            return null;
        }

        int i = rank(key);                          //Retrive position of key in need

        if(i < n && keys[i].compareTo(key) == 0) {  //If key position is less than ST size && key retrived matches key searched
            return vals[i];                         //Return value of the index stored with key position
        }
        else {
            return null;                            //Else Key does not exist
        }
    }

    //använder en binary search algoritm, räknar ut antalet keys som är mindre än den som söks efter
    public int rank(Key key) { //Returns the number of keys in the ST strictly less than chosen Key
        if(key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }

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
            else {                              //Else found the key
                return mid;                     //Return position of key
            }
        }
        return lo;                              //If search failed return lo position
    }

    public void put(Key key, Value val) {       //Inserting Key and Value to the ST
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
            //Making space for key & value to be inserted
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        keys[i] = key;                              //Now insert the chosen key and its value in its right position
        vals[i] = val;
        n++;                                        //Incrementing amount of keys in ST
    }

    public Key min() {          //returnerar minsta värdet, första key, högst upp
        if (isEmpty()) {
            throw new NoSuchElementException("called min() with empty symbol table");
        }
        return keys[0];
    }

    public Key max() {          //returnerar max värdet, längst ner i listan
        {
            if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        }
        return keys[n-1];
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }

        Queue<Key> queue = new Queue<Key>();

        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) {
            queue.enqueue(keys[rank(hi)]);
        }
        return queue;
    }
}
