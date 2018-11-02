public class ClassT extends Thread {
    static String info;

    public ClassT(String info) {
        this.info = info;
    }

    private void inProtected() {
        synchronized(info) {
            System.err.println("--->" + info);
            try {
                sleep(5000);
            } catch (Exception e) {
            }
            System.err.println("<---" + info);
        }
    }

    public void run() {
        inProtected();
    }

    public static void main(String args[]) {
        new ClassT("1").start();
        new ClassT("2").start();
        new ClassT("3").start();
    }
}
