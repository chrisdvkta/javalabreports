import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Lab6Q02RmiMax {
    private static final int DEFAULT_PORT = 1099;
    private static final String BIND_NAME = "MaxService";

    public static void main(String[] args) throws Exception {
        System.out.println("done by Krish Devkota");

        if (args.length < 1) {
            printUsage();
            return;
        }

        String mode = args[0].toLowerCase();
        int port = args.length >= 2 ? parseInt(args[1], DEFAULT_PORT) : DEFAULT_PORT;

        if ("server".equals(mode)) {
            startServer(port);
        } else if ("client".equals(mode)) {
            if (args.length < 4) {
                System.out.println("Client requires two integers: a b");
                printUsage();
                return;
            }
            int a = parseInt(args[2], 0);
            int b = parseInt(args[3], 0);
            runClient(port, a, b);
        } else {
            printUsage();
        }
    }

    private static void startServer(int port) throws Exception {
        try {
            LocateRegistry.createRegistry(port);
            System.out.println("RMI registry started on port " + port);
        } catch (RemoteException e) {
            System.out.println("RMI registry may already be running on port " + port);
        }

        MaxService service = new MaxServiceImpl();
        String url = "rmi://localhost:" + port + "/" + BIND_NAME;
        Naming.rebind(url, service);
        System.out.println("MaxService bound at " + url);
        System.out.println("Server ready. Press Ctrl+C to stop.");

        Thread.sleep(Long.MAX_VALUE);
    }

    private static void runClient(int port, int a, int b) throws Exception {
        String url = "rmi://localhost:" + port + "/" + BIND_NAME;
        MaxService service = (MaxService) Naming.lookup(url);
        int result = service.max(a, b);

        System.out.println("Connected to: " + url);
        System.out.println("Max(" + a + ", " + b + ") = " + result);
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java Lab6Q02RmiMax server [port]");
        System.out.println("  java Lab6Q02RmiMax client [port] <a> <b>");
        System.out.println("Example:");
        System.out.println("  java Lab6Q02RmiMax server 1099");
        System.out.println("  java Lab6Q02RmiMax client 1099 12 9");
    }

    private static int parseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    public interface MaxService extends Remote {
        int max(int a, int b) throws RemoteException;
    }

    private static class MaxServiceImpl extends UnicastRemoteObject implements MaxService {
        protected MaxServiceImpl() throws RemoteException {
            super();
        }

        @Override
        public int max(int a, int b) {
            return Math.max(a, b);
        }
    }
}

