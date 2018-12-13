public class X_6 extends Thread    {
    private String info;
    Object o_1;
    Object o_2;
    Object stop;
    static boolean oneIsRunning = false; // is static important?
    // es wird nur ein
    // Objekt erzeugt
    public X_6 (String info, Object o_1, Object o_2, Object stop) {
        this.info    = info;
        this.o_1     = o_1;
        this.o_2     = o_2;
        this.stop    = stop;
    }
    public void tryIt () {
        synchronized ( o_1 ) {
            try {
                synchronized ( stop ) {
                    if ( ! oneIsRunning )    {
                        System.err.println("in stop");
                        new X_6("1", o_2, o_1, stop).start();
                        oneIsRunning = true;
                        stop.wait();
                        System.err.println("stop exit");
                        sleep(1000);
                    } else {
                        System.err.println(info + " start");
                        stop.notify();
                        sleep(1000);
                        System.err.println(info + " end");
                    }

                }
                System.err.println("1. info: " + info );
                synchronized ( o_2 ) {
                    System.err.println("2. info: " + info );
                }
            } catch ( Exception e ) { }
        }
    }
    public void run () {
        tryIt();
    }
    public static void main (String args []) {
        Object o_1 = new Object();
        Object o_2 = new Object();
        Object stop = new Object();
        new X_6("0", o_1, o_2, stop).start();
    }
}