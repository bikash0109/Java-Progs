import java.util.*;

public class Thread_0 extends Thread    {
    private String info;
    static  Object o = new Object();

    public Thread_0 (String info) {
        this.info    = info;
    }

    public void run () {
        synchronized (o) {
            System.err.println(info + ": is in protected()");
            System.err.println(info + ": exit run");
        }
    }

    public static void main (String args []) {
         new Thread_0("first").start();
         new Thread_0("second").start();
    }
}