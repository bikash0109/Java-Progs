public interface StorageI<E>  {
    boolean add(E e);	// 2
    E get();
    void clear();		// 2 3
    boolean contains(E e);
    boolean isEmpty();
    void sort();		// 3
    int size();		// 2 3
    String getClassName();
}

