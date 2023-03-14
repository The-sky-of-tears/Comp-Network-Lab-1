package network.TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Random;

public class Client extends Thread{
    private final InetAddress ip;
    private final Integer port;

    public Client(InetAddress ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        Random random = new Random();

        try ( Socket clientSocket = new Socket(ip, port)) {
            System.out.println("The client with IP " + clientSocket.getInetAddress() +
                    " and port " + clientSocket.getPort() + " has started.");

            try (BufferedWriter out = new BufferedWriter (new OutputStreamWriter(clientSocket.getOutputStream()));
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                for (int i = 0; i < 8; i++) {
                    Integer data = random.nextInt(100);
                    System.out.println("Client: sent " + data + " at " + LocalTime.now());
                    out.write(data + "\n");
                    out.flush();
                }

                for (int i = 0; i < 8; i++) {
                    Double data = random.nextDouble();
                    System.out.println("Client: sent " + data + " at " + LocalTime.now());
                    out.write(Double.toString(data) + "\n");
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
