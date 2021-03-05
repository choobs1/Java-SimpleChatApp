import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
Thread to handle sending and receiving data from the server.
 */
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

            //Receives messages from the server.
            serverReply = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Holds user input from command line.
            userInput = new BufferedReader(new InputStreamReader(System.in));

            //Sends data to the server.
            reply = new PrintWriter(socket.getOutputStream(),true);

            //Continuos while loop as long as socket is open.
            while(!socket.isClosed()){

                //Sends out server message to the user
                if(serverReply.ready()){
                    String input = serverReply.readLine();
                    if(!input.equals(null)){
                        System.out.println(input);
                    }
                }

                //The user input that is sent to the server.
                if(userInput.ready()){
                    String userIn = userInput.readLine();
                    reply.println(userName + " > " + userIn);
                    exitApp(userIn.toLowerCase());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void exitApp(String command){
        if(command.equals("exit")){
            System.out.println("The chat client shall now shut down.");
            System.exit(1);
        }
        else{
            return;
        }
    }
}
