// A consumer thread class, which is a client to invoke remote consume method

import java.rmi.Naming;

class Consumer extends Thread {
    private StorageInterface sharedSpace;

    public Consumer(StorageInterface sharedSpace) {
        this.sharedSpace = sharedSpace;
    }

    public void run() {
        while (true) {
            try {
                sharedSpace.consume();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Arguments Missing for Consumer: <port>");
            return;
        }
        // remote object
        StorageInterface sharedSpace;
        try {
            String connectionString = "//:" + Integer.parseInt(args[0]) + "/remoteServer";
            sharedSpace = (StorageInterface) Naming.lookup(connectionString);

            Consumer ConsumerThread = new Consumer(sharedSpace);

            ConsumerThread.start();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}