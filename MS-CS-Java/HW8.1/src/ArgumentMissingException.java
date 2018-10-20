//The programs needs command line arguments, if they are missing, this exception is thrown

public class ArgumentMissingException extends Exception{
    ArgumentMissingException(String message){
        super(message);
    }
}
