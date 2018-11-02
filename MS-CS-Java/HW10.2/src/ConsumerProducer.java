/*
 * Program Name: ConsumerProducer.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 * A program that implements a multi-threaded solution to produce and consume items simultaneously.
 *
 * logic : use of synchronized block on the class, to allow only one part to access the list simultaneously.
 *
 * */

import java.util.Arrays;

public class ConsumerProducer {
    MyStorage<Integer> list = new MyStorage<>();
    int producerItem, consumerItem, capacity, originalConsumerItem;
    int value = 0;

    ConsumerProducer(int producerItem, int consumerItem, int capacity) {
        this.capacity = capacity;
        this.producerItem = producerItem;
        this.consumerItem = consumerItem;
        this.originalConsumerItem = consumerItem;
    }

    // Function called by producer thread
    public void produce() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (list.size() == producerItem || list.size() == capacity) {
                    wait();
                }
                System.out.println(Thread.currentThread().getName().split("-")[1] + " produce : " + value);
                list.add(value++);
                if (consumerItem == 0)
                    consumerItem = originalConsumerItem;
                // wake up consumer thread
                notifyAll();
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (consumerItem == 0 || list.size() == 0) {
                    wait();
                }
                int val = list.removeFirst();
                consumerItem--;
                System.out.println(Thread.currentThread().getName().split("-")[1] + " consumer : " + val);
                // Wake up producer thread
                notifyAll();
            }
        }
    }

    public static void main(String[] args) throws NoArgumentsException, NegativeNumberException, NotANumberException {
        try {
            int numOfConsumerThread, consumerItems, numOfProducerThread, producerItems, capacity;
            if (args.length == 5) {
                if (Arrays.asList(args).stream().anyMatch(x -> x.contains("-"))) {
                    throw new NegativeNumberException("");
                } else {
                    numOfConsumerThread = Integer.parseInt(args[0]);
                    consumerItems = Integer.parseInt(args[1]);
                    numOfProducerThread = Integer.parseInt(args[2]);
                    producerItems = Integer.parseInt(args[3]);
                    capacity = Integer.parseInt(args[4]);
                }
            } else {
                throw new NoArgumentsException("");
            }

            final ConsumerProducer consumerProducer = new ConsumerProducer(producerItems, consumerItems, capacity);

            Thread[] producerThreads = new Thread[numOfProducerThread];
            Thread[] consumerThreads = new Thread[numOfConsumerThread];

            for (int i = 0; i < producerThreads.length; i++) {
                // Create producer thread
                producerThreads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            consumerProducer.produce();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            for (int i = 0; i < consumerThreads.length; i++) {
                // Create consumer thread
                consumerThreads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            consumerProducer.consume();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            for (int i = 0; i < producerThreads.length; i++) {
                producerThreads[i].start();
            }

            for (int i = 0; i < consumerThreads.length; i++) {
                consumerThreads[i].start();
            }

        } catch (NegativeNumberException ex) {
            throw new NegativeNumberException("Negative number cannot be processed");
        }catch (NoArgumentsException ex){
            throw new NoArgumentsException("Enter correct arguments - in pairs (ignore brackets and commas) (Consumer_Thread_Count, Consumer_item)\" +\n" +
                    "                \" (Producer_Thread_Count, Producer_item) (Storage Size)");
        }catch (NumberFormatException ex){
            throw new NotANumberException("Not a number");
        }
    }
} 