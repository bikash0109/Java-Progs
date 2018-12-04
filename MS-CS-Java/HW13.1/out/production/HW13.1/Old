import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.BindException;
import java.util.ArrayList;
import java.util.Random;

public class qServerTcp {
    private static ArrayList<String> listQuotes = new ArrayList<>();
    private static Random rand = new Random();

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Arguments Missing for server: <file> <port>");
            System.exit(0);
        }
        int port = Integer.parseInt(args[1]);
        String quoteFile = args[0];
        try {
            BufferedReader in = new BufferedReader(new FileReader(quoteFile));
            String line;
            while ((line = in.readLine()) != null) {
                listQuotes.add(line);
            }
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
            while (true) {
                try {
                    Socket connection = server.accept();
                    System.out.println("Connected to: " + connection.getInetAddress().getHostName());
                    PrintStream out = new PrintStream(connection.getOutputStream());
                    out.println(listQuotes.get(rand.nextInt(listQuotes.size())));
                    connection.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + quoteFile + " file not found");
        } catch (BindException e) {
            System.out.println("Couldn't bind to port " + port + ".");
        } catch (IOException e) {
            System.out.println("Error: reading " + quoteFile);
        }
    }
}
