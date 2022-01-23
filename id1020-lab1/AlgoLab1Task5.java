/**
 * Lab1Task5
 * Created by Dana Ismail on 2020-09-06. Updated: 2020-09-09.
 * What problem this code solves: Adding and removing data from a circular doubly linked list.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * Methods for adding/removing from front and back. FIFO queue. Also removing from any position in list.
 * What this code has been based on: Partly based on Princeton code named DoublyLinkedList.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AlgoLab1Task5<Item> implements Iterable<Item> {
    private int n;                          // number of elements on list
    private Node<Item> firstReference;      // sentinel before first item
    private Node<Item> reference;

    /**
     * When a new list is created these are the defaults for the list.
     */
    public AlgoLab1Task5() {
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
            newNode.prev = reference;
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
            firstReference.prev.next = firstReference.next;
            firstReference.next.prev = firstReference.prev;
            firstReference = firstReference.next;
            n--;
        }
    }

    /**
     * This method removes the last node of the list.
     */
    public void removeLast() {
        if (!isEmpty()) {
            if (n < 1) {
                firstReference.next = null;
                firstReference.prev = null;
                firstReference = null;
            }
            else {
                Node<Item> second = firstReference.next;
                Node<Item> last = firstReference.prev;
                second.prev = last;
                last.next = second;
            }
            n--;
        }
        else{
            System.out.print("The list is empty! Please add another item.");
        }
    }

    /**
     * This method removes a node in any desired position in the list.
     * @param position the index of where a node should be removed.
     */
    public void choosePositionToRemove(int position) {
        Node<Item> removing = reference;                             //pointer, pointing to last
        if (position > n || position == 0) {                         //outside of queue
            System.out.println("Index OutOfBounds!");
        } else {
            if (position == n) {
                removing = firstReference;
                removing.prev.next = removing.next;         //inga kopplingar till första
                removing.next.prev = removing.prev;        //sista pekar på andra istället, första borta
                firstReference = reference.next;
            } else{
            for (int i = 1; i < position; i++) {
                removing = removing.prev;
            }
            removing.prev.next = removing.next;
            removing.next.prev = removing.prev;
            }
            n--;
        }
            removing.prev = null;      //garbage collector
            removing.next = null;
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
        AlgoLab1Task5<String> list = new AlgoLab1Task5<>();

        list.addToLast("Hej");
        System.out.println(list);
        list.addToLast("A");
        System.out.println(list);
        list.addToLast("B");
        System.out.println(list);
        list.addToLast("C");
        System.out.println(list);
        list.choosePositionToRemove(4);
        System.out.println(list);
    }
}