import java.rmi.*;

public class HelloClientComponent 
{
    private static final String host = "localhost";

    public static void main(String[] args) 
    {
        try 
        {
            IHello greeting_message = (IHello) Naming.lookup("//:"
                    + 55 + "/remoteServer");

            System.out.println("Message received: " +
                    greeting_message.getGreetingMessage());
        } 
        catch (ConnectException conEx) 
        {
            System.out.println("Unable to connect to server!");
            System.exit(1);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}
