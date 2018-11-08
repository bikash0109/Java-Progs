/*
 * Program Name: ElevatorSimulator.java
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


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorSimulator {

    private static FloorMap elevatorController;

    public static void main(String[] args) {
        elevatorController = FloorMap.getInstance();
        ExecutorService executorElevator = Executors.newSingleThreadExecutor();
        executorElevator.execute(elevatorController);
        ExecutorService executorPeople = null;
        for (int i = 0; i < 5; i++) {
            executorPeople = Executors.newSingleThreadExecutor(new MyThreadFactory());
            executorPeople.execute(new Thread(new PassengerArrival(i, i+2)));
        }
        executorPeople.shutdown();
        executorElevator.shutdown();
    }
}
