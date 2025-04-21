import java.io.IOException;
import java.net.*;
import java.io.*;

public class PrinterClient implements Runnable {

    private final String host;
    private final int port;
    private boolean is_running;

    PrinterClient(String host, int port){
        this.host=host;
        this.port=port;
        this.is_running=true;
    }

    public boolean isRunning(){
        return is_running;
    }

    public void stop(){
        is_running=false;
    }

    @Override
    public void run() {
        is_running= true;

        //a generic object that will be overwritten by the variable from the server.
        //Anything can replace it even another object.
        Object parcel;

        try (Socket s = new Socket(host, port)) {
            try (InputStream inputStream = s.getInputStream()) {
                try (ObjectInputStream oinputstream = new ObjectInputStream(inputStream)) {
                    parcel = oinputstream.readObject();
                    System.out.println(parcel);
                }

                //in the case of an error all catches will include a close.
                catch (Exception e) {
                    System.out.println("Client Error: Failed to assign ois or var to a value.");
                    s.close();
                }
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            is_running = false;
        }
    }

}
