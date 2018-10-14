public class MyIllegalArgumentException extends RuntimeException {
    public MyIllegalArgumentException() {
        System.err.println("Arguments passed are not right!");
    }
}
