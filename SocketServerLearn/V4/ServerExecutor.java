import java.io.IOException;
import java.util.Scanner;
import java.io.*;
import java.util.*;


public class ServerExecutor {

    //Main String
    public static void main(String[] args) {

        //Variables for prompt loop
        boolean prompt = true;
        Scanner Scantastic = new Scanner(System.in);

        StringServer wtf = new StringServer(4999,"*bang*");
        Thread server_thread = new Thread(wtf);
        server_thread.start();

        while (wtf.isRunning()) {
            prompt = true;
            while (prompt == true) {
                System.out.println("\t\t-What would you like to do?-");
                System.out.println();
                System.out.println("\t{1: Close the Server\t2: Continue running Server}");

                int choice = Scantastic.nextInt();
                switch (choice) {

                    //Close the server
                    case 1:
                        wtf.stop();
                        server_thread.interrupt();
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
                }
            }
        }
    }


}
