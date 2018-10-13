
public class Map<K, V>{

    Storage[] bucket;
    int bucketSize = 10;
    int size;

    public Map() {
        bucket = new Storage[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            bucket[i] = null;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    static int ASCIIWordSum(String str)
    {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += str.charAt(i);
        }
        return sum;
    }

    private int getHash(K key) {
        int something = (int)Math.sqrt(ASCIIWordSum(key.toString()));
        return Math.abs(something) % bucketSize;
    }

    public V get(K key) {
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

    public V remove(K key) {
        int index = getHash(key);
        Storage<K, V> head = bucket[index];
        if (head == null) {
            return null;
        }
        if (head.key.equals(key)) {
            V val = head.value;
            head = head.next;
            bucket[index] = head;
            size--;
            return val;
        } else {
            Storage<K, V> prev = null;
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
        Storage<K, V> traverseNode = bucket[index];
        Storage<K, V> newNode = new Storage<>(key, value);
        if (traverseNode == null) {
            bucket[index] = newNode;
            size++;

        } else {
            while (traverseNode != null) {
                if (traverseNode.key.equals(key)) {
                    traverseNode.value = value;
                    size++;
                    break;
                }
                traverseNode = traverseNode.next;
            }
            if (traverseNode == null) {

//                Entry entryInOldBucket = new Entry(key, value);
//                entryInOldBucket.next = table[bucket];
//                table[bucket] = entryInOldBucket;

                newNode.next = bucket[index];
                bucket[index] = newNode;
                size++;
            }
        }
        if ((1.0 * size) / bucketSize > 0.7) {
            // make bucket size double
            Storage[] tmp = bucket;
            bucketSize = 2 * bucketSize;
            bucket = new Storage[bucketSize];
            for (int i = 0; i < bucketSize; i++) {
                bucket[i] = null;
            }
            for (Storage<K, V> headNode : tmp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public String printMap() {
        String elementValue = "";
        for (Storage<K, V> node : bucket) {
            if (node != null) {
                String withNext ="";
                String withoutNext = "\nKey: " + node.key.toString() + " -> Value: " + node.value.toString();
                if(node.next != null){
                    Storage<K, V> nodeTemp = node.next;
                    while (nodeTemp.next != null){
                        withNext += "Key:->" +nodeTemp.key.toString() + "Value: ->" + nodeTemp.value.toString();
                        nodeTemp = nodeTemp.next;
                    }
                }
                elementValue += withoutNext + withNext;
            }
        }
        return elementValue;
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new Map<>();
        map.add("just", 1);
        map.add("like", 2);
        map.add("this", 3);
        map.add("just1", 4);
        map.add("like1", 5);
        map.add("this1", 6);
        map.add("just2", 7);
        map.add("like2", 8);
        map.add("this2", 9);
        map.add("just3", 10);
        map.add("like3", 11);
        map.add("this4", 12);
        map.add("just5", 13);
        map.add("like5", 14);
        map.add("this5", 15);
        map.add("just6", 16);
        map.add("like6", 17);
        map.add("this6", 18);
        System.out.println(map.printMap());
        System.out.println(map.get("like1"));
    }
}