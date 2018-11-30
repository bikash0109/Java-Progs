/*
 * Program Name: qClientUdp.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 *
 * This program send a token to connect to the server, after which the server sends back a quote to be printed by the
 * client.
 */

import java.io.IOException;
import java.net.*;

public class qClientUdp {
    public static void main(String args[]) {
        if (args.length < 2) {
            System.out.println("Arguments Missing for client: <hostname> <port>");
            return;
        }
        try {
            int port = Integer.parseInt(args[1]);
            String host = args[0];
            InetAddress address = InetAddress.getByName(host);
            DatagramSocket socket = new DatagramSocket();

            DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
            System.out.println("Sending request to server..." + request.getSocketAddress());
            socket.send(request);

            byte[] buffer = new byte[512];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            System.out.println("Connected to : " + response.getSocketAddress());

            System.out.println("quote of the day: - " + new String(buffer, 0, response.getLength()));
        } catch (SocketTimeoutException ex) {
            System.err.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        }catch (UnknownHostException ex){
            System.err.println("Unknown host: " + ex.getMessage());
        }catch (IOException ex) {
            System.err.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }catch (NumberFormatException ex){
            System.err.println("Not a number: " + ex.getMessage());
        }
    }
}