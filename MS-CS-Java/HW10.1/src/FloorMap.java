// Class to handle the floor map

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;

public final class FloorMap implements Runnable {

    private static Elevator currentElevator;
    private static final FloorMap instance = new FloorMap();

    private FloorMap() {
        if (instance != null) {
            throw new IllegalStateException("Already instantiated");
        }
        Elevator elevator = new Elevator("1");
        Thread t = new Thread(elevator);
        t.start();
        currentElevator = elevator;
    }

    public static FloorMap getInstance() {
        return instance;
    }

    public synchronized Elevator processRequest(ElevatorRequest elevatorRequest) {
        Elevator elevator = locateElevatorMap(elevatorRequest.getRequestFloor(), elevatorRequest.getTargetFloor());
        notifyAll();
        return elevator;
    }

    // Decides the floormap and saves it to a hashmap
    private static Elevator locateElevatorMap(int requestedFloor, int targetFloor) {
        Elevator elevator = currentElevator;
        ElevatorRequest newRequest = new ElevatorRequest(elevator.getCurrentFloor(), requestedFloor);
        ElevatorState elevatorDirection = getElevatorDirection(newRequest);
        ElevatorRequest newRequest2 = new ElevatorRequest(requestedFloor, targetFloor);
        ElevatorState elevatorDirection2 = getElevatorDirection(newRequest2);
        NavigableSet<Integer> floorSet = elevator.floorStopWithDirection.get(elevatorDirection);
        if (floorSet == null) {
            floorSet = new ConcurrentSkipListSet<>();
        }
        floorSet.add(elevator.getCurrentFloor());
        floorSet.add(requestedFloor);
        elevator.floorStopWithDirection.put(elevatorDirection, floorSet);
        NavigableSet<Integer> floorSet2 = elevator.floorStopWithDirection.get(elevatorDirection2);
        if (floorSet2 == null) {
            floorSet2 = new ConcurrentSkipListSet<>();
        }
        floorSet2.add(requestedFloor);
        floorSet2.add(targetFloor);
        elevator.floorStopWithDirection.put(elevatorDirection2, floorSet2);
        elevator.initialFloor = requestedFloor;
        elevator.setCurrentFloor(targetFloor);
        return elevator;
    }

    private static ElevatorState getElevatorDirection(ElevatorRequest elevatorRequest) {
        ElevatorState elevatorState;
        if (elevatorRequest.getTargetFloor() - elevatorRequest.getRequestFloor() > 0) {
            elevatorState = ElevatorState.UP;
        } else {
            elevatorState = ElevatorState.DOWN;
        }
        return elevatorState;
    }


    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            }
        }
    }
}
