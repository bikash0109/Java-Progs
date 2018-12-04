/*
 * Program Name: qServerTcp.java
 *
 * Version :  1.0
 *
 * @author: Bikash Roy (br8376)
 * @author: Tanay Bhardwaj
 *
 * This program waits for a end connection form any client, on encountering a connection, a thread from pool is executed
 * to seed the Quote to client.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.BindException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class qServerTcp {
    private static ArrayList<String> listQuotes = new ArrayList<>();
    private static Random rand = new Random();
    private static ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Arguments Missing for server: <file> <port>");
            System.exit(0);
        }
        int port = Integer.parseInt(args[1]);
        String quoteFile = args[0];
        ServerSocket server = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(quoteFile));
            String line;
            while ((line = in.readLine()) != null) {
                listQuotes.add(line);
            }
            server = new ServerSocket(port);
            System.out.println("Server listening on port 17");
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + quoteFile + " file not found");
        } catch (BindException e) {
            System.out.println("Couldn't bind to port " + port + ".");
        } catch (IOException e) {
            System.out.println("Error: reading " + quoteFile);
        }
        while (true) {
            try {
                final Socket connection = server.accept();
                Runnable task = () -> {
                    try {
                        System.out.println(Thread.currentThread().getName()  + " connected to: " + connection.getInetAddress().getHostName());
                        PrintStream out = new PrintStream(connection.getOutputStream());
                        out.println(listQuotes.get(rand.nextInt(listQuotes.size())));
                    } catch (IOException e) {
                        System.out.println(e);
                    } finally {
                        try {
                            connection.close();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                };
                pool.execute(task);
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}