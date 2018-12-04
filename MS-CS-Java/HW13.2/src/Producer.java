// A producer thread class, which is a client to invoke remote produce method

import java.rmi.Naming;

class Producer extends Thread {
    private StorageInterface sharedSpace;

    public Producer(StorageInterface sharedSpace) {
        this.sharedSpace = sharedSpace;
    }

    public void run() {
        int counter = 0;

        while (true) {
            try {
                sharedSpace.produce(counter++);
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Arguments Missing for Producer: <port>");
            return;
        }
        // remote object
        StorageInterface sharedSpace;
        try {
            String connectionString = "//:" + Integer.parseInt(args[0]) + "/remoteServer";
            sharedSpace = (StorageInterface) Naming.lookup(connectionString);

            Producer ProducerThread = new Producer(sharedSpace);

            ProducerThread.start();
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}