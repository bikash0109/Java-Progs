public class X_1b extends Thread    {
    private String info;
    Object o_1;
    Object o_2;
    Object stop;
    static boolean oneIsRunning = false;

    public X_1b (String info, Object o_1, Object o_2, Object stop) {
        this.info    = info;
        this.o_1    = o_1;
        this.o_2    = o_2;
        this.stop    = stop;
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
    public void one () {
        synchronized ( o_1 ) {
            System.out.println(info);
            try {
                if ( ! oneIsRunning )    {
                    new X_1b("1", o_1, o_2, stop).start();
                    oneIsRunning = true;
                }
                synchronized ( stop ) {
                    stop.wait();
                    synchronized ( o_2 ) {
                        System.out.println("I will not get there");
                    }
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
        new X_1b("0", o_1, o_2, stop).start();
    }
}