/*
 * Program Name: FastAdd.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * This program implements a generic Storage class. sorts the elements in the Storage class and returns it.
 * */


class FastAdd<T> implements StorageI<T> {
    private int size = 0;
    public Node<T> head = null; // Head of the list
    public Node<T> tail = null;
    private Node<T> tempHead = null;
    private boolean tempHeadAssigned = false;


    @Override
    public boolean add(T e) {
        // create a new node to be added at the end.
        Node<T> newNode = new Node<>(e);
        // if head is pointing to nothing, and head has no data, list is empty.
        if (head == null) {
            head = newNode;
            tail = head;
            size++;
            return true;
        } else if (head.next == null && head.value != null) {  // if head is pointing to nothing, and head has data, append at the end.
            if (!contains(e)) {
                head.next = newNode;
                tail = newNode;
                tail.next = null;
                size++;
                return true;
            }
        } else {
            if (!contains(e)) {
                newNode.next = null; // since, this will be the last node, make its next as null.
                Node<T> last = tail;
                last.next = newNode;
                tail = newNode;
                size++;
                return true;
            }
        }
        return false;
    }


    @Override
    public T get() {
        if(tempHeadAssigned == false) {
            tempHead = head;
            tempHeadAssigned = true;
        }
        T returnValue = tempHead == null? null: tempHead.value;
        if(tempHead != null)
            tempHead = tempHead.next;
        else
            tempHeadAssigned = false;
        return returnValue;
    }


    @Override
    public void clear() {
       this.head = null;
       size = 0;
    }


    @Override
    public boolean contains(T e) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.value.equals(e)) {
                System.out.println("The SET contains this item.");
                return true;
            }
            temp = temp.next;
        }
        return false;
    }


    @Override
    public boolean isEmpty() {
        return head == null;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public String getClassName() {
        return this.head.value.getClass().toString();
    }


    // Returns the Storage items in form of array.
    public Object[] toArray() {
        Object[] storageArray = new Object[size()];
        if (this.size() == 0)
            return null;
        Node<T> temp = head;
        int i = 0;
        while (temp != null) {
            storageArray[i] = temp.value;
            temp = temp.next;
            i++;
        }
        return storageArray;
    }


    @Override
    public void sort() {
        if (this.size == 0) {
            System.out.println("No elements to sort, add some items first !");
            return;
        }
        Object[] sortedArray = new Object[size()];
        sortedArray = selectionSort(sortedArray);
        StorageI<T> sortedList = new FastAdd<>();
        for (Object item : sortedArray) {
            sortedList.add((T) item);
        }
        this.head = (Node<T>) (((FastAdd<Object>) sortedList)).head;
        this.tail = (Node<T>) (((FastAdd<Object>) sortedList)).tail;
    }


    public <E extends Comparable<E>> Object[] selectionSort(Object[] sortedArray) {
        sortedArray = this.toArray();
        for (int i = 0; i < sortedArray.length - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < sortedArray.length; j++) {
                E item1 = (E) (sortedArray[min_index]);
                E item2 = (E) (sortedArray[j]);
                if (item2.compareTo(item1) < 0)
                    min_index = j;
            }
            Object temp = sortedArray[min_index];
            sortedArray[min_index] = sortedArray[i];
            sortedArray[i] = temp;
        }
        return sortedArray;
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
        StorageI<String> stringList = new FastAdd<>();
        stringList.add("black");
        stringList.add("blue");
        stringList.add("red");
        stringList.add("green");

        System.out.println("Before sort: " + stringList);
        System.out.println("Size of Storage: " + stringList.size());
        stringList.sort();
        System.out.println("After sort: " + stringList);
        System.out.println("Is Empty: " + stringList.isEmpty());

        stringList.clear();
        System.out.println("After clear call: " + stringList);
        System.out.println("After clear call -> size: " + stringList.size());


        StorageI<Integer> intList = new FastAdd<>();
        intList.add(5);
        intList.add(10);
        intList.add(1);
        intList.add(1700);
        intList.add(57);


        System.out.println("Before sort: " + intList);
        System.out.println("Size of Storage: " + intList.size());
        intList.sort();

        System.out.println("Get Sorted : ");
        var element = intList.get();
        while(element != null) {
            System.out.println(element);
            element = intList.get();
        }

        System.out.println("After sort: " + intList);
    }
}