package pkg;

import java.io.IOException;

public class ServerExecutor{

	//Main String
		public static void main(String[] args) {
			//This note is for my own benefit.
			//This is essentially what I've been doing in CYOA2 with creatures
			//but used for a proper purpose.
			StringServer wtf = new StringServer();
			
			//Two exceptions needed to allow run.
			//IO catch for server and a General catch for close()
			try {
				wtf.Run();
				wtf.close();
			} catch (IOException e) {
				System.out.println("Error: IOException");
				e.printStackTrace();
			} catch (Exception e) {
				System.out.println("Error: Could not close 'wtf'");
				e.printStackTrace();
			}
		}//main

}
