public class H extends Thread {
    String info = "";
    static Object o = new Object();

    public H(String info) {
        this.info = info;
    }

    public void run() {
        synchronized (o) {
            try {
                while (true) {
                    System.out.println(info);
                    o.notify();
                    sleep(100);
                    o.wait();
                }
            } catch (Exception ex) {
            }
        }
    }

    public static void main(String[] args) {
        new H("0").start();
        new H("1").start();
    }
}
