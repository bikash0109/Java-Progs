import java.util.*;

public class Thread_5 extends Thread {
    private String info;
    static Vector aVector;

    public Thread_5(String info, Vector aVector) {
        this.info = info;
        this.aVector = aVector;
    }

    public void inProtected() {
        synchronized (aVector) {
            System.err.println(info + ": is in protected()");
            try {
                var a = info.equals("second");
                if (info.equals("second")) {
                    System.err.println("only second");
                    sleep(1000);
                }
                else{
                    System.err.println("only first");
                    sleep(3000);
                }
            } catch (InterruptedException e) {
                System.err.println("Interrupted!");
            }
            System.err.println(info + ": exit run");
        }
    }

    public void run() {
        inProtected();
    }

    public static void main(String args[]) {
        Vector aVector = new Vector();
        Thread_5 aT5_0 = new Thread_5("first", aVector);
        Thread_5 aT5_1 = new Thread_5("second", aVector);

        aT5_1.start();
        aT5_0.start();
    }
}