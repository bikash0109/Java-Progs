// A custom exception class to check for class cast, extending RuntimeException

public class MyClassCastException extends RuntimeException {
    public MyClassCastException() {
        System.err.println("Wrong class cast.");
    }
}
