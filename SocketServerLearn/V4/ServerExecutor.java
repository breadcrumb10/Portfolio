import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.util.*;


//The Runnable Thread.
//For some reason I can't add runnable to ServerExecutor Class.
//Will need to ask Ilja
class ServerThread implements Runnable, AutoCloseable {
    public int portnum = 4999;
    public static boolean running = true;

    //This thread creates a server
    @Override
    public void run() {
        System.out.println("Created Runnable Thread");

        //Two exceptions needed to allow run.
        //IO catch for server and a General catch for close()
        try (StringServer wtf = new StringServer()) {
            wtf.MakeandServe(portnum);
        } catch (IOException e) {
            System.out.println("Server Error: IOException");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Server Error: Could not close 'wtf'");
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {
        // TODO Auto-generated method stub

    }

}

public class ServerExecutor {

    //Main String
    public static void main(String[] args) {

        //Variables for prompt loop
        boolean prompt = true;
        Scanner Scantastic = new Scanner(System.in);

        //Runnable Thread Implementation
        ServerThread g2 = new ServerThread();
        Thread serverthread = new Thread(g2);
        serverthread.start();
        while (ServerThread.running) {
            prompt = true;
            while (prompt == true) {
                System.out.println("\t\t-What would you like to do?-");
                System.out.println();
                System.out.println("\t{1: Close the Server\t2: Continue running Server}");

                int choice = Scantastic.nextInt();
                switch (choice) {

                    //Close the server
                    case 1:
                        ServerThread.running = false;
                        serverthread.interrupt();
                        prompt = false;
                        Scantastic.close();
                        break;

                    //Continue running current server
                    case 2:
                        prompt = false;
                        break;

                    //Default - Run the Prompt again
                    default:
                        break;
                }//switch
            }//prompt loop
        }//While Running
    }//main


}
