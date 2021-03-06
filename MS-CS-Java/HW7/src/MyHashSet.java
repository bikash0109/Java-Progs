/*
 * Program Name: MyHashSet.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * This program implements a generic HashSet. implements a sebset interface of Set..
 * */


public class MyHashSet<E> implements SetI<E> {
    private int size;
    private Map<E, Object> map;

    // Invoke the Map Class, where hashing will be done
    public MyHashSet() {
        map = new Map<>();
    }

    // returns the size of the hashset
    @Override
    public int size() {
        return size;
    }

    // Check if the hashset is empty
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Checks if an object exits in the hashset
    @Override
    public boolean contains(Object o) {
        return map.contains(o);
    }

    // Checks if all the elements of a set is present in given set.
    @Override
    public boolean containsAll(SetI<?> c) {
        Object[] allAllElements = c.toArray();
        boolean valueExists = false;
        for (int i = 0; i < allAllElements.length; i++) {
            if (this.contains(allAllElements[i])) {
                valueExists = true;
            } else {
                valueExists = false;
            }
        }
        if (valueExists && this.size != c.size()) {
            valueExists = false;
        }
        return valueExists;
    }

    // Add a single item to the hashSet
    @Override
    public boolean add(E e) {
        boolean isAdded = map.add(e, e, size);
        if (isAdded) {
            size++;
        }
        return isAdded;
    }

    // Add all elements to the HashSet, if item does't exits
    @Override
    public boolean addAll(SetI<? extends E> c) {
        Object[] allAllElements = c.toArray();
        boolean valueAdded = false;
        for (int i = 0; i < allAllElements.length; i++) {
            if (!this.contains(allAllElements[i])) {
                valueAdded = this.add((E) allAllElements[i]);
            }
        }
        return valueAdded;
    }

    // remove the object form the HashSet, if it exists
    @Override
    public boolean remove(Object o) {
        boolean valueRemoved = map.remove(o);
        if (valueRemoved) {
            size--;
        }
        return valueRemoved;
    }

    // Remove all elements from the HashSet, if item does't exits
    @Override
    public boolean removeAll(SetI<?> c) {
        Object[] allAllElements = c.toArray();
        boolean valueRemoved = false;
        for (int i = 0; i < allAllElements.length; i++) {
            if (this.contains(allAllElements[i])) {
                this.remove(allAllElements[i]);
                valueRemoved = true;
            }
        }
        return valueRemoved;
    }

    // Clear the existing hashSet
    @Override
    public void clear() {
        map.clear(size);
        size = 0;
    }

    // Convert the hashSet to an array.
    @Override
    public Object[] toArray() {
        return map.toArray(size);
    }

    // Override the default equals to accommodate set equal capability
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof MyHashSet))
            return false;
        if (obj == this)
            return true;
        return this.containsAll(((MyHashSet) obj));
    }

    // Returns sum of all hascode of the hashMaps.
    @Override
    public int hashCode() {
        return map.calcHash();
    }

    // Overriding the default toString() to print the elements
    public String toString() {
        return map.printMap();
    }

    public static void main(String[] args) {
        MyHashSetTest.test();
    }
}