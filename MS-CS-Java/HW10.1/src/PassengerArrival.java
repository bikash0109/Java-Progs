// Passenger Class, which has the new request.

public class PassengerArrival implements Runnable {
    private int requestFloor;
    private int destinationFloor;

    public PassengerArrival(int requestFloor, int destinationFloor) {
        this.requestFloor = requestFloor;
        this.destinationFloor = destinationFloor;
    }

    //Getter methods

    public int getDestinationFloor() {
        return this.destinationFloor;
    }

    public int getrequestFloor() {
        return this.requestFloor;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    Thread.sleep(100);
                    ElevatorRequest elevatorRequest = new ElevatorRequest(getrequestFloor(), getDestinationFloor());
                    elevatorRequest.submitRequest();
                } catch (InterruptedException e) {
                    System.out.println(e.getStackTrace());
                }
            }
        }
    }
}
