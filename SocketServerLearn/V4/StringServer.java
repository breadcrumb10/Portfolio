import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.*;

public class StringServer implements AutoCloseable{

	
	//Variables
	private final String send_me = "*bang*";
	
	//This does everything
	void MakeandServe(int port) throws IOException {
		
		//Create Server and Connect Client
		try (ServerSocket server = new ServerSocket(port);){
			System.out.println("\t\t\t<Server established>");
			System.out.println();
			System.out.println("\t\t-Attempting to Connect Client-");
			try (Socket s = server.accept();){
				System.out.println("Server: Client connected");
				
				//Sends the Variable to the client.
				try (OutputStream outputStream = s.getOutputStream();){
					try (ObjectOutputStream stream = new ObjectOutputStream(outputStream);){
						while (ServerThread.running) {
								stream.writeObject(send_me);
								stream.flush();		
						}//while running
					}//OOS
				}//OS
				catch(Exception e) {
					System.out.println("Server Error: Could not send variable.");
					s.close();
					server.close();
				}
				
			}//TryCatch - s
		}//TryCatch - server 
	}//Run


	//Auto-closable must remain public.
	//Without public modifier - cannot close private variables
	@Override
	public void close() throws IOException, Exception {
		System.out.println("Server: Closing resources.");
		
	}
	
}//StringServer class

