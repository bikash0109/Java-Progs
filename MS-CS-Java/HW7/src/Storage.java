public class Storage<K, V> {
    public K key;
    public V value;
    public Storage<K, V> next;

    Storage(K key, V value) {
        this.key = key;
        this.value = value;
    }
}