
public class ConsumerProducer {
    static MyStorage<Integer> list = new MyStorage<>();
    int producerItem, consumerItem, capacity,originalConsumerItem;
    ConsumerProducer(int producerItem, int consumerItem, int capacity){
        this.capacity = capacity;
        this.producerItem = producerItem;
        this.consumerItem = consumerItem;
        this.originalConsumerItem = consumerItem;
    }
    // Function called by producer thread
    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (this) {
                while (list.size() == producerItem) {
                    wait();
                }
                System.out.println(Thread.currentThread().getName().split("-")[1] + " produce : " + value);
                list.add(value++);
                if(consumerItem == 0)
                    consumerItem = originalConsumerItem;
                // wake up consumer thread
                notifyAll();
                Thread.sleep(1000);
            }
        }
    }
    // Function called by consumer thread
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                while (consumerItem == 0) {
                    wait();
                }
                int val = list.removeFirst();
                consumerItem--;
                System.out.println(Thread.currentThread().getName().split("-")[1] + " consumer : " + val);
                // Wake up producer thread
                notifyAll();
                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) {

        int numOfProducerThread = Integer.parseInt(args[2]);
        int producerItems = Integer.parseInt(args[3]);
        int numOfConsumerThread = Integer.parseInt(args[0]);
        int consumerItems = Integer.parseInt(args[2]);
        int capacity = Integer.parseInt(args[4]);

        final ConsumerProducer consumerProducer = new ConsumerProducer(producerItems, consumerItems, capacity);

        Thread[] producerThreads = new Thread[numOfProducerThread];
        Thread[] consumerThreads = new Thread[numOfConsumerThread];

        for (int i = 0; i < producerThreads.length; i++) {
            // Create producer thread
            producerThreads[i] = new Thread(() -> {
                try {
                    consumerProducer.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < consumerThreads.length; i++) {
            // Create consumer thread
            consumerThreads[i] = new Thread(() -> {
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

        for (int i = 0; i < consumerThreads.length; i++) {
            consumerThreads[i].start();
        }
    }
} 