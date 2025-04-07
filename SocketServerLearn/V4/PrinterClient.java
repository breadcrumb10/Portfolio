import java.io.IOException;
import java.net.*;
import java.io.*;

public class PrinterClient implements AutoCloseable {
	
	
	void Run(String portname, int portnumber) throws IOException, ClassNotFoundException{
		
		//a generic object that will be overwritten by the sent variable.
		//Anything can replace it even another object.
		Object var = null;
		
		try (Socket s = new Socket(portname,portnumber);){
			
			
				try (InputStream inputStream = s.getInputStream();){
					try (ObjectInputStream ois = new ObjectInputStream(inputStream);) {
						var = ois.readObject();
						System.out.println(var);					
				}//OIS
				
				//in the case of an error all catches will include a close.
				catch (Exception e) {
					System.out.println("Client Error: Failed to assign ois or var to a value.");
					s.close();
					}
			}//IS
			
		}//Socket
	}//run()



	@Override
	public void close() throws Exception {
		System.out.println("Closing Resources");

	}


}//PrinterClient Class
