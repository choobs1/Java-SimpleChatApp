
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private static ServerSocket serverSocket;
    protected static ArrayList<ClientThread> clients;
    //14001 is our default server socket
    private static int portNumber = 14001;

    public static void main(String[] args) {

        //This is used for checking the command line arguments.
        if (args.length < 2){
            System.out.println("No arguments provided, default value is used");
            System.out.println("Server started on port: " + portNumber);
            makeConnection();
        } else if (args[0].equals("-csp")) {
            portNumber = Integer.parseInt(args[1]);
            System.out.println("Server started on port: " + portNumber);
            makeConnection();
        } else {
            System.err.println("Wrong command line arguments, default port is used");
            System.out.println("Server started on port: " + portNumber);
            makeConnection();
        }

    }
    /*
    The server handles the client with this method. It contains the thread for each of the clients that are connected and then it
    adds each of them into an arraylist.
     */
    public static void acceptClients() {
        clients = new ArrayList<ClientThread>();
        while (true){
            try{
                Socket socket = serverSocket.accept();
                System.out.println("New Client has joined.");
                ClientThread client = new ClientThread(socket);
                Thread UserThread = new Thread (client);
                UserThread.start();
                clients.add(client);
            } catch (IOException e){
                System.out.println("Failed to accept port.");
            }
        }
    }
    /*
      The method sets up the Server socket to be used for connection.
     */
    public static void makeConnection(){
        try {
            serverSocket = new ServerSocket(portNumber);
            acceptClients();
        } catch (IOException e){
            System.err.println("Could not get any connections on port: " + portNumber);
            System.exit(1);
        }
    }
}
