import java.util.ArrayList;

public class Map<K, V> {

    ArrayList<HashNode<K, V>> bucket = new ArrayList<>();
    int numBuckets = 10;
    int size;

    public Map() {
        for (int i = 0; i < numBuckets; i++) {
            bucket.add(null);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getHash(K key) {
        int hashCod = key.hashCode();
        return hashCod % numBuckets;
    }

    public V get(K key) {
        int index = getHash(key);
        HashNode<K, V> head = bucket.get(index);
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {
        int index = getHash(key);
        HashNode<K, V> head = bucket.get(index);
        if (head == null) {
            return null;
        }
        if (head.key.equals(key)) {
            V val = head.value;
            head = head.next;
            bucket.set(index, head);
            size--;
            return val;
        } else {
            HashNode<K, V> prev = null;
            while (head != null) {

                if (head.key.equals(key)) {
                    prev.next = head.next;
                    size--;
                    return head.value;
                }
                prev = head;
                head = head.next;
            }
            size--;
            return null;
        }
    }

    public void add(K key, V value) {
        int index = getHash(key);
        HashNode<K, V> traverseNode = bucket.get(index);
        HashNode<K, V> newNode = new HashNode<>();
        newNode.key = key;
        newNode.value = value;
        if (traverseNode == null) {
            bucket.set(index, newNode);
            size++;

        } else {
            while (traverseNode != null) {
                if (traverseNode.key.equals(key)) {
                    traverseNode.value = value;
                    size++;
                    break;
                }
                if (traverseNode == null) {
                    traverseNode = bucket.get(index);
                    newNode.next = traverseNode;
                    bucket.set(index, traverseNode);
                    size++;
                }
                traverseNode = traverseNode.next;
            }
        }
        if ((1.0 * size) / numBuckets > 0.7) {
            // make bucket size double
            ArrayList<HashNode<K, V>> tmp = bucket;
            bucket = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            for (int i = 0; i < numBuckets; i++) {
                bucket.add(null);
            }
            for (HashNode<K, V> headNode : tmp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new Map<>();
        map.add("just", 1);
        map.add("like", 2);
        map.add("this", 3);
        System.out.println(map.get("this"));
    }

}