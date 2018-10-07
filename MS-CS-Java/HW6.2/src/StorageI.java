public interface StorageI<E>  {
    boolean add(E e);
    E get();
    void clear();
    boolean contains(E e);
    boolean isEmpty();
    void sort();
    int size();
    String getClassName();
}

