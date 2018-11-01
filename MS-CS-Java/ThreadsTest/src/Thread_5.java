import java.util.*;

public class Thread_5 extends Thread {
    private String info;
    private Vector aVector;

    public Thread_5(String info, Vector aVector) {
        this.info = info;
        this.aVector = aVector;
    }

    public void inProtected() {
        synchronized (aVector) {
            System.out.println(info + ": is in protected()");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted!");
            }
            System.out.println(info + ": exit run");
        }
    }

    public void run() {
        inProtected();
    }

    public static void main(String args[]) {
        Thread_5 aT5_0 = new Thread_5("first", new Vector());
        aT5_0.start();
        Thread_5 aT5_1 = new Thread_5("second", new Vector());
        aT5_1.start();

    }
}