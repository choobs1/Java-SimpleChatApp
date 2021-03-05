
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static ServerSocket serverSocket;
    protected static ArrayList<ClientThread> clients;

    public static void main(String[] args) {
        int portNumber = 5050;
        //created static server socker for now
        try {
            serverSocket = new ServerSocket(portNumber);
            acceptClients();
        } catch (IOException e){
            System.err.println("Could not get any connections on port: " + portNumber);
            System.exit(1);
        }


    }

    public static void acceptClients() {
        clients = new ArrayList<ClientThread>();
        while (true){
            try{
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket);
                Thread UserThread = new Thread (client);
                UserThread.start();
                clients.add(client);
            } catch (IOException e){
                System.out.println("Failed to accept port.");
            }
        }
    }
}
