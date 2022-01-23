package AlgoLab4Task1;
/*
 * Lab4Task1
 * Created by Dana Ismail on 2020-10-06. Updated: 2020-10-06.
 * A Bag class mainly for usage of add() to add elements to bag
 * Based on: Partly Princeton code Bag.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 */
import java.util.Iterator;
public class Bag<Item> implements Iterable<Item> {
    private Node first;             //första noden i listan

    private class Node {
        Item item;
        Node next;
    }

    public void add(Item item) {  // samma som push() i Stack
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }

    public Iterator<Item> iterator() {  return new ListIterator();  }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;
        public boolean hasNext() {  return current != null;  }
        public void remove() { }
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}