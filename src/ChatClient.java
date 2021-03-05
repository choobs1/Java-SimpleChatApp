import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {


        //default values per client
        int portNumber = 14001;
        String ip = "localhost";
        Socket socket = null;
        System.out.println("Enter userName: ");
        String userName = null;

        //checking command line args.
        if (args.length < 2){
            System.out.println("No arguments provided, default value is used");
            System.out.println("Server started on port: " + portNumber + " and ip: " + ip);
        } else if (args[2].equals("-ccp") && args[0].equals("-cca")) {
            portNumber = Integer.parseInt(args[3]);
            ip = args[1];
            System.out.println("Server started on port: " + portNumber + " and ip: " + ip);
        } else if (args[0].equals("-ccp")){
            portNumber = Integer.parseInt(args[1]);
            System.out.println("Server started on port: " + portNumber + " and ip: " + ip);
        } else if (args[0].equals("-cca")){
            ip = args[1];
            System.out.println("Server started on port: " + portNumber + " and ip: " + ip);
        } else {
            System.err.println("Wrong command line arguments, default port and ip is used");
            System.out.println("Server started on port: " + portNumber + " and ip: " + ip);
        }

        //user name input
        try {
            userName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket = new Socket (ip,portNumber);
            Thread.sleep(1000);
            Thread server = new Thread(new ServerThread(socket,userName));
            server.start();

        } catch (IOException e){
            System.err.println("Could not connect");
            System.out.println("Closing Application, please restart in a few seconds.");
            System.exit(1);
        } catch (InterruptedException e){
            System.err.println("Thread run error");
            e.printStackTrace();
        }
    }
}
