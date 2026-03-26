import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Lab6Q01RmiSum {
    private static final int DEFAULT_PORT = 1099;
    private static final String BIND_NAME = "SumService";

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

        SumService service = new SumServiceImpl();
        String url = "rmi://localhost:" + port + "/" + BIND_NAME;
        Naming.rebind(url, service);
        System.out.println("SumService bound at " + url);
        System.out.println("Server ready. Press Ctrl+C to stop.");

        // Keep process alive
        Thread.sleep(Long.MAX_VALUE);
    }

    private static void runClient(int port, int a, int b) throws Exception {
        String url = "rmi://localhost:" + port + "/" + BIND_NAME;
        SumService service = (SumService) Naming.lookup(url);
        int result = service.sum(a, b);

        System.out.println("Connected to: " + url);
        System.out.println("Sum(" + a + ", " + b + ") = " + result);
    }

    private static void printUsage() {
        System.out.println("Usage:");
        System.out.println("  java Lab6Q01RmiSum server [port]");
        System.out.println("  java Lab6Q01RmiSum client [port] <a> <b>");
        System.out.println("Example:");
        System.out.println("  java Lab6Q01RmiSum server 1099");
        System.out.println("  java Lab6Q01RmiSum client 1099 5 7");
    }

    private static int parseInt(String s, int fallback) {
        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return fallback;
        }
    }

    public interface SumService extends Remote {
        int sum(int a, int b) throws RemoteException;
    }

    private static class SumServiceImpl extends UnicastRemoteObject implements SumService {
        protected SumServiceImpl() throws RemoteException {
            super();
        }

        @Override
        public int sum(int a, int b) {
            return a + b;
        }
    }
}

