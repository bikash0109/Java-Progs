// Storage interface, which holds the methods to be invoked remotely

public interface StorageInterface extends java.rmi.Remote
{
    void produce (int item) throws java.rmi.RemoteException, InterruptedException;

    int consume() throws java.rmi.RemoteException, InterruptedException;
}