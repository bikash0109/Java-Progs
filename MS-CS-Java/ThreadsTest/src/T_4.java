/*
 * is this output	--->
 *			<--
 *			...
 * the only possible output?
 * nur ein objekt wird benutzt
 */
public class T_4 extends Thread {

    static Object o = new Object();
    String info;

    public T_4(String info) {
        this.info = info;
    }

    public void waitForABit() {
        synchronized (o) {
            System.err.println("--->" + info + " waitForABit");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted!");
            }
            System.err.println("<---" + info+ " waitForABit");
        }
    }

    public void run() {
        synchronized (o) {
            System.err.println("--->" + info);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.err.println("Interrupted!");
            }
            System.err.println("<---" + info);
        }
    }

    public static void main(String args[]) {
        T_4 t4 = new T_4("1");
        t4.waitForABit();
        t4.start();
    }
}