import java.util.Random;

class FastAdd<T> implements StorageI<T>{
    public int size = 0;
    public Node<T> head = null; // Head of the list
    public Node<T> tail = null;
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
        return head == null ? null : head.value;
    }

    @Override
    public void clear() {
        this.head = null;
    }

    @Override
    public boolean contains(T e) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.value.equals(e))
                return true;
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
        for(Object item: sortedArray){
            sortedList.add((T)item);
        }
        this.head = (Node<T>)(((FastAdd<Object>) sortedList)).head;
        this.tail = (Node<T>)(((FastAdd<Object>) sortedList)).tail;
    }

    public <E extends Comparable<E>> Object[] selectionSort(Object[] sortedArray) {
        sortedArray = this.toArray();
        for (int j = 0; j < sortedArray.length - 1; j++) {
            int smallest = j;
            for (int k = j + 1; k < sortedArray.length; k++) {
                E item1 = (E)(sortedArray[smallest]);
                E item2 = (E)(sortedArray[j]);
                if (item2.compareTo(item1)<=0) {
                    smallest = k;
                }
            }
            swap(sortedArray, j, smallest);  // swap smallest to front
        }
        return sortedArray;
    }

    private <E> void swap(Object[] a, int i, int j) {
        if (i != j) {
            Object temp = a[i];
            a[i] = a[j];
            a[j] = temp;
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
        StorageI<String> stringList = new FastAdd<>();
        stringList.add("banish");
        stringList.add("eden");
        stringList.add("god");
        stringList.add("alchemy");
        stringList.add("caught");


        System.out.println("Before sort: " + stringList);
        stringList.sort();
        System.out.println("After sort: " + stringList);


        StorageI<Integer> intList = new FastAdd<>();
        intList.add(5);
        intList.add(10);
        intList.add(1);
        intList.add(1700);
        intList.add(57);


        System.out.println("Before sort: " + intList);
        intList.sort();
        System.out.println("After sort: " + intList);
    }
}