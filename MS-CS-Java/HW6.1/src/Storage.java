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
            if (!contains(value)) {
                // set the new node's next to the current head.
                newNode.next = head;
                // make the incoming node as the head node.
                head = newNode;
            }
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
            addFirst(value);
        } else if (head.next == null && head.value != null) {  // if head is pointing to nothing, and head has data, append at the end.
            if (!contains(value)) {
                head.next = newNode;
                tail = newNode;
                tail.next = null;
            }
        } else {
            if (!contains(value)) {
                newNode.next = null; // since, this will be the last node, make its next as null.
                Node<T> last = tail;
                last.next = newNode;
                tail = newNode;
            }
        }
    }

    // Adds a new node to specific index
    public void add(int index, T value) {
        if (index > size() - 1) {
            System.out.println("Index greater than the size of the Storage.");
            return;
        }
        //create new node.
        Node<T> newNode = new Node<>(value);
        newNode.next = null;
        if (head == null && index == 0) {
            addFirst(value);
            return;
        }
        if (head != null && index == 0) { // have to make head as next item, and incoming node as head
            if (!contains(value)) {
                newNode.next = head;
                head = newNode;
            }
            return;
        }
        if (!contains(value)) {
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
    }

    public boolean addAll(Storage<T> collection) {
        Node<T> temp = collection.head;
        while (temp != null) {
            if (!contains(temp.value))
                add(temp.value);
            temp = temp.next;
        }
        return true;
    }

    boolean contains(Object o) {
        Node<T> temp = head;
        while (temp != null) {
            if (temp.value.equals(o))
                return true;
            temp = temp.next;
        }
        return false;
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
        if (index > size() - 1) {
            System.out.println("Index greater than size.");
            return null;
        }
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
        if (current != null) {
            T returnValue = current.value;
            previous.next = current.next;
            return returnValue;
        }
        return null;
    }

    public boolean remove(Object o) {
        if (head == null) {
            return false;
        }
        if (head != null && o.equals(head.value)) {
            remove();
            return true;
        }
        Node<T> current = head;
        Node<T> previous = null;
        while (!o.equals(current.value)) {
            previous = current;
            current = current.next;
            if (current == null) {
                break;
            }
        }
        if (current != null) {
            previous.next = current.next;
            if (previous.next == null)
                tail = previous;
            return true;
        }
        return false;
    }

    public boolean removeAll(Storage<T> collection) {
        boolean itemRemoved = false;
        Node<T> temp = collection.head;
        while (temp != null) {
            if (contains(temp.value)) {
                remove(temp.value);
                itemRemoved = true;
                temp = temp.next;
            }
        }
        return itemRemoved;
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

    public static void main(String[] args) {
        Storage<Integer> list1 = new Storage();
        Storage<Integer> list1Elements = new Storage<>();
        list1Elements.add(1);
        list1Elements.add(2);
        list1Elements.add(3);
        list1Elements.add(4);
        list1Elements.add(5);
        list1Elements.add(6);
        list1Elements.add(7);
        list1.addAll(list1Elements);
        System.out.println(list1.head.value);
        System.out.println(list1.tail.value);
        System.out.println("First List is. " + list1);
        Storage<Integer> removeList = new Storage<>();
        removeList.add(4);
        removeList.add(5);
        removeList.add(6);
        list1.removeAll(removeList);
        System.out.println(list1.head.value);
        System.out.println(list1.tail.value);
        System.out.println("Removed List is. " + list1);
        System.out.println("Object array. " + list1.toArray());
    }
}