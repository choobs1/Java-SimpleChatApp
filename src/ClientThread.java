import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

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
            InputStreamReader clientMessageStream = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(clientMessageStream);

            writer = new PrintWriter(socket.getOutputStream(),true);

            while(!socket.isClosed()){
                String incoming = reader.readLine();
                System.out.println("Message: " + incoming);
                clientMessages.add(incoming);
                //writer.println(incoming);
                if(!incoming.equals(null)){
                    for (ClientThread client : clients){
                       client.getWriter().println(incoming);
                    }
                    //for (String msg : clientMessages){
                     //   writer.println(msg);
                    //}
                }
            }
        } catch (IOException e){
            System.err.println("Issue sending output stream to client");
            e.printStackTrace();
        }
    }

    public PrintWriter getWriter() {
        return writer;
    }
}
