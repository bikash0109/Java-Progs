//A hashMap class for HashSet to map its values.
public class Map<K, V> {
    Storage[] bucket;
    int bucketSize = 10;

    //default constructor call, bucket is initialized, and made all node as null
    public Map() {
        bucket = new Storage[bucketSize];
        for (int i = 0; i < bucketSize; i++) {
            bucket[i] = null;
        }
    }

    // Returns an Index
    public int getHash(Object key) {
        String keyAscii;
        if (key == null) {
            keyAscii = "null";
        } else {
            keyAscii = key.toString();
        }
        int asciiWordSum = ASCIIWordSum(keyAscii);
        return asciiWordSum % (keyAscii.length() * 5);
    }

    // Helper method to calculate ascii values
    private int ASCIIWordSum(String str) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum += str.charAt(i);
        }
        return sum;
    }

    // Checks for a single object in the hashmap, also throws runtime exception, NullPointer and ClassCast
    public boolean contains(Object element) throws MyNullPointerException, MyClassCastException {
        int index = getHash(element);
        Storage current = bucket[index];
        while (current != null) {
            if (element == null) {
                if (current.key == element) {
                    return true;
                }
            } else {
                if (current.key == null) {
                    if (current.key == element) {
                        return true;
                    }
                } else {
                    if (current.key.equals(element)) {
                        return true;
                    }
                }
            }
            current = current.next;
        }
        return false;
    }

    // clears the entire list
    public void clear(int size) {
        this.bucket = new Storage[size];
        for (int i = 0; i < size; i++) {
            bucket[i] = null;
        }
    }

    // remove a single element, also throws runtime exceptions of UnsupportedOperationException and ClassCast
    public boolean remove(Object element) throws MyUnsupportedOperationException, MyClassCastException,
            MyNullPointerException {
        int index = getHash(element);
        Storage current = bucket[index];
        Storage previous = null;
        while (current != null) {
            if (element == null) {
                if (current.key == element) {
                    if (previous == null) {
                        bucket[index] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    return true;
                }
            } else {
                if (current.key.equals(element)) {
                    if (previous == null) {
                        bucket[index] = current.next;
                    } else {
                        previous.next = current.next;
                    }
                    return true;
                }
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    // adds a single element, also throws runtime exceptions of UnsupportedOperationException , ClassCast, nullPoiner and IllegalArguments
    public boolean add(K key, V value, int size) throws MyUnsupportedOperationException, MyClassCastException,
            MyIllegalArgumentException, MyNullPointerException {
        boolean valueAdded = false;
        if (this.contains(value)) {
            return valueAdded;
        }
        int index = getHash(key);
        Storage<K, V> traverseNode = bucket[index];
        Storage<K, V> newNode = new Storage<>(key, value);
        if (traverseNode == null) {
            bucket[index] = newNode;
            valueAdded = true;
        } else {
            while (traverseNode != null) {
                if (traverseNode.key == null) {
                    if (traverseNode.key == key) {
                        traverseNode.value = value;
                        valueAdded = true;
                        break;
                    }
                } else {
                    if (traverseNode.key.equals(key)) {
                        traverseNode.value = value;
                        valueAdded = true;
                        break;
                    }
                }
                traverseNode = traverseNode.next;
            }
            if (traverseNode == null) {
                newNode.next = bucket[index];
                bucket[index] = newNode;
                valueAdded = true;
            }
        }
        //Increase the bucket size
        if ((1.0 * size) / bucketSize > 0.7) {
            Storage[] oldBucket = bucket;
            bucketSize = bucketSize + 5;
            bucket = new Storage[bucketSize];
            for (int i = 0; i < bucketSize; i++) {
                bucket[i] = null;
            }
            for (Storage<K, V> headNode : oldBucket) {
                while (headNode != null) {
                    add(headNode.key, headNode.value, size);
                    headNode = headNode.next;
                }
            }
        }
        return valueAdded;
    }

    // Convert the hasMap to an array
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

    // Calculate hashcode for entire HashMap
    public int calcHash() {
        int hashSum = 0;
        for (Storage<K, V> node : bucket) {
            if (node != null) {
                if (node.value == null) {
                    hashSum += 0;
                } else {
                    hashSum += getHash(node.value);
                }
                if (node != null && node.next != null) {
                    Storage<K, V> nodeTemp = node.next;
                    while (nodeTemp != null) {
                        if (nodeTemp.value == null) {
                            hashSum += 0;
                        } else {
                            hashSum += getHash(nodeTemp.value);
                        }
                        nodeTemp = nodeTemp.next;
                    }
                }
            }
        }
        return hashSum;
    }

    // Print the hashmap
    public String printMap() {
        String elementValue = "";
        int i = 0;
        for (Storage<K, V> node : bucket) {
            if (node != null) {
                String withNext = "";
                String withoutNext = "\n" + i + ". " + node.value;
                if (node != null && node.next != null) {
                    Storage<K, V> nodeTemp = node.next;
                    while (nodeTemp != null) {
                        withNext += " -> " + nodeTemp.value;
                        nodeTemp = nodeTemp.next;
                    }
                }
                elementValue += withoutNext + withNext;
            } else {
                String withoutNext = "\n" + i + ". " + node;
                elementValue += withoutNext;
            }
            i++;
        }
        return elementValue;
    }
}