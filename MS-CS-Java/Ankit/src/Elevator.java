import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Elevator implements Runnable {

    Random r = new Random();
    int liftsource;
    int liftdesti;
    int personSource = 0;
    int personDesti = 0;
    int currentLiftpos = 0;


    void getRequest(People request) {
        synchronized (People.class) {
            this.liftsource = request.source;
            this.liftdesti = request.destination;
        }
    }

    public String decideMovement(int liftpos, int psource) {

        liftpos = currentLiftpos;
        psource = personSource;

        if (liftpos < psource) {
            return "up";
        } else {
            return "down";
        }
    }


    @Override
    public synchronized void run() {
        try {
            Thread.sleep(1000);
            if (liftsource > liftdesti) {
                System.out.println("Lift moving down from " + liftsource + " to " + liftdesti);
                for (int index = liftsource; index >= liftdesti; index--) {
                    System.out.println("At floor: " + index);
                }
            } else {
                System.out.println("Lift moving up from " + liftsource + " to " + liftdesti);
                for (int index = liftsource; index <= liftdesti; index++) {
                    System.out.println("At floor: " + index);
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}