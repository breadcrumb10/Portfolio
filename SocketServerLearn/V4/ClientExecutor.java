import java.io.IOException;
import java.util.*;
import java.io.*;

class ClientThread extends Thread {
	
	public static int portnum = 4999;
	public static boolean running = true;
	
	// initiated run method for Thread
    public void run()
    {
        System.out.println("Created Client Thread");
        
        try (PrinterClient ftw = new PrinterClient()){
        	ftw.Run("localhost",portnum);
        } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
}//ClientThread Class

public class ClientExecutor implements AutoCloseable{

	public static void main(String[] args) {
		Scanner Scantato = new Scanner(System.in);
		
		//Thread Class implementation of java threads
	    ClientThread clientthread = new ClientThread();
	    clientthread.start();
	    
		while (ClientThread.running) {

			System.out.println("\t\t-What would you like to do?-");
			System.out.println();
			System.out.println("\t{1: Close the Client\t\t\t\t2: Keep Client Running}");

			int choice = Scantato.nextInt();
			switch (choice) {

				//Close the client
				case 1:
					Scantato.close();
					ClientThread.running = false;
					clientthread.interrupt();
					break;

				//Keep Client Running
				case 2:
					break;

				//repeat
				default:
					break;
			}//Switch
		}//Running

		try {
			clientthread.join();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//main

	@Override
	public void close() throws Exception {
		System.out.println("Client: Closing Resources");
	}
	
}//ClientExecutor Class
