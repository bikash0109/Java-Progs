import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class qServerUdp {
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<>();
    private Random random;

    public qServerUdp(int port) throws SocketException {
        socket = new DatagramSocket(port);
        random = new Random();
    }

    private void sendQuotes() throws IOException {
        while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            System.out.println("Waiting for client to connect. Listening to port...: " + this.socket.getLocalPort());
            socket.receive(request);

            System.out.println("Connected to: " + request.getAddress());

            byte[] buffer = listQuotes.get(random.nextInt(listQuotes.size())).getBytes();

            //response
            socket.send(new DatagramPacket(buffer, buffer.length, request.getAddress(), request.getPort()));
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Arguments Missing for server: <file> <port>");
            return;
        }
        String quoteFile = args[0];
        try {
            qServerUdp server = new qServerUdp(Integer.parseInt(args[1]));
            BufferedReader reader = new BufferedReader(new FileReader(quoteFile));
            String aQuote;
            while ((aQuote = reader.readLine()) != null) {
                server.listQuotes.add(aQuote);
            }
            reader.close();
            server.sendQuotes();
        }catch (NumberFormatException ex){
            System.out.println("port should be integer type");
        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
        }
    }
}