public class MyHashSetTest {
    static int[] intArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8};

    public static boolean testRemoveAll() {
        boolean rValue = true;
        SetI<Integer> intList = new MyHashSet<>();
        for (int item : intArray) {
            intList.add(item);
        }
        SetI<Integer> removeList = new MyHashSet<>();
        for (int i = 2; i < 6; i++) {
            removeList.add(intArray[i]);
        }
        rValue &= intList.removeAll(removeList);
        return rValue;
    }

    public static boolean testContainsAll() {
        boolean rValue = true;
        SetI<Integer> intList = new MyHashSet<>();
        for (int item : intArray) {
            intList.add(item);
        }
        SetI<Integer> containAllList = new MyHashSet<>();
        for (int item : intArray) {
            containAllList.add(item);
        }
        rValue &= intList.containsAll(containAllList);
        return rValue;
    }

    public static boolean testClear() {
        boolean rValue = true;
        SetI<Integer> intList = new MyHashSet<>();
        for (int item : intArray) {
            intList.add(item);
        }
        intList.clear();
        rValue &= intList.size() == 0;
        return rValue;
    }

    public static void test(){
        SetI<Integer> intHash = new MyHashSet<>();
        for (int i = 1; i < 19 ; i++){
            intHash.add(i);
        }
        SetI<String> aSet = new MyHashSet<>();
        SetI<String> bSet = new MyHashSet<>();

        String[] aStrings = {"a", "b", "c"};
        String[] bStrings = {"A", "B", "C"};
        aSet.add(aStrings[0]);
        aSet.add(aStrings[1]);           // setup a, b
        bSet.add(bStrings[0]);
        bSet.add(bStrings[1]);           // setup A, B

        System.out.println("aSet = " + aSet);                  // --> a, b

        for (int index = 0; index < aStrings.length; index++) {       // contans a and b, not c
            System.out.println("does " +
                    (aSet.contains(aStrings[index]) ? "" : " not ") + "contain: " +
                    aStrings[index]);
        }
        System.out.println("aSet = " + aSet);                  // --> a, b

        System.out.println("aSet.remove(aStrings[0]); = " + aSet.remove(aStrings[0])); // contains b
        System.out.println("aSet.remove(aStrings[2]); = " + aSet.remove(aStrings[2])); // can not remove x
        System.out.println("aSet = " + aSet);

        aSet.addAll(bSet);                                      // --> b, A, B
        System.out.println("aSet = " + aSet);


        aSet.add(null);                                         // --> b, A, B, null
        System.out.println("aSet = " + aSet);
        System.out.println("aSet.remove(null); = " + aSet.remove(null));       // can remove null
        System.out.println("aSet = " + aSet);
        System.out.println("\nintHash with collisions = " + intHash);
        if (!testRemoveAll())
            System.err.println("testRemoveAll failed");

        if (!testContainsAll())
            System.err.println("testContainsAll failed");

        if (!testClear())
            System.err.println("testClear failed");
    }
}
