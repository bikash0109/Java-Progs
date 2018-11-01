import java.util.*;

public class X_1 extends Thread {
    static Object o = new Object();
    static int counter = 0;
    int id;

    public X_1(int id, Object object) {
        this.id = id;
        this.o = object;
    }

    public void run() {
        synchronized (o) {
            System.err.println(id + " --->");
            System.err.println(id + " <---");
        }
    }

    public static void main(String args[]) {
        Object o = new Object();
        new X_1(1, o).start();
        new X_1(2, o).start();
    }
}
