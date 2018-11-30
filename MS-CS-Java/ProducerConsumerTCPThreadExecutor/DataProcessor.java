import java.util.concurrent.BlockingQueue;
public class DataProcessor implements Runnable {
    private final BlockingQueue<byte[]> messageQueue;
    public DataProcessor(BlockingQueue<byte[]> messageQueue) {
        this.messageQueue = messageQueue;
    }
    @Override
    public void run() {
        int counter = 0;
        while (true){
            try {
                /**
                 * Try and take a message from the queue. Will block if the
                 * message queue is empty, until an element becomes available.
                 */
                byte[] rawData = this.messageQueue.take();
                /**
                 * Increase message counter after processing
                 */
                counter++;
                System.out.println("Consumer consumed " + counter );
                /**
                 * Simulate a 3 ms delay
                 */
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}