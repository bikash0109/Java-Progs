// A custom exception class to check for null references, extending RuntimeException

public class MyNullPointerException extends RuntimeException {
    public MyNullPointerException() {
        System.err.println("Some reference is not right ! Pointing to null.");
    }
}
