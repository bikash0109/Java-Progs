//Exception - To check if the arguments provided are not numbers

public class NotANumberException extends Exception {
    NotANumberException(String message){
        super(message);
    }
}