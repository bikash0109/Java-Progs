public class NegativeNumberException extends Exception {
    NegativeNumberException(String message){
        super(message);
        System.out.println("Negative number cannot be processed");
    }
}
