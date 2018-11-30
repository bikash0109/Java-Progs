/*
 * Program Name: ProducerConsumerServer.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * A ProducerConsumerServer monitor host, which reads in stream from TCP sockets and updates the storage for producer to
 * produce and consumer to consume
 */


import java.io.*;
import java.net.*;

public class ProducerConsumerServer {
    public static MyStorage<String> storage = new MyStorage<>();
    public static int capacity;
    public int sleepTimer;

    public ProducerConsumerServer(int capacity, int sleepTimer){
        this.capacity = capacity;
        this.sleepTimer = sleepTimer;
    }

    // Function called by producer thread
    public void produce(String value) throws InterruptedException {
        synchronized (this) {
            // producer thread waits while list is full
            while (storage.size() == capacity) {
                wait();
            }
            System.out.println("Producer produced-" + value);
            storage.add(value);
            printStorage();
            // notifies the consumer thread that now it can start consuming
            notify();
            Thread.sleep(this.sleepTimer);
        }
    }

    // Function called by consumer thread, has a return type to send to PrintStream for Consumer server to consume
    public String consume() throws InterruptedException {
        synchronized (this) {
            // consumer thread waits while list is empty
            while (storage.size() == 0) {
                wait();
            }
            String val = storage.removeFirst();
            System.out.println("Consumer consumed-" + val);
            printStorage();
            notify();
            Thread.sleep(this.sleepTimer);
            return val;
        }
    }

    public static void printStorage(){
        System.out.println("---Elements in Storage----\n" + storage + "\n--------------------------");
    }

    public static void main(String args[]) {
        if (args.length < 3) {
            System.out.println("Arguments Missing for ProducerConsumer Monitor host: <port> <capacity>" +
                    " <production and consumption rate (thread sleep timeout)>");
            return;
        }
        try{
            ServerSocket s = new ServerSocket(Integer.parseInt(args[0]));
            ProducerConsumerServer server = new ProducerConsumerServer(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            System.out.println("ProducerConsumer Monitor host running, waiting for producer anc consumer to connect.");
            //Accept producer
            Socket producerSocket = s.accept();
            // Create producer thread
            Thread producer = new Thread(new Runnable() {
                PrintStream out = new PrintStream(producerSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(producerSocket.getInputStream()));

                @Override
                public void run() {
                    while (true) {
                        String item = null;
                        try {
                            item = in.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            server.produce(item);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        out.println(item);
                    }
                }
            });
            producer.start();

            //Accept consumer
            Socket consumerSocket = s.accept();
            // Create consumer thread
            Thread consumer = new Thread(new Runnable() {
                PrintStream out = new PrintStream(consumerSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(consumerSocket.getInputStream()));

                @Override
                public void run() {
                    while (true) {
                        try {
                            in.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String item = null;
                        try {
                            item = server.consume();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        out.println(item);
                    }
                }
            });
            consumer.start();
        }catch (SocketException ex) {
            System.err.println("Socket error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.err.println("Not a number: " + ex.getMessage());
        } catch (UnknownHostException ex) {
            System.err.println("Unknown host: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Exception: " + ex.getLocalizedMessage());
        }
    }
}