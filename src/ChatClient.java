import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClient {

    public static void main(String[] args) {
        Socket socket = null;
        System.out.println("Enter userName: ");
        String userName = null;
        try {
            userName = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int portNumber = 5050;
        try {
            socket = new Socket ("localhost",portNumber);
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
