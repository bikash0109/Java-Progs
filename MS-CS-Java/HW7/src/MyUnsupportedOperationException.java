// A custom exception class to throw is any operation is not supported, extending RuntimeException

public class MyUnsupportedOperationException extends RuntimeException {
    public MyUnsupportedOperationException() {
        System.err.println("This operation is not supported.");
    }
}
