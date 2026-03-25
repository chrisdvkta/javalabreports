import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class Lab4Q02UdpClientSender {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "192.168.10.20";
        int port = args.length > 1 ? parsePort(args[1], 9999) : 9999;
        String message = args.length > 2 ? args[2] : "Tribhuvan University";

        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] data = message.getBytes(StandardCharsets.UTF_8);
            InetAddress address = InetAddress.getByName(host);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);

            System.out.println("UDP message sent to " + host + ":" + port);
            System.out.println("Message: " + message);
        } catch (Exception e) {
            System.out.println("UDP send failed: " + e.getMessage());
            System.out.println("Tip: Ensure the target host/port is reachable.");
        }

        System.out.println("done by Krish Devkota");
    }

    private static int parsePort(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }
}

