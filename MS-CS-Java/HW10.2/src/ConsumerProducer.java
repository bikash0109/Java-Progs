/*
 * Program Name: ConsumerProducer.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 * A program that implements a multi-threaded solution to produce and consume items simultaneously, with 3 kinds of producer
 *
 * logic : use of synchronized block on the class, to allow only one part to access the list simultaneously.
 *
 * */

import java.util.Arrays;
import java.util.Random;

public class ConsumerProducer {
    MyStorage<Integer> listInt = new MyStorage<>();
    MyStorage<String> listString = new MyStorage<>();
    MyStorage<Boolean> listBool = new MyStorage<>();
    int capacity;
    int value = 0;

    ConsumerProducer(int capacity) {
        this.capacity = capacity;
    }

    // Function called by producer thread
    public void produce(String type) throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (listInt.size() + listBool.size() + listString.size() == capacity) {
                    wait();
                }
                int valueCounter = value++;
                if (type.equals("int")) {
                    int intItem = valueCounter;
                    System.out.println(Thread.currentThread().getName().split("-")[1] + " produce : " + intItem);
                    listInt.add(intItem);
                }

                if (type.equals("string")) {
                    String strItem = "Str_" + valueCounter;
                    System.out.println(Thread.currentThread().getName().split("-")[1] + " produce : " + strItem);
                    listString.add(strItem);
                }

                if (type.equals("bool")) {
                    Boolean boolItem = valueCounter % 2 == 0 ? true : false;
                    System.out.println(Thread.currentThread().getName().split("-")[1] + " produce : " + boolItem);
                    listBool.add(boolItem);
                }

                // wake up consumer thread
                notifyAll();

                //Thread.sleep(1000);
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (listInt.size() + listBool.size() + listString.size() == 0) {
                    wait();
                }
                if (listInt.size() > 3) {
                    for (int i = 0; i < 3; i++) {
                        int val = listInt.removeFirst();
                        System.out.println(Thread.currentThread().getName().split("-")[1] + " consumer : " + val);
                    }
                }
                if (listString.size() > 5) {
                    for (int i = 0; i < 5; i++) {
                        String str = listString.removeFirst();
                        System.out.println(Thread.currentThread().getName().split("-")[1] + " consumer : " + str);
                    }
                }
                if (listBool.size() > 2) {
                    for (int i = 0; i < 2; i++) {
                        Boolean bool = listBool.removeFirst();
                        System.out.println(Thread.currentThread().getName().split("-")[1] + " consumer : " + bool);
                    }
                }
                // Wake up producer thread
                notifyAll();

                //Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) throws NoArgumentsException, NegativeNumberException, NotANumberException {
        try {
            int numOfConsumerThread, numOfProducerThread, capacity;
            if (args.length == 3) {
                if (Arrays.asList(args).stream().anyMatch(x -> x.contains("-"))) {
                    throw new NegativeNumberException("");
                } else {
                    numOfConsumerThread = Integer.parseInt(args[0]);
                    numOfProducerThread = Integer.parseInt(args[1]);
                    capacity = Integer.parseInt(args[2]);
                }
            } else {
                throw new NoArgumentsException("");
            }

            final ConsumerProducer consumerProducer = new ConsumerProducer(capacity);

            Thread[] producerThreads = new Thread[numOfProducerThread];
            Thread[] consumerThreads = new Thread[numOfConsumerThread];

            final String[] producerType = {"int", "string", "bool"};

            System.out.println();

            for (int i = 0; i < producerThreads.length; i++) {
                Random random = new Random();
                int index = random.nextInt(producerType.length);
                // Create producer thread
                producerThreads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            consumerProducer.produce(producerType[index]);
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
        } catch (NoArgumentsException ex) {
            throw new NoArgumentsException("Enter correct arguments - in pairs (ignore brackets and commas) (Consumer_Thread_Count, Consumer_item)\" +\n" +
                    "                \" (Producer_Thread_Count, Producer_item) (Storage Size)");
        } catch (NumberFormatException ex) {
            throw new NotANumberException("Not a number");
        }
    }
} 