import java.io.IOException;
import java.net.*;
import java.io.*;

public class StringServer implements Runnable{

    private final String send_me;
    private final int port;
    private boolean is_running;

    StringServer(int port, String send_me) {
        this.port=port;
        this.send_me=send_me;
        this.is_running = true;
    }

    public boolean isRunning (){
        return is_running;
    }

    public void stop(){
        is_running=false;
    }

    //This does everything
    @Override
    public void run() {

        is_running = true;

        //Create Server and Connect Client
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("\t\t\t<Server established>");
            System.out.println();
            System.out.println("\t\t-Attempting to Connect Client-");

            while (is_running){
                try (Socket s = server.accept()) {
                    System.out.println("Server: Client connected");

                    //Sends the Variable to the client.
                    try (OutputStream outputStream = s.getOutputStream()) {
                        try (ObjectOutputStream stream = new ObjectOutputStream(outputStream)) {
                            stream.writeObject(send_me);
                            stream.flush();
                        }
                    }
                    catch (Exception e) {
                        System.out.println("Server Error: Could not send variable.");
                        s.close();
                        server.close();
                    }
            }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            is_running = false;
        }


    }

}

