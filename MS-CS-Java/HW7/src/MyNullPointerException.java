public class MyNullPointerException extends RuntimeException {
    public MyNullPointerException() {
        System.err.println("Some reference is not right ! Pointing to null.");
    }
}
