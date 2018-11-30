/*
 * Program Name: MyStorage.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * A custom Storage unit, based on linked list Data Structure.
 */


import java.util.*;

public class MyStorage<E> {
    private static class Node<E> {
        private E data;
        private Node<E> next;

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private int size = 0;
    private Node<E> head;

    public MyStorage() {
        head = null;
    }

    public void addFirst(E item) {
        head = new Node<>(item, head);
        size++;
    }

    public E getFirst() {
        if (head == null) throw new NoSuchElementException();

        return head.data;
    }

    public E removeFirst() {
        E tmp = getFirst();
        head = head.next;
        size--;
        return tmp;
    }

    public void add(E item) {
        if (head == null)
            addFirst(item);
        else {
            Node<E> tmp = head;
            while (tmp.next != null) {
                tmp = tmp.next;
            }

            tmp.next = new Node<>(item, null);
            size++;
        }
    }

    public int size() {
        return size;
    }

    // Prints the entire list (only values)
    public String toString() {
        if (this.size() == 0)
            return null;
        Node<E> temp = head;
        String returnValue = "";
        while (temp != null) {
            returnValue +=  temp.data + " | ";
            temp = temp.next;
        }
        return returnValue;
    }
}