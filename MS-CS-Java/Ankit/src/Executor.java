import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {


    public static void main(String[] args) {


        ExecutorService liftExecutor = Executors.newSingleThreadExecutor();
        Random r = new Random();
        ExecutorService peopleExecutor = Executors.newFixedThreadPool(2);
        int start, end;
        People p = null;
        for (int index = 0; index < 10; index++) {
            start = r.nextInt(10) + 1;
            end = r.nextInt(10) + 1;
            if (start != end) {
                Elevator ele = new Elevator();
                peopleExecutor.execute(p = new People(start, end));
                ele.getRequest(p);
                liftExecutor.execute(ele);
            }
        }

    }
}
