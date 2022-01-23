/**
 * Lab1Task2
 * Created by Dana Ismail on 2020-09-01. Updated: 2020-09-03.
 * What problem this code solves: This code takes a word and prints it backwards.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * Using methods like push, pop and recursiveReverse to reverse a word.
 * What this code has been based on: Based on Princeton code named Stack.java.
 * Copyright © 2020 Dana Ismail. All rights reserved.
 */

import java.util.NoSuchElementException;
import java.util.*;

/**
 *  The {@code Stack} class represents a last-in-first-out (LIFO) stack of generic items.
 *  It supports the usual <em>push</em> and <em>pop</em> operations, along with methods
 *  for peeking at the top item, testing if the stack is empty, and iterating through
 *  the items in LIFO order.
 *  <p>
 *  This implementation uses a singly linked list with a static nested class for
 *  linked-list nodes. See {@link //LinkedStack} for the version from the
 *  textbook that uses a non-static nested class.
 *  See {@link //ResizingArrayStack} for a version that uses a resizing array.
 *  The <em>push</em>, <em>pop</em>, <em>peek</em>, <em>size</em>, and <em>is-empty</em>
 *  operations all take constant time in the worst case.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/13stacks">Section 1.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Item> the generic type of an item in this stack
 */
public class AlgoLab1Task2<Item> {
    private Node<Item> first;     // top of stack

    /**
     * Attributes for a node. A node has a item inside, and a next node.
     * @param <Item>
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Initializes an empty stack.
     */
    public AlgoLab1Task2() {
        first = null;
    }

    /**
     * Returns true if this stack is empty.
     *
     * @return true if this stack is empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Adds the item to this stack.
     *
     * @param  item the item to add
     */
    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldfirst;
    }

    /**
     * Removes and returns the item most recently added to this stack.
     *
     * @return the item most recently added
     * @throws NoSuchElementException if this stack is empty
     */
    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        Item item = first.item;        // save item to return
        first = first.next;            // delete first node
        return item;                   // return the saved item
    }


    /**
     * Prints out words backwards recursively
     *
     * @param ord the word to be reversed
     * @return character for character
     */
    public static String recursiveReverse(String ord) {
        if(ord.length() <= 1)
            return ord;
        else
            return recursiveReverse(ord.substring(1)) + ord.charAt(0);
    }

    /**
     * Unit tests the {@code Stack} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);                      //"förbereder" för inmatning från användaren
        AlgoLab1Task2<String> stacken = new AlgoLab1Task2<>();          //skapar en stack som heter stacken

        System.out.println("Skriv in ett ord: ");
        String ordet = scan.nextLine();

        for(int i = 0; i < ordet.length(); i++) {
            stacken.push(ordet.substring(i,i+1));       //pushar bokstav för bokstav
        }
        for(int i = 0; i < ordet.length(); i++) {
            System.out.print(stacken.pop());            //poppar i LIFO ordning, därav reverse
        }

        System.out.println();
        System.out.println("Skriv in ett andra ord: ");
        String ordet2 = scan.nextLine();
        System.out.println(recursiveReverse(ordet2));
    }
}