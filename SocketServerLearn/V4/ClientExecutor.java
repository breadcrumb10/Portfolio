import java.io.IOException;
import java.util.*;

class ClientExecutor extends Thread {


    //Main thread
    public static void main(String[] args) {
        Scanner Scantato = new Scanner(System.in);

        //Runnable Thread Class implementation of java threads
        PrinterClient ftw = new PrinterClient("localhost", 4999);
        Thread client_thread = new Thread(ftw);
        client_thread.start();

        while (ftw.isRunning()) {

            System.out.println("\t\t-What would you like to do?-");
            System.out.println();
            System.out.println("\t{1: Close the Client\t\t\t\t2: Keep Client Running}");

            int choice = Scantato.nextInt();
            switch (choice) {

                //Close the client
                case 1:
                    Scantato.close();
                    ftw.stop();
                    client_thread.interrupt();
                    break;

                //Keep Client Running
                case 2:
                    break;

                //repeat
                default:
                    break;
            }
        }

        try {
            client_thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

