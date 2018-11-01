/*
 * Will end up in a dead lock
 *
 *
 */
public class DeadLock extends Thread	{
    private String info;
    Object o_1;
    Object o_2;
    Object stop;
    static boolean oneIsRunning = false; // is static important?
    // es wird nur ein
    // Objekt erzeugt
    public DeadLock (String info, Object o_1, Object o_2, Object stop) {
        this.info    = info;
        this.o_1    = o_1;
        this.o_2    = o_2;
        this.stop    = stop;
    }
    public void one () {
        synchronized ( o_1 ) {
            System.out.println(info);
            try {
                synchronized ( stop ) {
                    if ( ! oneIsRunning )	{
                        new DeadLock("1", o_1, o_2, stop).start();
                        oneIsRunning = true;
                    }
                    stop.wait();
                    synchronized ( o_2 ) {
                        System.out.println("I will not get there");
                    }
                }
            } catch ( Exception e ) { }
        }
    }
    public void two () {
        synchronized ( o_2 ) {
            synchronized ( stop ) {
                stop.notify();
            }
            System.out.println(info);
            try {
                synchronized ( o_1 ) {
                    System.out.println("I will not get there");
                }
            } catch ( Exception e ) { }
        }
    }
    public void run () {
        if ( info.equals("0") )
            one();
        else
            two();
    }
    public static void main (String args []) {
        Object o_1 = new Object();
        Object o_2 = new Object();
        Object stop = new Object();
        new DeadLock("0", o_1, o_2, stop).start();
    }
}
