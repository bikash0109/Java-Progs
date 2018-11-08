// Elevator class, which implements Runnable, this class handles the movement of the elevator


import java.util.*;

public class Elevator implements Runnable {

    private String name;
    private ElevatorState elevatorState;
    private int currentFloor;
    private NavigableSet<Integer> floorStops;
    public Map<ElevatorState, NavigableSet<Integer>> floorStopWithDirection;
    public int initialFloor;

    public Elevator(String name) {
        this.name = name;
        setElevatorState(ElevatorState.STATIONARY);
        this.floorStopWithDirection = new LinkedHashMap<>();
        setCurrentFloor(0);
    }

    public ElevatorState getElevatorState() {
        return elevatorState;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setElevatorState(ElevatorState elevatorState) {
        this.elevatorState = elevatorState;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public void move() {
        synchronized (FloorMap.getInstance()) { // Synchronized over the ElevatorController singleton.
            Iterator<ElevatorState> iter = floorStopWithDirection.keySet().iterator();
            while (iter.hasNext()) {
                elevatorState = iter.next();
                floorStops = floorStopWithDirection.get(elevatorState);
                iter.remove();
                Integer currFlr;
                Integer nextFlr;
                while (!floorStops.isEmpty()) {
                    if (elevatorState.equals(ElevatorState.UP)) {
                        currFlr = floorStops.pollFirst();
                        nextFlr = floorStops.higher(currFlr);
                    } else if (elevatorState.equals(ElevatorState.DOWN)) {
                        currFlr = floorStops.pollLast();
                        nextFlr = floorStops.lower(currFlr);
                    } else {
                        return;
                    }
                    setCurrentFloor(currFlr);
                    if (nextFlr != null) {
                        generateIntermediateFloors(currFlr, nextFlr);
                    } else {
                        setElevatorState(ElevatorState.STATIONARY);
                    }
                    if (currFlr == initialFloor && getElevatorState() != ElevatorState.STATIONARY) {
                        System.out.println("Elevator " + this.name + " | Current floor - " + initialFloor + " | next move - " + ElevatorState.STATIONARY);
                        try {
                            System.out.println("Waiting for people to get down and getting in.");
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Elevator " + this.name + " | Current floor - " + getCurrentFloor() + " | next move - " + getElevatorState());
                    try {
                        if (getElevatorState() == ElevatorState.STATIONARY) {
                            System.out.println("Waiting for people to get down and getting in.");
                            Thread.sleep(2500);
                        } else {
                            Thread.sleep(10);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void generateIntermediateFloors(int initial, int target) {
        if (initial == target) {
            return;
        }
        if (Math.abs(initial - target) == 1) {
            return;
        }
        int n = 1;
        if (target - initial < 0) {
            // This means with are moving DOWN
            n = -1;
        }
        while (initial != target) {
            initial += n;
            if (!floorStops.contains(initial)) {
                floorStops.add(initial);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            move();
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex.getStackTrace());
            }
        }
    }
}
