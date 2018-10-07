/*
 * Program Name: FastSort.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * This program implements a generic Storage class. Elements are sorted before inserting in the storage.
 * */


class FastSort<T> implements StorageI<T> {

    private class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    private int size = 0;
    private Node<T> head = null; // Head of the list
    private Node<T> tempHead = null;
    private boolean tempHeadAssigned = false;


    @Override
    public boolean add(T e) {
        // create a new node to be added.
        Node<T> newNode = new Node<>(e);
        sortedAdd(newNode);
        return true;
    }

    public <E extends Comparable<E>> void sortedAdd(Node<T> newNode) {
        Node<T> temp;
        if (this.head == null || ((E) (this.head.value)).compareTo((E) (newNode.value)) >= 0) {
            newNode.next = this.head;
            this.head = newNode;
            size++;
        } else {
            temp = head;
            while (temp.next != null && ((E) temp.next.value).compareTo((E) newNode.value) < 0)
                temp = temp.next;

            newNode.next = temp.next;
            temp.next = newNode;
            size++;
        }
    }

    // This get method returns the value of the nodes , in ordered manner.
    @Override
    public T get() {
        if (!tempHeadAssigned) {
            tempHead = head;
            tempHeadAssigned = true;
        }
        T returnValue = tempHead == null ? null : tempHead.value;
        if (tempHead != null)
            tempHead = tempHead.next;
        else
            tempHeadAssigned = false;
        return returnValue;
    }


    // clears the whole list
    @Override
    public void clear() {
        this.head = null;
        size = 0;
    }


    // Checks if a value already exits in the Storage
    @Override
    public boolean contains(T e) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.value.equals(e)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    // Checks if the Storage is empty or not.
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // Returns the size of the Storage
    @Override
    public int size() {
        return size;
    }

    // Returns the run time class name
    @Override
    public String getClassName() {
        return this.head.value.getClass().toString();
    }


    // Sorts the Storage
    @Override
    public void sort() {
        if (this.size == 0) {
            System.out.println("No elements to sort, add some items first !");
            return;
        } else {
            System.out.println("Sorting done !");
        }
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

    public static void main(String[] args) {
        FastSortTest.test();
    }
}
