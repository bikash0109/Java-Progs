/*
 * Program Name: qClientUdp.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * A producer client, it generates a data and send as a string to ProducerConsumerServer Monitor class
 */


import java.io.*;
import java.net.*;

public class Producer {
    public static void main(String args[]) throws IOException {
        if (args.length < 2) {
            System.out.println("Arguments Missing for Producer client: <hostname> <port>");
            return;
        }
        try {
            int port = Integer.parseInt(args[1]);
            String host = args[0];
            Socket s = new Socket(host, port);

            //Input Output Streams
            PrintStream out = new PrintStream(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            int counter = 0;
            System.out.println("Producer client started ... \n");
            while (true) {
                String item = Integer.toString(counter++);
                out.println(item);
                in.readLine();
                System.out.println("Producer produced - " + item);
            }
        } catch (SocketException ex) {
            System.err.println("Host not available: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            System.err.println("Not a number: " + ex.getMessage());
        } catch (UnknownHostException ex) {
            System.err.println("Unknown host: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Exception: " + ex.getMessage());
        }
    }

}
