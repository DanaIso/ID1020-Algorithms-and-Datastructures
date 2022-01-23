/**
 * Lab1Task6
 * Created by Dana Ismail on 2020-09-09. Updated: 2020-09-09.
 * What problem this code solves: Adding in order and removing data from a circular doubly linked list.
 * How this code is used(how it is executed, what input it takes, what it outputs):
 * Methods for adding items in ascending order and removing from any desired position.
 * What this code has been based on: Partly based on Princeton code named DoublyLinkedList.java
 * Copyright © 2020 Dana Ismail. All rights reserved.
 * @author Dana Ismail
 */

public class AlgoLab1Task6 {
    private int n;                          // number of elements on list
    private Node firstReference;
    private Node reference;

    /**
     * When a new list is created these are the defaults for the list.
     */
    public AlgoLab1Task6() {
        firstReference = new Node();
        n = 0;
    }

    /**
     * All the attributes needed for a node. A value 'item', a next and previous.
     */
    private static class Node {
        private int item;
        private Node next;
        private Node prev;
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
     * This method adds elements in ascending order.
     * @param item the 'value' in the new node
     */
    public void addInOrder(int item) {
        Node ny = new Node();
        ny.item = item;

        if(n == 0)                              //ifall inga noder i listan, lägg bara först
        {
            firstReference.item = item;
            firstReference.next = firstReference;
            firstReference.prev = firstReference;
        }

        else if(ny.item < firstReference.item)      //om den nyas värde är mindre än den första
        {
            firstReference.prev.next = ny;
            ny.prev = firstReference.prev;
            ny.next = firstReference;
            firstReference.prev = ny;
            firstReference = ny;
        }

        else
        {
            Node temp = firstReference;
            for(int i = 1; i <= n; i++)
            {
                if(ny.item >= temp.item)
                {
                    temp = temp.next;
                }
            }

            ny.next = temp;
            ny.prev = temp.prev;
            temp.prev.next = ny;
            temp.prev = ny;
        }
        n++;
        printList();
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
                Node second = firstReference.next;
                Node last = firstReference.prev;
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
     * This method adds a new node in any desired position in the list.
     * @param position the index of where the new node should be.
     */
    public void choosePositionToRemove(int position) {
        Node removing = reference;                             //pointer, pointing to last
        if (position > n || position == 0) {                         //outside of queue
            System.out.println("Index OutOfBounds!");
        } else {
            if (position == n) {
                removing = firstReference;
                removing.prev.next = removing.next;
                removing.next.prev = removing.prev;
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


    /**
     * Prints out the string with the help of the iterator.
     */
    public void printList() {
        Node curr = firstReference;
        for(int i = 1; i <= n; i++) {
            if (i == n) {                                       //sista noden, printa utan kommatecken.
                System.out.println("[" + curr.item + "]");
                break;
            }
            System.out.print("[" + curr.item + "] , ");
            curr = curr.next;                                   //fortsätter till nästa nodens värde.
        }
    }

    // a test client
    public static void main(String[] args) {
        System.out.println();
        AlgoLab1Task6 list = new AlgoLab1Task6();

        list.addInOrder(1);
        list.addInOrder(2);
        list.addInOrder(1);
        list.addInOrder(3);
        list.addInOrder(5);
        list.addInOrder(10);
        list.addInOrder(77);
        list.addInOrder(0);
        list.addInOrder(-2);
        list.printList();
    }
}