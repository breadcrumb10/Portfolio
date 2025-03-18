package pkg;

import java.io.IOException;
import java.net.*;
import java.io.*;

public class PrinterClient implements AutoCloseable {

	public static void main(String[] args) throws IOException, ClassNotFoundException{
		//Creates localhost socket 4999
		Socket s = new Socket("localhost",4999);
		
		//The Print Method
		Print(s);
		
		//Closes the connection, like a good lad.
		//s.close();
	}

	
	private static void Print(Socket s) throws IOException, ClassNotFoundException{
				ObjectInputStream ois = null;
				Object var = null;
				
				//The real magic.
				//Gets the input stream from server.
				//Then reads the input.
				try {InputStream inputStream = s.getInputStream();
				ois = new ObjectInputStream(inputStream);
				var = ois.readObject();}
				catch (Exception e) {System.out.println("Error: Failed to assign ois or var to a value.");}
				
				
				//Read input is put into a local string and printed.
				
				System.out.println(var);
		
	}


	@Override
	public void close() throws Exception {
		System.out.println("Closing Resources");
		
	}


}
