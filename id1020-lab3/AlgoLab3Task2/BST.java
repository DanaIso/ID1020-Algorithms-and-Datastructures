package AlgoLab3Task2;
/*
 * Lab3Task2
 * Created by Dana Ismail on 2020-09-24. Updated: 2020-09-24.
 * A method that is using Binary Search Tree to store keys and values.
 * The inputs are Strings and Integers (Keys & Values)
 * Based on: Princeton code BST.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.NoSuchElementException;

public class BST<Key extends Comparable <Key>, Value> {
    private Node root;              //Root of Tree

    private class Node {            //Properties of the Node
        private Key key;            //Sorted by key
        private Value val;          //Value associated with key
        private Node left, right;   //Left & Right Subtrees
        private int size;           //Number of nodes in the subtree

        public Node(Key key, Value val, int size) {  //Constructor
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {   //Return number of key-value pairs in tree rooted at selected node
        if(x == null) {
            return 0;
        }
        else {
            return x.size;
        }
    }

    public boolean contains(Key key) {   //Check if the symbol table contains the given key, Return true or false
        if(key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;        //Return only if key is found
    }                                   //returnerar true om om det finns ett value till key:n

    public Value get(Key key) {
        return get(root, key);  //Search for key, start at root
    }

    private Value get(Node x, Key key) {        //hämtar value för en key
        if(key == null) {                       //för nyckeln
            throw new IllegalArgumentException("calls get() with a null key");
        }

        if(x == null) {                         //för noden
            return null;
        }

        int cmp = key.compareTo(x.key);     //Compare key with x.key (root in first iteration), går höger/vänster tills position funnen

        if(cmp < 0) {                       //If key is less than checked node
            return get(x.left,key);         //Check left node for key
        }
        else if(cmp > 0) {                  //Else if key is greater than checked node
            return get(x.right,key);        //Check right node for key
        }
        else {                              //Else key found! Return found key
            return x.val;
        }
    }

    public void put(Key key, Value val) {
        if(key == null) {                //No key inserted
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = put(root, key, val);     //Insert new key, start at root
    }

    private Node put(Node x, Key key, Value val) {
        if(x == null) {                          //If tree / subtree is empty
            return new Node(key, val, 1);       //Create a node and set key, value and size
        } //detta blir roten

        int cmp = key.compareTo(x.key);         //Compare key to chosen node (root in first iteration)

        if(cmp < 0) {                            //If key is less than checked node
            x.left = put(x.left, key, val);     //Go left and try create node in subtree
        }
        else if(cmp > 0) {                       //Else if key is greater than checked node
            x.right = put(x.right, key, val);   //Go right and try create node in subree
        }
        else {                                   //Else if node is found, set value of chosen node
            x.val = val;
        }

        x.size = 1 + size(x.left) + size(x.right);  //Update chosen node size with containing left and right subarrays

        return x;
    }

    public int rank(Key key) {
        if(key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(key,root);
    }

    private int rank(Key key, Node x) {
        if(x == null) {
            return 0;
        }

        int cmp = key.compareTo(x.key);

        if(cmp < 0) {
            return rank(key,x.left);
        }
        else if(cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        }
        else {
            return size(x.left);
        }
    }

    public Key min() {
        if(isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).key;           //får en nod men .key gör så att man får key:n
    }

    private Node min(Node x) {
        if(x.left == null) {
            return x;
        }
        else {
            return min(x.left);
        }
    }

    public Key max() {
        if(isEmpty()) {
            throw new NoSuchElementException("calls max() with empty symbol table");
        }
        return max(root).key;
    }

    private Node max(Node x) {
        if(x.right == null) {
            return x;
        }
        else {
            return max(x.right);
        }
    }

    public Iterable<Key> keys() {
        if(isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    //returnerar alla keys mellan low och high, givet intervall
    public Iterable<Key> keys(Key lo, Key hi) {
        if(lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if(hi == null) {
            throw new IllegalArgumentException("last argument to keys() is null");
        }

        Queue<Key> queue = new Queue<Key>();

        keys(root, queue, lo, hi);
        return queue;
    }

    //returns all wanted keys in the queue
    private void keys (Node x, Queue<Key> queue, Key lo, Key hi) {
        if(x == null) {
            return;
        }

        int cmplo = lo.compareTo(x.key);

        int cmphi = hi.compareTo(x.key);

        if(cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }

        if(cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }

        if(cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }
}