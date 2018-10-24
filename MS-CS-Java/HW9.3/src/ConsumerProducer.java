// Java program to implement solution of producer 
// consumer problem. 

import java.util.LinkedList;

public class ConsumerProducer {

    // Create a list shared by producer and consumer
    // Size of list is 2.
    static LinkedList<Integer> list = new LinkedList<>();
    int capacity = 2;

    // Function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                // producer thread waits while list
                // is full
                while (list.size() == capacity) {
                    System.out.println("Waiting to be consumed.");
                    wait();
                }

                System.out.println("Producer produced-" + value);

                // to insert the jobs in the list
                list.add(value++);

                // notifies the consumer thread that
                // now it can start consuming
                notify();

                Thread.sleep(500);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                // consumer thread waits while list
                // is empty
                while (list.size() == 0) {
                    System.out.println("Waiting to be produced.");
                    wait();
                }

                //to retrive the ifrst job in the list
                int val = list.removeFirst();

                System.out.println("Consumer consumed-" + val);

                // Wake up producer thread
                notify();

                Thread.sleep(500);
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        // Object of a class that has both produce()
        // and consume() methods
        final ConsumerProducer pc = new ConsumerProducer();

        // Create producer thread 
        Thread t1 = new Thread(() -> {
            try {
                pc.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        );

        // Create consumer thread 
        Thread t2 = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        );

        // Start both threads 
        t1.start();
        t2.start();

        // t1 finishes before t2 
        t1.join();
        t2.join();
    }
} 