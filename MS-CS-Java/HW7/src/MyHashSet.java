public class MyHashSet<E> implements SetI<E> {
    private int size;
    private Map<E, Object> map;

    public MyHashSet() {
        map = new Map<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return map.contains(o);
    }

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
        return valueExists;
    }

    @Override
    public boolean add(E e) {
        map.add(e, e, size);
        size++;
        return true;
    }

    @Override
    public boolean addAll(SetI<? extends E> c) {
        Object[] allAllElements = c.toArray();
        boolean valueAdded = false;
        for (int i = 0; i < allAllElements.length; i++) {
            if (!this.contains(allAllElements[i])) {
                this.add((E) allAllElements[i]);
                valueAdded = true;
            }
        }
        return valueAdded;
    }

    @Override
    public boolean remove(Object o) {
        size--;
        return map.remove(o);
    }

    @Override
    public boolean removeAll(SetI<?> c) {
        Object[] allAllElements = c.toArray();
        boolean valueRemoved = false;
        for (int i = 0; i < allAllElements.length; i++) {
            if (this.contains(allAllElements[i])) {
                this.remove((E) allAllElements[i]);
                valueRemoved = true;
            }
        }
        return valueRemoved;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object[] toArray() {
        return map.toArray(size);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof MyHashSet))
            return false;
        if (obj == this)
            return true;
        return this.containsAll(((MyHashSet) obj));
    }

    @Override
    public int hashCode() {
        return map.sumHashCode;
    }

    public static void main(String[] args) {
        SetI<Integer> stringHashset = new MyHashSet<>();
        for (int i = 1; i < 19; i++) {
            stringHashset.add(i);
        }
        SetI<Integer> intList = new MyHashSet<>();
        for (int i = 1; i < 19; i++) {
            intList.add(i);
        }
        System.out.println(intList.size());
        System.out.println(stringHashset.equals(intList));
        System.out.println(((MyHashSet<Integer>) stringHashset).map.printMap());
        System.out.println(stringHashset.hashCode());
    }
}