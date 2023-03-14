package network.UDP;

import java.net.InetAddress;
public class RunnerUDP {
    public static void main(String[] args) {
        Server server = null;
        Client client = null;

        try {
            server = new Server(1488);
            server.start();

            client = new Client(InetAddress.getByName("localhost"), 1488);
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
