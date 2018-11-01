public class X_4 extends Thread    {
    static int  theValue  = 0;
    X_4(int id)       {
        this.theValue = id;
    }
    public void run () {
        if ( theValue == 2 )
            System.out.println("1. " + theValue );
        if ( theValue == 4 )
            System.out.println("2. " + theValue );
    }

    public static void main (String args []) {
        new X_4(1).start();;
        new X_4(2).start();;
        System.out.println("3. " + theValue );
        new X_4(3).start();;

        System.out.println("4. " + theValue );
        new X_4(4).start();;
        System.out.println("5. " + theValue );
    }
}  