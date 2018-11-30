import java.io.*;
import java.net.*;

public class qClientTcp {
    public static void main(String args[]) {
        if (args.length < 2) {
            System.out.println("Arguments Missing for client: <hostname> <port>");
            System.exit(0);
        }
        int port = 0;
        String host = null;
        try {
            port = Integer.parseInt(args[1]);
            host = args[0];
            Socket clientSocket = new Socket(host, port);
            System.out.println("Quote of the day: " +
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream())).readLine());
            clientSocket.close();
        } catch (SocketTimeoutException ex) {
            System.err.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            System.err.println("Unknown host: " + host  + " is not found. "+  ex.getMessage());
        } catch (IOException ex) {
            System.err.println("Client error: Port " + port + " is not active. " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.err.println("Not a number: " + ex.getMessage());
        }
    }
}