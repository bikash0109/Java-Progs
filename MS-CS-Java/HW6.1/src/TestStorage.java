public class TestStorage {
    public static void exampleOfHowToUseIt( Storage<String> aStorage)   {
        aStorage = new Storage<String>();
        Storage<String> bStorage = new Storage<String>();
        aStorage.add("a");
        System.out.println("aStorage: " + aStorage );
        bStorage.add("b");
        if ( ! aStorage.addAll(aStorage) )
            System.out.println("You can not add yourself to yourself.");
        aStorage.addAll(bStorage);
        aStorage.removeAll(bStorage);
        System.out.println("aStorage: " + aStorage );

    }
    public static void test(Storage<String> aStorage)   {
//        if ( ! testRemove() )
//            System.err.println("testRemove failed");
//        if ( ! testRemoveAll() )
//            System.err.println("testRemove failed");
//        if ( ! testContains() )
//            System.err.println("testContains failed");
//        if ( ! testAdd() )
//            System.err.println("testAdd failed");
//        if ( ! testAddAll() )
//            System.err.println("testAddAll failed");
//        if ( ! testClear() )
//            System.err.println("testClear failed");
    }
    public static void main(String args[] )     {
        //est(new Storage<String>());
        exampleOfHowToUseIt(new Storage<String>());

    }
}
