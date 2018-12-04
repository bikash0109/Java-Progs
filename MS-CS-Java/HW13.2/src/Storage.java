/*
 * Program Name: Storage.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * A Storage server, which adds and removes items form the storage
 */

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.*;

public class Storage extends java.rmi.server.UnicastRemoteObject implements
        StorageInterface {

    public static int capacity;
    private MyDataStructure<Integer> storage;
    Registry registry;

    public Storage(int capacity) throws RemoteException {
        this.storage = new MyDataStructure<>();
        this.capacity = capacity;
    }

    @Override
    public synchronized void produce(int item) throws InterruptedException {
        while (storage.size() == capacity) {
            wait();
        }
        storage.add(item);
        System.out.println("Produced " + item);
        Thread.sleep(3000);
        notify();
    }

    @Override
    public synchronized int consume() throws InterruptedException {
        int item;
        while (storage.size() == 0) {
            wait();
        }
        item = storage.removeFirst();
        System.out.println("Consumed " + item);
        notify();
        Thread.sleep(3000);
        return item;
    }

    public static void main(String args[]) {
        if (args.length < 2) {
            System.out.println("Arguments Missing for ProducerConsumer Monitor host: <port> <capacity>");
            return;
        }
        try {
            Storage server = new Storage(Integer.parseInt(args[1]));
            server.registry = LocateRegistry.createRegistry(Integer.parseInt(args[0]));
            server.registry.rebind("remoteServer", server);
            System.out.println("Server Bound");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}