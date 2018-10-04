/*
 * Program Name: Storage.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * This program implements a Storage class, using generics. Typically LinkedList implementation.
 * */


import java.util.*;

// The generic storage class
public class Storage<T> {

    // A node to store a value and refer to next node.
    class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    public Node<T> head = null; // Head of the list
    public Node<T> tail = null;

    // Adds new node to the start of the list
    public void addFirst(T value) {
        // create a new node to be added at the beginning.
        Node<T> newNode = new Node<>(value);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            Node<T> temp = head;
            // set the new node's next to the current head.
            newNode.next = head;
            // make the incoming node as the head node.
            head = newNode;
        }
    }

    // Adds a new node to the end of the list
    public boolean add(T value) {
        addLast(value);
        return true;
    }

    // Adds a new node to the end of the list
    public void addLast(T value) {
        // create a new node to be added at the end.
        Node<T> newNode = new Node<>(value);
        // if head is pointing to nothing, and head has no data, list is empty.
        if (head == null) {
            head = newNode;
            tail = newNode;
            tail.next = null;
        } else if (head.next == null && head.value != null) {  // if head is pointing to nothing, and head has data, append at the end.
            head.next = newNode;
            tail = newNode;
            tail.next = null;
        } else {
            newNode.next = null; // since, this will be the last node, make its next as null.
            Node<T> last = tail;
            last.next = newNode;
            tail = newNode;
        }
    }

    // Adds a new node to specific index
    public void add(int index, T value) {
        //create new node.
        Node<T> newNode = new Node<>(value);
        newNode.next = null;
        if (head == null) {
            //if head is null and index is zero then exit.
            if (index != 0) {
                return;
            } else { //node set to the head.
                head = newNode;
                return;
            }
        }
        if (head != null && index == 0) { // have to make head as next item, and incoming node as head
            newNode.next = head;
            head = newNode;
            return;
        }

        // Insert between the current and previous node.
        Node<T> current = head;
        Node<T> previous = null;
        int i = 0;
        while (i < index) {
            previous = current;
            current = current.next;
            if (current == null) {
                break; // if index is out of bounds, break and assign that to be the current node.
            }
            i++;
        }
        newNode.next = current;
        previous.next = newNode;
    }

    public boolean addAll(Collection<? extends T> collection) {
        Iterator<? extends T> itr = collection.iterator();
        if (head.value.getClass().equals(itr.next().getClass())) {
            while (itr.hasNext()) {
                add(itr.next());
            }
        }
        return true;
    }

    // removes the head (first) from the list
    public T remove() {
        if (head == null)
            return null;
        if (head.next != null) {
            T returnValue = head.value;
            head = head.next;
            return returnValue;
        } else {
            T returnValue = head.value;
            head = null;
            return returnValue;
        }
    }

    // removes specific item from the list, (position is determined by index)
    public T remove(int index) {
        if (head == null) // handle null pointer exception.
            return null;
        if (index == 0) { // edge case, when index is 0
            T returnValue = head.value;
            remove();
            return returnValue;
        }

        // Traverse to the indexed node, link previous node's next to current's next
        Node<T> current = head;
        Node<T> previous = null;
        int i = 0;
        while (i != index) {
            previous = current;
            current = current.next;
            if (current == null) {
                break;
            }
            i++;
        }
        T returnValue = current.value;
        previous.next = current.next;
        return returnValue;
    }

    // Prints the entire list (only values)
    public String toString() {
        if (this.size() == 0)
            return null;
        Node<T> temp = head;
        String returnValue = "";
        while (temp != null) {
            returnValue += " -> " + temp.value;
            temp = temp.next;
        }
        return returnValue;
    }

    // returns size of the list
    public int size() {
        if (head == null) {
            return 0;
        }
        Node<T> temp = head;
        int count = 1;
        while (temp.next != null) {
            count++;
            temp = temp.next;
        }
        return count;
    }

    // returns the value of head item, but does't retrieve it.
    public T element() {
        return head == null ? null : head.value;
    }

    // removes all elements from the list
    public void clear() {
        this.head = null;
    }

    public static void main(String[] args) {
        Storage<Integer> intList = new Storage<>();
        intList.addFirst(4);
        intList.addFirst(5);
        intList.addFirst(6);
        intList.addFirst(7);
        intList.addFirst(8);
        System.out.println(intList);
        intList.addLast(9);
        System.out.println(intList);
//        ArrayList<Integer> intList2 = new ArrayList<>();
//        var a = intList2.getClass();
//        var b = intList.getClass();
//        intList2.add(9);
//        intList2.add(10);
//        intList.addAll(intList2);
    }
}