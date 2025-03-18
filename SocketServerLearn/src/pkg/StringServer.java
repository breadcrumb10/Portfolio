package pkg;

import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class StringServer implements AutoCloseable {

	
	//Static String
	public static String send_me = "*bang*";
	
	//Autoclosables - needs to live outside scope
	private Scanner Scantastic = new Scanner(System.in);
	private ServerSocket server;
	
	
	//This does everything
	void Run() throws IOException {
		try (ServerSocket server = new ServerSocket(4999);){
			System.out.println("\t\t\t<Server established>");
			System.out.println();
			System.out.println("\t\t-Attempting to Connect Client-");
			
			//The "heart of the server", will pulse with every iteration
			//Allows clients to connect multiple times
			boolean heart = true;
			while (heart == true) {
			
			try (Socket s = server.accept();){
				System.out.println("Client connected");
				//Sends the Variable to the client.
				//I could eliminate this method entirely.
				//But I prefer this to be its own method for legibility
					try {
						SendVar(s);
						}
					catch(Exception e) {
						System.out.println("Error: Could not send variable.");
						}
					
					//For Loop for Closing the Server
					//
					boolean prompt = true;
					while (prompt == true) {

					System.out.println("\t\t-Close the Server?-");
					System.out.println();
					System.out.println("\t{1: Yes\t\t\t\t2: No}");
						
					int yesno = Scantastic.nextInt();
					switch (yesno) {
					
					//Yes - Close the Server
					case 1:
						heart = false;
						prompt = false;
						break;
					//No - Keep Server Running
					case 2:
						prompt = false;
						break;
					//Default - Run the Prompt again
					default:
						break;
									}/*switch statement*/ 
					break;
					
											
					}//prompt loop		
				}//TryCatch - s
			}//Heart Loop
		}//TryCatch - server 
	}//Run

	//Send the variable to client method.
	private void SendVar(Socket s) throws IOException{
		//"shoots" the variable
		try (OutputStream outputStream = s.getOutputStream();){
			try (ObjectOutputStream stream = new ObjectOutputStream(outputStream);){
				stream.writeObject(send_me);
				stream.flush();	
				stream.close();
			}
		}
		//Cannot Auto-close stream. 
		//As stream is not a private / public variable of StringServer class.
	}

	//Auto-closable must remain public.
	//Without public modifier - cannot close private variables
	@Override
	public void close() throws Exception {
		System.out.println("Closing resources.");
		
		try {server.close();}
		catch(Exception e) {
			System.out.println("Error: Could not close 'server'");
		}
		
		try {Scantastic.close();}
		catch (Exception e) {
			System.out.println("Error: Could not close 'Scantastic'");
		}	
	}
	
}//StringServer class

