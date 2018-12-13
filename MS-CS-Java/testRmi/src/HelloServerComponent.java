
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HelloServerComponent {

    private static final String host = "localhost";

    public static void main(String[] args) throws Exception {
        HelloImplementation temp = new HelloImplementation();

        
        temp.registry = LocateRegistry.createRegistry(55);
        temp.registry.rebind("remoteServer", temp);


        System.out.println("Binding complete...\n");
    }
}
