public class NoArgumentsException extends Exception {
    NoArgumentsException(String message){
        super(message);
        System.out.println("Enter correct arguments - in pairs (ignore brackets and commas) (Consumer_Thread_Count, Consumer_item)" +
                " (Producer_Thread_Count, Producer_item) (Storage Size)");
    }
}
