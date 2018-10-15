// A custom exception class to check the number of arguments provided, extending RuntimeException

public class MyIllegalArgumentException extends RuntimeException {
    public MyIllegalArgumentException() {
        System.err.println("Arguments passed are not right!");
    }
}
