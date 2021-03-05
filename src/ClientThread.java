import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/*
The client thread here is used to handle the reading and writing of data to the Client.
 */
public class ClientThread extends ChatServer implements Runnable{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private static ArrayList<String> clientMessages = new ArrayList<>();
    public ClientThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            //Initialising input streams to be read from the client
            InputStreamReader clientMessageStream = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(clientMessageStream);

            //Initialising a PrintWriter to relay messages to clients
            writer = new PrintWriter(socket.getOutputStream(),true);

            //Our loop is open as long as the socket is not closed.
            while(!socket.isClosed()){

                String incoming = reader.readLine();
                System.out.println("Message: " + incoming);
                clientMessages.add(incoming);

                //As long as there is an incoming stream, relay the stream to all clients.
                if(incoming != null){
                    for (ClientThread client : clients){
                       client.getWriter().println(incoming);
                    }
                }

                assert incoming != null;
                //Function for checking the chat log.
                if (incoming.contains("Log")){
                    writer.println("The chat log: \n");
                    for (String msg : clientMessages){
                        writer.println(msg);
                    }
                }
                //Function for using a bot message.
                if (incoming.contains("Bot")){
                    if(incoming.contains("Sup")){
                        this.getWriter().println("Bot > hmm");
                    }
                }

            }
        } catch (SocketException se){
            System.out.println("Client has disconnected");
        } catch (IOException e){
            System.err.println("Issue sending output stream to client");
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("Client has disconnected");
        }
    }

    public PrintWriter getWriter() {
        return writer;
    }

}
