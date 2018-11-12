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
 * logic : use of semaphore block on the class, to allow only one part to access the list simultaneously.
 *
 * */

import java.util.Arrays;

public class ConsumerProducer {
    int capacity;
    int value = 0;
    MyStorage<Integer> listInt = new MyStorage<>();
    MyStorage<String> listString = new MyStorage<>();
    MyStorage<Boolean> listBool = new MyStorage<>();
    static Semaphore comsumerSemphore = new Semaphore(1);
    static Semaphore producerSemaphore = new Semaphore(0);
    static Semaphore mutex = new Semaphore(1);

    ConsumerProducer(int capacity) {
        this.capacity = capacity;
    }

    // Function called by producer thread
    public void produce(String type) throws InterruptedException {
        while (true) {
            mutex.acquire();
            while (listInt.size() + listBool.size() + listString.size() == capacity) {
                producerSemaphore.acquire();
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
            mutex.release();
            // wake up consumer thread
            comsumerSemphore.release();


            Thread.sleep(1000);
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            while (listInt.size() + listString.size() + listBool.size() == 0) {
                comsumerSemphore.acquire();
            }
            mutex.acquire();
            if(listInt.size() > 3 & listString.size() > 5 & listBool.size() > 2){
                for (int i = 0; i < 3; i++) {
                    int val = listInt.removeFirst();
                    System.out.println(Thread.currentThread().getName().split("-")[1] + " consumer : " + val);
                }
                for (int i = 0; i < 5; i++) {
                    String str = listString.removeFirst();
                    System.out.println(Thread.currentThread().getName().split("-")[1] + " consumer : " + str);
                }
                for (int i = 0; i < 2; i++) {
                    Boolean bool = listBool.removeFirst();
                    System.out.println(Thread.currentThread().getName().split("-")[1] + " consumer : " + bool);
                }
            }
            mutex.release();
            // Wake up producer thread
            producerSemaphore.release();

            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws NoArgumentsException, NegativeNumberException, NotANumberException {
        try {
            int index = 0;
            int numOfConsumerThread, numOfProducerThread, capacity;
            if (args.length == 3) {
                if (Arrays.asList(args).stream().anyMatch(x -> x.contains("-"))) {
                    throw new NegativeNumberException("");
                } else {
                    numOfConsumerThread = Integer.parseInt(args[0]);
                    numOfProducerThread = Integer.parseInt(args[1]);
                    if(numOfProducerThread < 3) {
                        System.out.println("Minimum 3 producers are required.");
                        return;
                    }
                    capacity = Integer.parseInt(args[2]);
                }
            } else {
                throw new NoArgumentsException("");
            }

            final ConsumerProducer consumerProducer = new ConsumerProducer(capacity);

            Thread[] producerThreads = new Thread[numOfProducerThread];
            Thread[] consumerThreads = new Thread[numOfConsumerThread];

            final String[] producerType = {"int", "string", "bool"};

            for (int i = 0; i < producerThreads.length; i++) {
                if (index == 3)
                    index = 0;
                String str = producerType[index];
                // Create producer thread
                producerThreads[i] = new Thread(() -> {
                    try {
                        consumerProducer.produce(str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                index++;
            }

            for (int j = 0; j < consumerThreads.length; j++) {
                // Create consumer thread
                consumerThreads[j] = new Thread(() -> {
                    try {
                        consumerProducer.consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }

            for (int i = 0; i < producerThreads.length; i++) {
                producerThreads[i].start();
            }

            for (int j = 0; j < consumerThreads.length; j++) {
                consumerThreads[j].start();
            }

        } catch (NegativeNumberException ex) {
            throw new NegativeNumberException("Negative number cannot be processed");
        } catch (NoArgumentsException ex) {
            throw new NoArgumentsException("Enter correct arguments - (ignore brackets and commas) (Consumer_Thread_Count)\" +\n" +
                    "                \" (Producer_Thread_Count) (Storage Size)");
        } catch (NumberFormatException ex) {
            throw new NotANumberException("Not a number");
        }
    }
} 