/*
This is a testing class, for the implemented storage class.
* */

public class StorageTest {
    static String theTestStrings[] = {"a", "b", "c"};
    static Storage<String> aStorage = new Storage<>();
    public static void addTestData(Storage<String> aStorage) {
        aStorage.add(0, "a");
        aStorage.add(aStorage.size(), "b");
        aStorage.add(aStorage.size() + 1, "c");
    }
    public static boolean testAddandAddLast() {
        boolean rValue = true;
        for (int index = 0; index < theTestStrings.length; index++)
            aStorage.add(theTestStrings[index]);
        for (int index = 0; index < theTestStrings.length; index++)
            rValue &= aStorage.remove().equals(theTestStrings[index]);
        rValue &= aStorage.remove() == null;
        return rValue;
    }

    public static boolean testAddandAddFirst() {
        boolean rValue = true;
        for (int index = 0; index < theTestStrings.length; index++)
            aStorage.addFirst(theTestStrings[index]);
        for (int index = theTestStrings.length - 1; index >= 0; index--)
            rValue &= aStorage.remove().equals(theTestStrings[index]);
        rValue &= aStorage.remove() == null;
        return rValue;
    }

    public static boolean testRemoveIndex() {
        boolean rValue = true;

        // Adding elements to the storage class
        for (int index = 0; index < theTestStrings.length; index++)
            aStorage.add(theTestStrings[index]);

        // Removing each element and checking, if the returned element is same as my test data input.
        for (int index = aStorage.size(); index > 0; index--)
            rValue &= aStorage.remove(index - 1).equals(theTestStrings[index - 1]);
        rValue &= aStorage.remove() == null;
        return rValue;
    }

    public static boolean testAddIndex() {
        addTestData(aStorage);
        boolean rValue = true;
        for (int index = 0; index < theTestStrings.length; index++)
            rValue &= aStorage.remove().equals(theTestStrings[index]);
        rValue &= aStorage.remove() == null;
        return rValue;
    }

    public static boolean testClear() {
        addTestData(aStorage);
        boolean rValue = true;
        aStorage.clear();
        rValue &= aStorage.size() == 0;
        return rValue;
    }

    public static boolean testSize(){
        addTestData(aStorage);
        boolean rValue = true;
        rValue &= aStorage.size() == theTestStrings.length;
        return rValue;
    }

    public static boolean testElement(){
        addTestData(aStorage);
        boolean rValue = true;
        rValue &= aStorage.element() == theTestStrings[0];
        return rValue;
    }

    public static boolean testRemove(){
        addTestData(aStorage);
        boolean rValue = true;
        rValue &= aStorage.remove() == theTestStrings[0];
        return rValue;
    }

    public static void test() {
        if (!testAddandAddLast())
            System.err.println("testAddandAddLast failed");
        if (!testAddandAddFirst())
            System.err.println("testAddandAddFirst failed");
        if (!testRemoveIndex())
            System.err.println("testRemoveIndex failed");
        if (!testAddIndex())
            System.err.println("testAddIndex failed");
        if (!testClear())
            System.err.println("testClear failed");
        if (!testSize())
            System.err.println("testSize failed");
        if (!testElement())
            System.err.println("testElement failed");
        if (!testRemove())
            System.err.println("testRemove failed");
    }

    
}
