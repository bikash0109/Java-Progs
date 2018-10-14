public class MyUnsupportedOperationException extends RuntimeException {
    public MyUnsupportedOperationException() {
        System.err.println("This operation is not supported.");
    }
}
