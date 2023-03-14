package network.TCP;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.time.LocalTime;

public class Server extends Thread{
    private final Integer port;

    public Server(Integer port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("The Server with port: " + serverSocket.getLocalPort() + " has started.");

            try (Socket clientSocket = serverSocket.accept()) {
                try (BufferedWriter out = new BufferedWriter (new OutputStreamWriter(clientSocket.getOutputStream()));
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     FileWriter fileWriter = new FileWriter("DataTCP.txt")){

                    int counter = 0;
                    while (counter++ < 8) {
                        fileWriter.write("Server: received " + in.readLine() + " at " + LocalTime.now() + "\n");
                    }

                   counter = 0;
                    while (counter++ < 8) {
                        fileWriter.write("Server: received " + in.readLine() + " at " + LocalTime.now() + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
