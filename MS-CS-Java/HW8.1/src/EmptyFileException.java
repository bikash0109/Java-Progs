//If the file is empty, this exception is invoked.

public class EmptyFileException extends Exception {
    EmptyFileException(String message){
        super(message);
    }
}
