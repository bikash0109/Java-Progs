/*
 * Program Name: qClientUdp.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * A consumer client, it reads a data, sent as a string from ProducerConsumerServer Monitor class
 */


import java.io.*;
import java.net.*;

public class Consumer {
    public static void main(String args[]){
        if (args.length < 2) {
            System.out.println("Arguments Missing for Consumer client: <hostname> <port>");
            return;
        }
        try{
            int port = Integer.parseInt(args[1]);
            String host = args[0];
            Socket s = new Socket(host, port);

            //Streams
            PrintStream out = new PrintStream(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            System.out.println("Consumer client started ... \n");
            while (true) {
                out.println("CONSUME");
                String item = in.readLine();
                System.out.println("Consumer consumed - " + item);
            }
        }catch (SocketException ex){
            System.err.println("Host not available: " + ex.getMessage());
        }catch (NumberFormatException ex){
            System.err.println("Not a number: " + ex.getMessage());
        }catch (UnknownHostException ex){
            System.err.println("Unknown host: "+ ex.getMessage());
        }catch (Exception ex){
            System.err.println("Exception: "+ ex.getMessage());
        }
    }
}