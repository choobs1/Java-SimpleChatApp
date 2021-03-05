import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;

public class ServerThread implements Runnable{
    private Socket socket;
    private String userName;
    private BufferedReader serverReply;
    private BufferedReader userInput;
    private PrintWriter reply;
    public ServerThread(Socket socket, String userName){
        this.socket = socket;
        this.userName = userName;
    }

    @Override
    public void run() {
        try {
            serverReply = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            userInput = new BufferedReader(new InputStreamReader(System.in));

            reply = new PrintWriter(socket.getOutputStream(),true);

            while(!socket.isClosed()){
                if(serverReply.ready()){
                    String input = serverReply.readLine();
                    if(!input.equals(null)){
                        System.out.println(input);
                    }
                }

                if(userInput.ready()){
                    String userIn = userInput.readLine();
                    reply.println(userName + " > " + userIn);
                    if(userIn.equals("EXIT")){
                        System.out.println("The chat client shall now shut down.");
                        System.exit(1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
