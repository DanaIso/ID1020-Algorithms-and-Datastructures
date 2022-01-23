/**
 * Lab1Task3
 * Created by Dana Ismail on 2020-09-05. Updated: 2020-09-09.
 * What problem this code solves: Adding and removing data from a circular doubly linked list.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * Methods for adding items to back and removing item in front. FIFO queue.
 * What this code has been based on: Partly based on Princeton code named DoublyLinkedList.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AlgoLab1Task3V2<Item> implements Iterable<Item> {
    private int n;                          // number of elements on list
    private Node<Item> firstReference;
    private Node<Item> reference;

    /**
     * When a new list is created these are the defaults for the list.
     */
    public AlgoLab1Task3V2() {
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
     * This method adds a node at the last of the list.
     * @param item the 'value' in the new node
     */
    public void addToLast (Item item) {
        if (size() == 0) {
            firstReference.item = item;
            firstReference.next = firstReference;
            firstReference.prev = firstReference;
            reference = firstReference.prev;
        }
        else {
            Node<Item> newNode = new Node<>();
            firstReference.prev = newNode;
            newNode.next = firstReference;
            newNode.prev = reference;               //reference because first wont do it in every case
            reference.next = newNode;
            newNode.item = item;
            reference = firstReference.prev;
        }
        n++;
    }

    /**
     * This method removes the first node of the list.
     */
    public void removeFirst() {
        if (n == 1) {
            firstReference.next = null;
            firstReference.prev = null;
            n--;
        } else if (isEmpty()) {
            System.out.print("The list is empty! Please add another item.");
        } else {
            firstReference.prev.next = firstReference.next;             //sista noden pekar på plats 2 istället
            firstReference.next.prev = firstReference.prev;             //den andra noden pekar på sista
            firstReference = firstReference.next;
            n--;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current = firstReference;
        private int index = 0;

        public boolean hasNext()      { return index < n; }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            index++;
            return item;
        }
    }

    /**
     * Prints out the string with the help of the iterator.
     * @return the string
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append("[").append(item).append("]").append(", ");
        if(!isEmpty()) {s.deleteCharAt(s.lastIndexOf(", "));}
        return s.toString();
    }

    // a test client
    public static void main(String[] args) {
        System.out.println();
        AlgoLab1Task3V2<String> list = new AlgoLab1Task3V2<>();
        list.addToLast("hej");
        list.addToLast("bye");
        //list.addToLast("link");
        //list.addToLast("abc");
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        System.out.println(list);
    }
}