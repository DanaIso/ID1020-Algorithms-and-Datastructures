package AlgoLab4Task1;
/*
 * Lab4Task1
 * Created by Dana Ismail on 2020-10-06. Updated: 2020-10-06.
 * A queue class mainly for usage of addToFirst
 * Based on: Partly Princeton code BinarySearchST.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {
    private int n;                          // number of elements on list
    private Node<Item> firstReference;
    private Node<Item> reference;

    /**
     * When a new list is created these are the defaults for the list.
     */
    public Stack() {
        firstReference = new Node<>();
        n = 0;
    }

    /**
     * All the attributes needed for a node. A value 'item', a next and previous.
     * @param <Item> a generic object called item
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    /**
     * @return true if list is empty, false otherwise
     */
    public boolean isEmpty()    { return n == 0; }
    /**
     * @return the size of the list, how many elements/nodes
     */
    public int size()           { return n;      }

    /**
     * This method adds a node at the beginning of the list.
     * @param item the 'value' in the new node
     */
    public void addToFirst (Item item) {
        if (size() == 0) {
            firstReference.item = item;
            firstReference.next = firstReference;
            firstReference.prev = firstReference;
            reference = firstReference.prev;
        } else {
            Node<Item> newNode = new Node<>();
            newNode.next = firstReference;
            firstReference.prev = newNode;
            newNode.prev = reference;
            reference.next = newNode;
            newNode.item = item;
            firstReference = newNode;
            reference = firstReference.prev;
        }
        n++;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = firstReference;
        private int index = 0;

        public boolean hasNext()    { return index < n; }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }
    }
}