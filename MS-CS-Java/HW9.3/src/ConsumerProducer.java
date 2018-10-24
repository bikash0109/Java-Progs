
public class ConsumerProducer {

    static MyStorage<Integer> list = new MyStorage<>();
    int capacity = 10;

    // Function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {

                while (list.size() == capacity) {
                    wait();
                }

                System.out.println("Producer " + Thread.currentThread().getName() + " produced : " + value);

                // to insert the jobs in the list
                list.add(value++);

                notifyAll();
            }
        }
    }

    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {

                while (list.size() == 0) {
                    wait();
                }

                int val = list.removeFirst();

                System.out.println("Consumer " + Thread.currentThread().getName() + " consumed : " + val);

                // Wake up producer thread
                notifyAll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final ConsumerProducer consumerProducer = new ConsumerProducer();

        Thread[] producerThreads = new Thread[10];
        Thread[] consumerThreads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            // Create producer thread
            producerThreads[i] = new Thread(() -> {
                try {
                    consumerProducer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            producerThreads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            // Create consumer thread
            consumerThreads[i] = new Thread(() -> {
                try {
                    consumerProducer.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            consumerThreads[i].start();
        }

        for (int i = 0; i < 10; i++) {
            producerThreads[i].join();
            consumerThreads[i].join();
        }

    }
} 