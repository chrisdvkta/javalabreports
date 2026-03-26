import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Lab4Q01TcpClientSender {
    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "192.168.10.20";
        int port = args.length > 1 ? parsePort(args[1], 9999) : 9999;
        String message = args.length > 2 ? args[2] : "Tribhuvan University";

        System.out.println("Connecting to " + host + ":" + port + " ...");

        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, port), 5000);

            try (PrintWriter out = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
                out.println(message);
                System.out.println("TCP message sent to " + host + ":" + port);
                System.out.println("Message: " + message);
            }
        } catch (Exception e) {
            // System.out.println("TCP send failed: " + e.getMessage());
            System.out.println("Tip: Ensure a TCP server is listening on " + host + ":" + port);
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
