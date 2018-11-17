import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class qClientUdp {
    public static void main(String args[]) {
        if (args.length < 1) {
            System.out.println("Arguments Missing for client: <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        try {
            InetAddress address = InetAddress.getLocalHost();
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
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}