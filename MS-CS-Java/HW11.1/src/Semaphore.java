public class Semaphore {
    protected int n;

    public Semaphore (int n) {
        this.n = n;
    }

    public synchronized void acquire () {
        if (n <= 0) {
            try {
                wait();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        -- n;
    }

    public synchronized void release () {
        if (++ n > 0)
            notify();
    }
}