//The file should only contains numbers and a ".", anything else, this exception is invoked.

public class NoNumbersException extends Exception {
    NoNumbersException(String message){
        super(message);
    }
}
