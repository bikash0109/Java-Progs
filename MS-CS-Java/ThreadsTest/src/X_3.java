public class X_3 extends Thread    {

    X_3 aT1;

    public void run () {
        System.out.println("enter run");
        try {
            sleep(100);
        }
        catch (  InterruptedException e ) {
            System.out.println("Interrupted!");
            if ( isInterrupted() )
                System.out.println("yup it's true.");
        }
        System.out.println("exit run");

    }
    public static void main (String args []) {
        X_3 aT1  = new X_3();

        aT1.start();
        aT1.interrupt();
    }
}