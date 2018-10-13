/*
* A test class to test the storage class.
*
* We create a known test data, which after being added to Storage list, we remove each item and check with the original
* test array, to assert if the method is working.
*
* */

public class TestStorage {
    static int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

    public static void exampleOfHowToUseIt() {
        Storage<String> aStorage = new Storage<>();
        Storage<String> bStorage = new Storage<>();
        aStorage.add("a");
        System.out.println("aStorage: " + aStorage);
        bStorage.add("b");
        bStorage.add("c");
        bStorage.add("d");
        if (!aStorage.addAll(aStorage))
            System.out.println("You can not add yourself to yourself.");
        aStorage.addAll(bStorage);
        System.out.println(aStorage);
        System.out.println("Head: " + aStorage.head.value);
        System.out.println("Tail: " + aStorage.tail.value);
        aStorage.removeAll(bStorage);
        System.out.println("aStorage: " + aStorage);

    }

    public static boolean testRemove() {
        boolean rValue = true;
        int[] intArray = new int[]{1, 3};
        Storage<Integer> intList = new Storage<>();
        intList.add(1);
        intList.add(2);
        intList.add(3);
        Object removeObject = 2;
        rValue &= intList.remove(removeObject);
        for (int item : intArray) { //After removing an item, test for the remaining list.
            rValue &= intList.remove().equals(item);
        }
        return rValue;
    }

    public static boolean testRemoveAll() {
        boolean rValue = true;
        int[] intArrayAfterRemoving = new int[]{1, 2, 7, 8};
        Storage<Integer> intList = new Storage<>();
        for (int item : intArray) {
            intList.add(item);
        }
        Storage<Integer> removeList = new Storage<>();
        for (int i = 2; i < 6; i++) {
            removeList.add(intArray[i]);
        }
        rValue &= intList.removeAll(removeList);
        for (int item : intArrayAfterRemoving) { //After removing some items, test for the remaining list.
            rValue &= intList.remove().equals(item);
        }
        return rValue;
    }

    public static boolean testContains() {
        boolean rValue = true;
        Storage<Integer> intList = new Storage<>();
        for (int item : intArray) {
            intList.add(item);
        }
        Object obj = 4;
        rValue &= intList.contains(obj);
        return rValue;
    }

    public static boolean testAdd() {
        boolean rValue = true;
        Storage<Integer> intList = new Storage<>();
        for (int item : intArray) {
            rValue &= intList.add(item);
        }
        for (int item : intArray) {
            rValue &= intList.remove().equals(item);
        }
        return rValue;
    }

    public static boolean testAddAll() {
        boolean rValue = true;
        Storage<Integer> intList = new Storage<>();
        Storage<Integer> intList1 = new Storage<>();
        for (int item : intArray) {
            intList1.add(item);
        }
        rValue &= intList.addAll(intList1);
        for (int item : intArray) {
            rValue &= intList.remove().equals(item);
        }
        return rValue;
    }

    public static boolean testClear() {
        boolean rValue = true;
        Storage<Integer> intList = new Storage<>();
        for (int item : intArray) {
            intList.add(item);
        }
        intList.clear();
        rValue &= intList.head == null;
        return rValue;
    }

    public static boolean testToArray(){
        boolean rValue = true;
        Storage<Integer> intList = new Storage<>();
        for (int item : intArray) {
            intList.add(item);
        }
        Object[] storageArray = intList.toArray();
        for(int i = 0; i < intArray.length ; i++){
            rValue &= storageArray[i].equals(intArray[i]);
        }
        return rValue;
    }

    public static void test() {
        if (!testRemove())
            System.err.println("testRemove failed");
        if (!testRemoveAll())
            System.err.println("testRemove failed");
        if (!testContains())
            System.err.println("testContains failed");
        if (!testAdd())
            System.err.println("testAdd failed");
        if (!testAddAll())
            System.err.println("testAddAll failed");
        if (!testClear())
            System.err.println("testClear failed");
        if (!testToArray())
            System.err.println("testToArray failed");
    }

    public static void main(String args[]) {
//        test();
//        exampleOfHowToUseIt();
        Storage<Animal> animal = new Storage<>();
        animal.add(new Animal(1, "Simba"));
        animal.add(new Animal(1, "Simba"));
    }
}
