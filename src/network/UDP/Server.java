package network.UDP;

import java.io.*;
import java.net.*;
import java.time.LocalTime;

public class Server extends Thread {
    private final Integer port;

    public Server(Integer port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (DatagramSocket datagramSocket = new DatagramSocket(port);
            FileWriter fileWriter = new FileWriter("DataUDP.txt")) {
            System.out.println("The Server with port: " + datagramSocket.getLocalPort() + " has started.");

            DatagramPacket packet;
            DataInputStream inputStream;
            byte[] buf = new byte[512];

            int counter = 0;
            while (counter++ < 8) {
                packet = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(packet);

                inputStream = new DataInputStream(new ByteArrayInputStream(packet.getData()));

                fileWriter.write("Server: received " + inputStream.readInt() + " at " + LocalTime.now() + "\n");
            }

            counter = 0;
            while (counter++ < 8) {
                packet = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(packet);

                inputStream = new DataInputStream(new ByteArrayInputStream(packet.getData()));

                fileWriter.write("Server: received " + inputStream.readDouble() + " at " + LocalTime.now() + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
