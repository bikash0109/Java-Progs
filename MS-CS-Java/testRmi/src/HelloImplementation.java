import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.server.*;

public class HelloImplementation extends UnicastRemoteObject
        implements IHello {

    Registry registry;
    public HelloImplementation() throws RemoteException {
        //There is no action need in this moment.
    }
    
    @Override
    public String getGreetingMessage() throws RemoteException {
        return ("Hello there, lol.");
    }
}
