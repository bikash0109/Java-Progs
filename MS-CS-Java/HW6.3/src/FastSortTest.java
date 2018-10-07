/*
 * A test class to test the storage class.
 *
 * We create a known test data, which after being added to Storage list, we check each item with the original
 * test array, to assert if the method is working using contains() method.
 *
 * */

public class FastSortTest {
    // Creating test data.
    static int[] unsortedIntArray = new int[]{57, 68, 1, 10, 2001, 1400};
    static String[] unsortedStringArray = new String[]{"Blue", "Black", "Magenta", "Red", "Maroon", "Yellow"};
    static int[] sortedIntArray = new int[]{1, 10, 57, 68, 1400, 2001};
    static String[] sortedStringArray = new String[]{"Black", "Blue", "Magenta", "Maroon", "Red", "Yellow"};

    public static boolean testSort(){
        boolean rValue = true;
        StorageI<Integer> intList = new FastSort<>();
        for (int item : unsortedIntArray) {
            intList.add(item);
        }
        intList.sort();
        for(int i = 0; i < sortedIntArray.length ; i++){
            rValue &= intList.contains(sortedIntArray[i]);
        }

        StorageI<String> stringlist = new FastSort<>();
        for (String item : unsortedStringArray) {
            stringlist.add(item);
        }
        stringlist.sort();
        for(int i = 0; i < sortedStringArray.length ; i++){
            rValue &= stringlist.contains(sortedStringArray[i]);
        }
        return rValue;
    }

    public static void test() {
        if (!testSort())
            System.err.println("testSort failed");
    }
}
