public class Map<K, V> {
    Storage[] bucket;
    int bucketSize = 10;
    int sumHashCode;

    public Map() {
        bucket = new Storage[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            bucket[i] = null;
        }
    }

    static int ASCIIWordSum(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += str.charAt(i);
        }
        return sum;
    }

    private int getHash(Object key) {
        int something = ASCIIWordSum(key.toString());
        return (something % Math.abs((int) Math.sqrt(bucketSize)) * 8) / 12;
    }

    public V get(Object key) {
        int index = getHash(key);
        System.out.println(index);
        Storage<K, V> head = bucket[index];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    public boolean contains(Object element) {
        int index = getHash(element);
        Storage current = bucket[index];
        while (current != null) {
            if (current.key.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear() {
        this.bucket = new Storage[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            bucket[i] = null;
        }
    }

    public boolean remove(Object element) {
        int index = getHash(element);
        Storage current = bucket[index];
        Storage previous = null;
        while (current != null) {
            if (current.key.equals(element)) {
                if (previous == null) {
                    bucket[index] = current.next;
                } else {
                    previous.next = current.next;
                }
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void add(K key, V value, int size) {
        int index = getHash(key);
        Storage<K, V> traverseNode = bucket[index];
        Storage<K, V> newNode = new Storage<>(key, value);
        if (traverseNode == null) {
            bucket[index] = newNode;
            sumHashCode += bucket[index].hashCode();
        } else {
            while (traverseNode != null) {
                if (traverseNode.key.equals(key)) {
                    traverseNode.value = value;
                    break;
                }
                traverseNode = traverseNode.next;
            }
            if (traverseNode == null) {
                newNode.next = bucket[index];
                bucket[index] = newNode;
                sumHashCode += bucket[index].hashCode();
            }
        }
        if ((1.0 * size) / bucketSize > 0.7) {
            // make bucket size double
            Storage[] oldBucket = bucket;
            bucketSize = 2 * bucketSize;
            bucket = new Storage[bucketSize];
            for (int i = 0; i < bucketSize; i++) {
                bucket[i] = null;
            }
            int i = 0;
            for (Storage<K, V> headNode : oldBucket) {
                while (headNode != null) {
                    add(headNode.key, headNode.value, size);
                    headNode = headNode.next;
                }
                i++;
            }
        }
    }

    public Object[] toArray(int size) {
        Object[] storageArray = new Object[size];
        int i = 0;
        for (Storage<K, V> node : bucket) {
            if (node != null) {
                storageArray[i] = node.value;
                i++;
                if (node.next != null) {
                    Storage<K, V> nodeTemp = node.next;
                    while (nodeTemp != null) {
                        storageArray[i] = nodeTemp.value;
                        nodeTemp = nodeTemp.next;
                        i++;
                    }
                }
            }
        }
        return storageArray;
    }

    public String printMap() {
        String elementValue = "";
        for (Storage<K, V> node : bucket) {
            String withNext = "";
            String withoutNext = node == null ? "\nKey:   -> Value: null" :
                    "\nKey: " + node.key.toString() + " -> Value: " + node.value.toString();
            if (node != null && node.next != null) {
                Storage<K, V> nodeTemp = node.next;
                while (nodeTemp != null) {
                    withNext += " Key:-> " + nodeTemp.key.toString() + " -> Value:" + nodeTemp.value.toString();
                    nodeTemp = nodeTemp.next;
                }
            }
            elementValue += withoutNext + withNext;
        }
        return elementValue;
    }
}