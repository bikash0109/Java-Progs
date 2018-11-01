public class Xa_1 extends Thread   {
    private String info;
    static  Object o = new Object();

    public  Xa_1 (String info) {
        this.info    = info;
    }
    public void run () {
        synchronized ( o ) {
            System.out.println("--> " + info);
            try {
                //o.notify();
                sleep(1000);
                o.notify();
            } catch ( Exception e ) { }
            System.out.println("<-- " + info);
        }
    }
    public static void main (String args []) {
        ( new Xa_1("0") ).start();
        ( new Xa_1("1") ).start();
    }
}