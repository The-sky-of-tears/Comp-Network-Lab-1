package network.TCP;

import java.net.InetAddress;

public class RunnerTCP {
    public static void main(String[] args) {
        Server server = null;
        Client client = null;

        try {
            server = new Server(13666);
            server.start();

            client = new Client(InetAddress.getByName("localhost"), 13666);
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            server.join();
            client.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}