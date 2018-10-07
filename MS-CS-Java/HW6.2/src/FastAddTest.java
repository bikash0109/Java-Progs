/*
 * A test class to test the storage class.
 *
 * We create a known test data, which after being added to Storage list, we check each item with the original
 * test array, to assert if the method is working using contains() method.
 *
 * */

public class FastAddTest {
    static int[] unsortedIntArray = new int[]{57, 68, 1, 10, 2001, 1400};
    static String[] unsortedStringArray = new String[]{"Blue", "Black", "Magenta", "Red", "Maroon", "Yellow"};
    static int[] sortedIntArray = new int[]{1, 10, 57, 68, 1400, 2001};
    static String[] sortedStringArray = new String[]{"Black", "Blue", "Magenta", "Maroon", "Red", "Yellow"};


    public static boolean testAdd() {
        boolean rValue = true;
        StorageI<Integer> intList = new FastAdd<>();
        for (int item : unsortedIntArray) {
            rValue &= intList.add(item);
        }

        for (int item : unsortedIntArray) {
            rValue &= intList.contains(item);
        }

        return rValue;
    }

    public static boolean testGetClassName() {
        boolean rValue = true;
        StorageI<Integer> intList = new FastAdd<>();
        for (int item : unsortedIntArray) {
            rValue &= intList.add(item);
        }
        rValue &= intList.getClassName().contains("Integer");

        StorageI<String> stringList = new FastAdd<>();
        for (String item : unsortedStringArray) {
            rValue &= stringList.add(item);
        }
        rValue &=  stringList.getClassName().contains("String");

        return rValue;
    }

    public static boolean testIsEmpty() {
        boolean rValue = true;
        StorageI<Integer> intList1 = new FastAdd<>();
        rValue &= intList1.isEmpty();

        StorageI<Integer> intList2 = new FastAdd<>();
        for (int item : unsortedIntArray) {
            rValue &= intList2.add(item);
        }
        rValue &= !intList2.isEmpty();

        return rValue;
    }

    public static boolean testSize() {
        boolean rValue = true;
        StorageI<Integer> intList1 = new FastAdd<>();
        rValue &= intList1.size() == 0;

        StorageI<Integer> intList2 = new FastAdd<>();
        for (int item : unsortedIntArray) {
            rValue &= intList2.add(item);
        }
        rValue &= intList2.size() == unsortedIntArray.length;

        return rValue;
    }

    public static boolean testClear() {
        boolean rValue = true;
        StorageI<Integer> intList = new FastAdd<>();
        for (int item : unsortedIntArray) {
            rValue &= intList.add(item);
        }
        intList.clear();
        rValue &= intList.size() == 0;

        return rValue;
    }

    public static boolean testToArray(){
        boolean rValue = true;
        StorageI<Integer> intList = new FastAdd<>();
        for (int item : unsortedIntArray) {
            intList.add(item);
        }
        Object[] storageArray = ((FastAdd<Integer>) intList).toArray();
        for(int i = 0; i < unsortedIntArray.length ; i++){
            rValue &= storageArray[i].equals(unsortedIntArray[i]);
        }
        return rValue;
    }

    public static boolean testSort(){
        boolean rValue = true;
        StorageI<Integer> intList = new FastAdd<>();
        for (int item : unsortedIntArray) {
            intList.add(item);
        }
        intList.sort();
        for(int i = 0; i < sortedIntArray.length ; i++){
            rValue &= intList.contains(sortedIntArray[i]);
        }

        StorageI<String> stringlist = new FastAdd<>();
        for (String item : unsortedStringArray) {
            stringlist.add(item);
        }
        stringlist.sort();
        for(int i = 0; i < sortedStringArray.length ; i++){
            rValue &= stringlist.contains(sortedStringArray[i]);
        }
        return rValue;
    }

    public static boolean testGet(){
        boolean rValue = true;
        StorageI<Integer> intList = new FastAdd<>();
        for (int item : unsortedIntArray) {
            intList.add(item);
        }
        intList.sort();
        var element = intList.get();
        int i = 0;
        while(element != null) {
            rValue &=   sortedIntArray[i] == element;
            element = intList.get();
            i++;
        }

        return rValue;
    }


    public static void test() {
        if (!testAdd())
            System.err.println("testAdd failed");
        if (!testGetClassName())
            System.err.println("testGetClassName failed");
        if (!testIsEmpty())
            System.err.println("testIsEmpty failed");
        if (!testSize())
            System.err.println("testSize failed");
        if (!testClear())
            System.err.println("testClear failed");
        if (!testToArray())
            System.err.println("testToArray failed");
        if (!testSort())
            System.err.println("testSort failed");
        if (!testGet())
            System.err.println("testGet failed");
    }

    public static void main(String args[]) {
        test();
    }
}
