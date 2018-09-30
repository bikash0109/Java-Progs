class Xa_2 {

    private final int aInt = 1;
    private final String aString = "abc";
    private       String bString = "abc";
    private final String[] aArray = new String[10];

    public void doTheJob() {
        // aInt = 3;
        // aString = aString + "abc";		// 1
        // aString = aString;			// 2
        // aArray = new String[10];		// 3
        bString = aString;			// 4
        bString = aString + "def";		// 5
        aArray[0] = "abc";			// 6
        // bString = 1 - "1";			// 7
        bString = 1  * 2 + "1";			// 8
    }

    public static void main( String args[] ) {
        new Xa_2().doTheJob();
    }
}