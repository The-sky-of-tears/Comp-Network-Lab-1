package network.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.time.LocalTime;
import java.util.Random;

public class Client extends Thread {
    private final InetAddress ip;
    private final Integer port;

    public Client(InetAddress ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    @Override
    public void run() {
        Random random = new Random();

        try (DatagramSocket socket = new DatagramSocket()){
            System.out.println("The client has started");

            DatagramPacket packet;
            for (int i = 0; i < 8; i++) {
                int data = random.nextInt(100);
                byte[] buf = ByteBuffer.allocate(Integer.BYTES).putInt(data).array();
                packet = new DatagramPacket(buf, buf.length, ip, port);

                System.out.println("Client: sent " + data + " at " + LocalTime.now());
                socket.send(packet);
            }

            for (int i = 0; i < 8; i++) {
                double data = random.nextDouble();
                byte[] buf = ByteBuffer.allocate(Double.BYTES).putDouble(data).array();
                packet = new DatagramPacket(buf, buf.length, ip, port);

                System.out.println("Client: sent " + data + " at " + LocalTime.now());
                socket.send(packet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
