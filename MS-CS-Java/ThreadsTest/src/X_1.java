public class X_1 extends Thread    {
    private String info;
    Object o_1;
    Object o_2;
    Object stop;

    public X_1 (String info, Object o_1, Object o_2, Object stop) {
        this.info    = info;
        this.o_1    = o_1;
        this.o_2    = o_2;
        this.stop    = stop;
    }
    public void one () {
        try { sleep(100); } catch (Exception e ) {}
        System.out.println("one: 1" );
        synchronized ( o_1 ) {
            try {
                synchronized ( stop ) {
                    System.out.println("one: before wait" );
                    stop.wait();
                    System.out.println("one: after wait" );
                }
                synchronized ( o_2 ) {
                    System.out.println("I will not get there");
                }
            } catch ( Exception e ) { }
        }
    }
    public void two () {
        synchronized ( o_1 ) {
            try {
                synchronized ( stop ) {
                    stop.notify();
                    System.out.println("two: after stop.notify()" );
                }
                synchronized ( o_2 ) {
                    System.out.println("I will not get there");
                }
            } catch ( Exception e ) { }
        }
        System.out.println("two: exiting two" );
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
        new X_1("0", o_1, o_2, stop).start();
        new X_1("1", o_2, o_1, stop).start();
    }
}